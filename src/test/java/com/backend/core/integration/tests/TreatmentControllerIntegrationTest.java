package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.TreatmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class TreatmentControllerIntegrationTest {

    private TreatmentCommandService treatmentCommandService;
    private TreatmentQueryService treatmentQueryService;
    private TreatmentController treatmentController;

    @BeforeEach
    void setUp() {
        treatmentCommandService = Mockito.mock(TreatmentCommandService.class);
        treatmentQueryService = Mockito.mock(TreatmentQueryService.class);
        treatmentController = new TreatmentController(treatmentCommandService, treatmentQueryService);
    }

    /**
     * Test que verifica que si existe un tratamiento con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     * Se mockea también el MedicalRecord para evitar NullPointerException en el assembler.
     */
    @Test
    void getTreatmentById_ReturnsOk_WhenExists() {
        Treatment mockTreatment = Mockito.mock(Treatment.class);
        var mockMedicalRecord = Mockito.mock(MedicalRecord.class);

        // Mock necesario para evitar NullPointerException en el assembler
        Mockito.when(mockTreatment.getMedicalRecord()).thenReturn(mockMedicalRecord);
        Mockito.when(mockMedicalRecord.getId()).thenReturn(1L);

        Mockito.when(treatmentQueryService.handle(any(GetTreatmentByIdQuery.class)))
                .thenReturn(Optional.of(mockTreatment));

        var response = treatmentController.getTreatmentById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    /**
     * Test que verifica que si NO existe un tratamiento con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getTreatmentById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(treatmentQueryService.handle(any(GetTreatmentByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = treatmentController.getTreatmentById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}