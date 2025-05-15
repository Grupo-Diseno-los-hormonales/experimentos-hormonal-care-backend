package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicationQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicationController;
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

@WebMvcTest(MedicationController.class)
class MedicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationQueryService medicationQueryService;

    @Test
    void getMedicationById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(medicationQueryService.handle(any(GetMedicationByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(Medication.class)));

        mockMvc.perform(get("/api/v1/medical-record/medications/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getMedicationById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(medicationQueryService.handle(any(GetMedicationByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medical-record/medications/1"))
                .andExpect(status().isNotFound());
    }
}