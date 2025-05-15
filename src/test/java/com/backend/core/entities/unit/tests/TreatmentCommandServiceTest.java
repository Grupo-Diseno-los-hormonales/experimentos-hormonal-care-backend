package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TreatmentCommandServiceTest {
    private TreatmentCommandService treatmentCommandService;

    @BeforeEach
    void setUp() {
        treatmentCommandService = Mockito.mock(TreatmentCommandService.class);
    }

    @Test
    void testHandleCreateTreatmentCommandReturnsTreatment() {
        CreateTreatmentCommand command = Mockito.mock(CreateTreatmentCommand.class);
        Treatment mockTreatment = Mockito.mock(Treatment.class);
        Mockito.when(treatmentCommandService.handle(any(CreateTreatmentCommand.class))).thenReturn(Optional.of(mockTreatment));

        Optional<Treatment> result = treatmentCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockTreatment, result.get());
    }
}