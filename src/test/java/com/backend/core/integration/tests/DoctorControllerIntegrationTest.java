package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetDoctorByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.DoctorCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.DoctorQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.DoctorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class DoctorControllerIntegrationTest {

    private DoctorQueryService doctorQueryService;
    private DoctorCommandService doctorCommandService;
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        doctorQueryService = Mockito.mock(DoctorQueryService.class);
        doctorCommandService = Mockito.mock(DoctorCommandService.class);
        doctorController = new DoctorController(doctorCommandService, doctorQueryService);
    }

    /**
     * Test que verifica que si existe un doctor con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     */
       @Test
    void getDoctorById_ReturnsOk_WhenDoctorExists() {
        Doctor mockDoctor = Mockito.mock(Doctor.class);
        var mockProfessionalId = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfessionalIdentificationNumber.class);
        var mockSubSpecialty = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.SubSpecialty.class);
    
        Mockito.when(mockDoctor.getProfessionalIdentificationNumber()).thenReturn(mockProfessionalId);
        Mockito.when(mockProfessionalId.professionalIdentificationNumber()).thenReturn(123456L);
        Mockito.when(mockDoctor.getId()).thenReturn(1L);
    
        // Mock para evitar NullPointerException en el assembler
        Mockito.when(mockDoctor.getSubSpecialty()).thenReturn(mockSubSpecialty);
        Mockito.when(mockSubSpecialty.subSpecialty()).thenReturn("Cardiología");
    
        Mockito.when(doctorQueryService.handle(any(GetDoctorByIdQuery.class)))
                .thenReturn(Optional.of(mockDoctor));
    
        var response = doctorController.getDoctorById(1L);
    
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }
    /**
     * Test que verifica que si NO existe un doctor con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getDoctorById_ReturnsNotFound_WhenDoctorDoesNotExist() {
        Mockito.when(doctorQueryService.handle(any(GetDoctorByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = doctorController.getDoctorById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}