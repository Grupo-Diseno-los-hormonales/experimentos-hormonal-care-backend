package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetPatientByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.PatientController;
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

@WebMvcTest(PatientController.class)
class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientQueryService patientQueryService;

    @Test
    void getPatientById_ReturnsOk_WhenPatientExists() throws Exception {
        Mockito.when(patientQueryService.handle(any(GetPatientByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(Patient.class)));

        mockMvc.perform(get("/api/v1/medical-record/patient/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById_ReturnsNotFound_WhenPatientNotExists() throws Exception {
        Mockito.when(patientQueryService.handle(any(GetPatientByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medical-record/patient/1"))
                .andExpect(status().isNotFound());
    }
}