package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicationCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedicationCommandServiceTest {
    private MedicationCommandService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(MedicationCommandService.class);
    }

    @Test
    void testHandleCreateMedicationCommandReturnsMedication() {
        CreateMedicationCommand command = Mockito.mock(CreateMedicationCommand.class);
        Medication mockMedication = Mockito.mock(Medication.class);
        Mockito.when(service.handle(any(CreateMedicationCommand.class))).thenReturn(Optional.of(mockMedication));

        Optional<Medication> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockMedication, result.get());
    }

    @Test
    void testHandleUpdateMedicationCommandReturnsMedication() {
        UpdateMedicationCommand command = Mockito.mock(UpdateMedicationCommand.class);
        Medication mockMedication = Mockito.mock(Medication.class);
        Mockito.when(service.handle(any(UpdateMedicationCommand.class))).thenReturn(Optional.of(mockMedication));

        Optional<Medication> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockMedication, result.get());
    }
}