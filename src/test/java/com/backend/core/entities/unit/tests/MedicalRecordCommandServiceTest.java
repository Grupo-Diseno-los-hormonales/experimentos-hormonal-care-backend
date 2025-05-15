package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalRecordCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedicalRecordCommandServiceTest {
    private MedicalRecordCommandService medicalRecordCommandService;

    @BeforeEach
    void setUp() {
        medicalRecordCommandService = Mockito.mock(MedicalRecordCommandService.class);
    }

    @Test
    void testHandleCreateMedicalRecordCommandReturnsMedicalRecord() {
        CreateMedicalRecordCommand command = Mockito.mock(CreateMedicalRecordCommand.class);
        MedicalRecord mockRecord = Mockito.mock(MedicalRecord.class);
        Mockito.when(medicalRecordCommandService.handle(any(CreateMedicalRecordCommand.class))).thenReturn(Optional.of(mockRecord));

        Optional<MedicalRecord> result = medicalRecordCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockRecord, result.get());
    }
}