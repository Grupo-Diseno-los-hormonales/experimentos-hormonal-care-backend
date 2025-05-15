package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.ReasonOfConsultationController;
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

@WebMvcTest(ReasonOfConsultationController.class)
class ReasonOfConsultationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReasonOfConsultationQueryService reasonOfConsultationQueryService;

    @Test
    void getReasonOfConsultationById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(reasonOfConsultationQueryService.handle(any(GetReasonOfConsultationByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(ReasonOfConsultation.class)));

        mockMvc.perform(get("/api/v1/medical-record/reasons-of-consultation/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getReasonOfConsultationById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(reasonOfConsultationQueryService.handle(any(GetReasonOfConsultationByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medical-record/reasons-of-consultation/1"))
                .andExpect(status().isNotFound());
    }
}