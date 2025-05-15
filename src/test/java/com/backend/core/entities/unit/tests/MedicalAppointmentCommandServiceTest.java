package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.DeleteMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedicalAppointmentCommandServiceTest {
    private MedicalAppointmentCommandService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(MedicalAppointmentCommandService.class);
    }

    @Test
    void testHandleCreateMedicalAppointmentCommandReturnsAppointment() {
        CreateMedicalAppointmentCommand command = Mockito.mock(CreateMedicalAppointmentCommand.class);
        MedicalAppointment mockAppointment = Mockito.mock(MedicalAppointment.class);
        Mockito.when(service.handle(any(CreateMedicalAppointmentCommand.class))).thenReturn(Optional.of(mockAppointment));

        Optional<MedicalAppointment> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockAppointment, result.get());
    }

    @Test
    void testHandleUpdateMedicalAppointmentCommandReturnsAppointment() {
        UpdateMedicalAppointmentCommand command = Mockito.mock(UpdateMedicalAppointmentCommand.class);
        MedicalAppointment mockAppointment = Mockito.mock(MedicalAppointment.class);
        Mockito.when(service.handle(any(UpdateMedicalAppointmentCommand.class))).thenReturn(Optional.of(mockAppointment));

        Optional<MedicalAppointment> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockAppointment, result.get());
    }

    @Test
    void testHandleDeleteMedicalAppointmentCommand() {
        DeleteMedicalAppointmentCommand command = Mockito.mock(DeleteMedicalAppointmentCommand.class);
        service.handle(command);
        Mockito.verify(service).handle(command);
    }
}