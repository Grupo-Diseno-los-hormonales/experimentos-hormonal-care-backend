package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetDoctorByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.DoctorQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.DoctorController;
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

@WebMvcTest(DoctorController.class)
class DoctorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorQueryService doctorQueryService;

    @Test
    void getDoctorById_ReturnsOk_WhenDoctorExists() throws Exception {
        Mockito.when(doctorQueryService.handle(any(GetDoctorByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(Doctor.class)));

        mockMvc.perform(get("/api/v1/doctor/doctor/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getDoctorById_ReturnsNotFound_WhenDoctorNotExists() throws Exception {
        Mockito.when(doctorQueryService.handle(any(GetDoctorByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/doctor/doctor/1"))
                .andExpect(status().isNotFound());
    }
}