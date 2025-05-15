package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePatientCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PatientCommandServiceTest {
    private PatientCommandService patientCommandService;

    @BeforeEach
    void setUp() {
        patientCommandService = Mockito.mock(PatientCommandService.class);
    }

    @Test
    void testHandleCreatePatientCommandReturnsPatient() {
        CreatePatientCommand command = Mockito.mock(CreatePatientCommand.class);
        Patient mockPatient = Mockito.mock(Patient.class);
        Mockito.when(patientCommandService.handle(any(CreatePatientCommand.class))).thenReturn(Optional.of(mockPatient));

        Optional<Patient> result = patientCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockPatient, result.get());
    }
}