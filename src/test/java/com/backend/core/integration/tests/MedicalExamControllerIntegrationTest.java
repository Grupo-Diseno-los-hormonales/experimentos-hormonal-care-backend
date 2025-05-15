package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalExamController;
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

@WebMvcTest(MedicalExamController.class)
class MedicalExamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalExamQueryService medicalExamQueryService;

    @Test
    void getMedicalExamById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(medicalExamQueryService.handle(any(GetMedicalExamByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(MedicalExam.class)));

        mockMvc.perform(get("/api/v1/medical-record/medical-exam/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getMedicalExamById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(medicalExamQueryService.handle(any(GetMedicalExamByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medical-record/medical-exam/1"))
                .andExpect(status().isNotFound());
    }
}