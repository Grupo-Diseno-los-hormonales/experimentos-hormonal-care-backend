package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.TreatmentController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TreatmentController.class)
class TreatmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreatmentQueryService treatmentQueryService;

    @Test
    void getTreatmentById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(treatmentQueryService.handle(any(GetTreatmentByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(Treatment.class)));

        mockMvc.perform(get("/api/v1/medical-record/treatments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getTreatmentById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(treatmentQueryService.handle(any(GetTreatmentByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medical-record/treatments/1"))
                .andExpect(status().isNotFound());
    }
}