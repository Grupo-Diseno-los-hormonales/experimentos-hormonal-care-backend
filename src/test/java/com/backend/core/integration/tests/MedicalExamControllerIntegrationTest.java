package com.backend.core.integration.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.MedicalExamController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class MedicalExamControllerIntegrationTest {

    private MedicalExamQueryService medicalExamQueryService;
    private MedicalExamCommandService medicalExamCommandService;
    private MedicalExamController medicalExamController;

    @BeforeEach
    void setUp() {
        medicalExamQueryService = Mockito.mock(MedicalExamQueryService.class);
        medicalExamCommandService = Mockito.mock(MedicalExamCommandService.class);
        medicalExamController = new MedicalExamController(medicalExamCommandService, medicalExamQueryService);
    }

    /**
     * Test que verifica que si existe un examen médico con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     */
   @Test
void getMedicalExamById_ReturnsOk_WhenExists() {
    MedicalExam mockExam = Mockito.mock(MedicalExam.class);
    var mockMedicalRecord = Mockito.mock(com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord.class);

    // Mock necesarios para evitar NullPointerException en el assembler
    Mockito.when(mockExam.getMedicalRecord()).thenReturn(mockMedicalRecord);
    Mockito.when(mockMedicalRecord.getId()).thenReturn(1L);

    Mockito.when(medicalExamQueryService.handle(any(GetMedicalExamByIdQuery.class)))
            .thenReturn(Optional.of(mockExam));

    var response = medicalExamController.getMedicalExamById(1L);

    assertTrue(response.getStatusCode().is2xxSuccessful());
    assertNotNull(response.getBody());
}
    /**
     * Test que verifica que si NO existe un examen médico con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getMedicalExamById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(medicalExamQueryService.handle(any(GetMedicalExamByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = medicalExamController.getMedicalExamById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}