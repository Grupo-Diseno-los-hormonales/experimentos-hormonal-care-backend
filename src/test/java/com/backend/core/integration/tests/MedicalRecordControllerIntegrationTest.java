package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalRecordByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalRecordController;
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

@WebMvcTest(MedicalRecordController.class)
class MedicalRecordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordQueryService medicalRecordQueryService;

    @Test
    void getMedicalRecordById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(medicalRecordQueryService.handle(any(GetMedicalRecordByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(MedicalRecord.class)));

        mockMvc.perform(get("/api/v1/medicalRecords/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getMedicalRecordById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(medicalRecordQueryService.handle(any(GetMedicalRecordByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medicalRecords/1"))
                .andExpect(status().isNotFound());
    }
}