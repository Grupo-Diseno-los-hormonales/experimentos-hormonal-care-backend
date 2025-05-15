package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.PrescriptionCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PrescriptionCommandServiceTest {
    private PrescriptionCommandService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(PrescriptionCommandService.class);
    }

    @Test
    void testHandleCreatePrescriptionCommandReturnsPrescription() {
        CreatePrescriptionCommand command = Mockito.mock(CreatePrescriptionCommand.class);
        Prescription mockPrescription = Mockito.mock(Prescription.class);
        Mockito.when(service.handle(any(CreatePrescriptionCommand.class))).thenReturn(Optional.of(mockPrescription));

        Optional<Prescription> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockPrescription, result.get());
    }

    @Test
    void testHandleUpdatePrescriptionCommandReturnsPrescription() {
        UpdatePrescriptionCommand command = Mockito.mock(UpdatePrescriptionCommand.class);
        Prescription mockPrescription = Mockito.mock(Prescription.class);
        Mockito.when(service.handle(any(UpdatePrescriptionCommand.class))).thenReturn(Optional.of(mockPrescription));

        Optional<Prescription> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockPrescription, result.get());
    }
}