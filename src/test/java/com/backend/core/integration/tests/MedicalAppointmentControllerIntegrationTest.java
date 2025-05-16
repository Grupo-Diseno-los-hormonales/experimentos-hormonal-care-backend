package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalAppointmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalAppointmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class MedicalAppointmentControllerIntegrationTest {

    private MedicalAppointmentQueryService medicalAppointmentQueryService;
    private MedicalAppointmentCommandService medicalAppointmentCommandService;
    private MedicalAppointmentController medicalAppointmentController;

    @BeforeEach
    void setUp() {
        medicalAppointmentQueryService = Mockito.mock(MedicalAppointmentQueryService.class);
        medicalAppointmentCommandService = Mockito.mock(MedicalAppointmentCommandService.class);
        medicalAppointmentController = new MedicalAppointmentController(medicalAppointmentCommandService, medicalAppointmentQueryService);
    }

    /**
     * Test que verifica que si existe una cita médica con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     */
@Test
void getMedicalAppointmentById_ReturnsOk_WhenExists() {
    MedicalAppointment mockAppointment = Mockito.mock(MedicalAppointment.class);
    var mockDoctor = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor.class);
    var mockPatient = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient.class);

    // Mock necesarios para evitar NullPointerException en el assembler
    Mockito.when(mockAppointment.getDoctor()).thenReturn(mockDoctor);
    Mockito.when(mockDoctor.getId()).thenReturn(1L);
    Mockito.when(mockAppointment.getPatient()).thenReturn(mockPatient);
    Mockito.when(mockPatient.getId()).thenReturn(1L);

    Mockito.when(medicalAppointmentQueryService.handle(any(GetMedicalAppointmentByIdQuery.class)))
            .thenReturn(Optional.of(mockAppointment));

    var response = medicalAppointmentController.getMedicalAppointmentById(1L);

    assertTrue(response.getStatusCode().is2xxSuccessful());
    assertNotNull(response.getBody());
}
    /**
     * Test que verifica que si NO existe una cita médica con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getMedicalAppointmentById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(medicalAppointmentQueryService.handle(any(GetMedicalAppointmentByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = medicalAppointmentController.getMedicalAppointmentById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}