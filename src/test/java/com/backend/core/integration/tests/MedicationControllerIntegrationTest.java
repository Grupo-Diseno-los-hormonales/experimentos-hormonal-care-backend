package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.*;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class MedicationControllerIntegrationTest {

    private MedicationCommandService medicationCommandService;
    private MedicationQueryService medicationQueryService;
    private MedicationTypeCommandService medicationTypeCommandService;
    private MedicationTypeQueryService medicationTypeQueryService;
    private PrescriptionCommandService prescriptionCommandService;
    private PrescriptionQueryService prescriptionQueryService;
    private MedicationController medicationController;

    @BeforeEach
    void setUp() {
        medicationCommandService = Mockito.mock(MedicationCommandService.class);
        medicationQueryService = Mockito.mock(MedicationQueryService.class);
        medicationTypeCommandService = Mockito.mock(MedicationTypeCommandService.class);
        medicationTypeQueryService = Mockito.mock(MedicationTypeQueryService.class);
        prescriptionCommandService = Mockito.mock(PrescriptionCommandService.class);
        prescriptionQueryService = Mockito.mock(PrescriptionQueryService.class);
        medicationController = new MedicationController(
                medicationCommandService,
                medicationQueryService,
                medicationTypeCommandService,
                medicationTypeQueryService,
                prescriptionCommandService,
                prescriptionQueryService
        );
    }

/**
 * Test que verifica que si existe un medicamento con el ID dado,
 * el controlador retorna HTTP 200 y el body no es null.
 * Se mockean también el MedicalRecord, el Prescription y el MedicationType
 * para evitar NullPointerException en el assembler, ya que estos son accedidos
 * al transformar la entidad Medication a su recurso de respuesta.
 */
@Test
void getMedicationById_ReturnsOk_WhenExists() {
    Medication mockMedication = Mockito.mock(Medication.class);
    var mockMedicalRecord = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord.class);
    var mockPrescription = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription.class);
    var mockMedicationType = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType.class);

    // Mocks necesarios para evitar NullPointerException en el assembler
    Mockito.when(mockMedication.getMedicalRecord()).thenReturn(mockMedicalRecord);
    Mockito.when(mockMedicalRecord.getId()).thenReturn(1L);
    Mockito.when(mockMedication.getPrescription()).thenReturn(mockPrescription);
    Mockito.when(mockPrescription.getId()).thenReturn(1L);
    Mockito.when(mockMedication.getMedicationType()).thenReturn(mockMedicationType);
    Mockito.when(mockMedicationType.getId()).thenReturn(1L);

    Mockito.when(medicationQueryService.handle(any(GetMedicationByIdQuery.class)))
            .thenReturn(Optional.of(mockMedication));

    var response = medicationController.getMedicationById(1L);

    assertTrue(response.getStatusCode().is2xxSuccessful());
    assertNotNull(response.getBody());
}

/**
 * Test que verifica que si NO existe un medicamento con el ID dado,
 * el controlador retorna HTTP 404 y el body es null.
 */
@Test
void getMedicationById_ReturnsNotFound_WhenNotExists() {
    Mockito.when(medicationQueryService.handle(any(GetMedicationByIdQuery.class)))
            .thenReturn(Optional.empty());

    var response = medicationController.getMedicationById(1L);

    assertTrue(response.getStatusCode().is4xxClientError());
    assertNull(response.getBody());
}
}