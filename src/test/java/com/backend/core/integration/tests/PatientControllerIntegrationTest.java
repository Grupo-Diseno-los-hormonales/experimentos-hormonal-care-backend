package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetPatientByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.PatientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class PatientControllerIntegrationTest {

    private PatientCommandService patientCommandService;
    private PatientQueryService patientQueryService;
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        patientCommandService = Mockito.mock(PatientCommandService.class);
        patientQueryService = Mockito.mock(PatientQueryService.class);
        patientController = new PatientController(patientCommandService, patientQueryService);
    }

/**
 * Test que verifica que si existe un paciente con el ID dado,
 * el controlador retorna HTTP 200 y el body no es null.
 * Se mockean también los objetos PersonalHistory y FamilyHistory,
 * ya que el assembler accede a ambos al transformar la entidad Patient
 * a su recurso de respuesta, evitando así posibles NullPointerException.
 */
@Test
void getPatientById_ReturnsOk_WhenPatientExists() {
    Patient mockPatient = Mockito.mock(Patient.class);
    var mockPersonalHistory = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PersonalHistory.class);
    var mockFamilyHistory = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.FamilyHistory.class);

    // Mocks necesarios para evitar NullPointerException en el assembler
    Mockito.when(mockPatient.getPersonalHistory()).thenReturn(mockPersonalHistory);
    Mockito.when(mockPersonalHistory.personalHistory()).thenReturn("historial");
    Mockito.when(mockPatient.getFamilyHistory()).thenReturn(mockFamilyHistory);
    Mockito.when(mockFamilyHistory.familyHistory()).thenReturn("familiar");

    Mockito.when(patientQueryService.handle(any(GetPatientByIdQuery.class)))
            .thenReturn(Optional.of(mockPatient));

    var response = patientController.getPatientById(1L);

    assertTrue(response.getStatusCode().is2xxSuccessful());
    assertNotNull(response.getBody());
}
    /**
     * Test que verifica que si NO existe un paciente con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getPatientById_ReturnsNotFound_WhenPatientNotExists() {
        Mockito.when(patientQueryService.handle(any(GetPatientByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = patientController.getPatientById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}