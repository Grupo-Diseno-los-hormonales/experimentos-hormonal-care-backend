package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalRecordByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalRecordController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class MedicalRecordControllerIntegrationTest {

    private MedicalRecordQueryService medicalRecordQueryService;
    private MedicalRecordCommandService medicalRecordCommandService;
    private MedicalRecordController medicalRecordController;

    @BeforeEach
    void setUp() {
        medicalRecordQueryService = Mockito.mock(MedicalRecordQueryService.class);
        medicalRecordCommandService = Mockito.mock(MedicalRecordCommandService.class);
        medicalRecordController = new MedicalRecordController(medicalRecordCommandService, medicalRecordQueryService);
    }

    /**
     * Test que verifica que si existe un registro médico con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     */
    @Test
    void getMedicalRecordById_ReturnsOk_WhenExists() {
        MedicalRecord mockMedicalRecord = Mockito.mock(MedicalRecord.class);

        Mockito.when(medicalRecordQueryService.handle(any(GetMedicalRecordByIdQuery.class)))
                .thenReturn(Optional.of(mockMedicalRecord));

        var response = medicalRecordController.getMedicalRecordById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    /**
     * Test que verifica que si NO existe un registro médico con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getMedicalRecordById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(medicalRecordQueryService.handle(any(GetMedicalRecordByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = medicalRecordController.getMedicalRecordById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}