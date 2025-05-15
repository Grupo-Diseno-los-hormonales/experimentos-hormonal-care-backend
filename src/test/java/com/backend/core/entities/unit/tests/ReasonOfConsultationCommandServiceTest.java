package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReasonOfConsultationCommandServiceTest {
    private ReasonOfConsultationCommandService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(ReasonOfConsultationCommandService.class);
    }

    @Test
    void testHandleCreateReasonOfConsultationCommandReturnsReason() {
        CreateReasonOfConsultationCommand command = Mockito.mock(CreateReasonOfConsultationCommand.class);
        ReasonOfConsultation mockReason = Mockito.mock(ReasonOfConsultation.class);
        Mockito.when(service.handle(any(CreateReasonOfConsultationCommand.class))).thenReturn(Optional.of(mockReason));

        Optional<ReasonOfConsultation> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockReason, result.get());
    }

    @Test
    void testHandleUpdateReasonOfConsultationCommandReturnsReason() {
        UpdateReasonOfConsultationCommand command = Mockito.mock(UpdateReasonOfConsultationCommand.class);
        ReasonOfConsultation mockReason = Mockito.mock(ReasonOfConsultation.class);
        Mockito.when(service.handle(any(UpdateReasonOfConsultationCommand.class))).thenReturn(Optional.of(mockReason));

        Optional<ReasonOfConsultation> result = service.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockReason, result.get());
    }
}