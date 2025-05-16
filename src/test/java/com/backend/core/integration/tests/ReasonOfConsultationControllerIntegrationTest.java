package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.ReasonOfConsultationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ReasonOfConsultationControllerIntegrationTest {

    private ReasonOfConsultationCommandService reasonOfConsultationCommandService;
    private ReasonOfConsultationQueryService reasonOfConsultationQueryService;
    private ReasonOfConsultationController reasonOfConsultationController;

    @BeforeEach
    void setUp() {
        reasonOfConsultationCommandService = Mockito.mock(ReasonOfConsultationCommandService.class);
        reasonOfConsultationQueryService = Mockito.mock(ReasonOfConsultationQueryService.class);
        reasonOfConsultationController = new ReasonOfConsultationController(
                reasonOfConsultationCommandService,
                reasonOfConsultationQueryService
        );
    }

    /**
     * Test que verifica que si existe un motivo de consulta con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     * Se mockea también el MedicalRecord para evitar NullPointerException en el assembler.
     */
    @Test
    void getReasonOfConsultationById_ReturnsOk_WhenExists() {
        ReasonOfConsultation mockReason = Mockito.mock(ReasonOfConsultation.class);
        var mockMedicalRecord = Mockito.mock(MedicalRecord.class);

        // Mock necesario para evitar NullPointerException en el assembler
        Mockito.when(mockReason.getMedicalRecord()).thenReturn(mockMedicalRecord);
        Mockito.when(mockMedicalRecord.getId()).thenReturn(1L);

        Mockito.when(reasonOfConsultationQueryService.handle(any(GetReasonOfConsultationByIdQuery.class)))
                .thenReturn(Optional.of(mockReason));

        var response = reasonOfConsultationController.getReasonOfConsultationById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    /**
     * Test que verifica que si NO existe un motivo de consulta con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getReasonOfConsultationById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(reasonOfConsultationQueryService.handle(any(GetReasonOfConsultationByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = reasonOfConsultationController.getReasonOfConsultationById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}