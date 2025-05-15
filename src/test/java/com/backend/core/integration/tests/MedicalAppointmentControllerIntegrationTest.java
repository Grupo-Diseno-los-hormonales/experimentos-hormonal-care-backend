package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalAppointmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalAppointmentController;
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

@WebMvcTest(MedicalAppointmentController.class)
class MedicalAppointmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalAppointmentQueryService medicalAppointmentQueryService;

    @Test
    void getMedicalAppointmentById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(medicalAppointmentQueryService.handle(any(GetMedicalAppointmentByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(MedicalAppointment.class)));

        mockMvc.perform(get("/api/v1/medicalAppointment/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getMedicalAppointmentById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(medicalAppointmentQueryService.handle(any(GetMedicalAppointmentByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/medicalAppointment/1"))
                .andExpect(status().isNotFound());
    }
}