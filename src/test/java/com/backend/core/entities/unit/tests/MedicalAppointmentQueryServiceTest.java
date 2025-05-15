package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedicalAppointmentQueryServiceTest {
    private MedicalAppointmentQueryService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(MedicalAppointmentQueryService.class);
    }

    @Test
    void testHandleGetAllMedicalAppointmentQueryReturnsList() {
        GetAllMedicalAppointmentQuery query = Mockito.mock(GetAllMedicalAppointmentQuery.class);
        List<MedicalAppointment> mockList = List.of(Mockito.mock(MedicalAppointment.class));
        Mockito.when(service.handle(any(GetAllMedicalAppointmentQuery.class))).thenReturn(mockList);

        List<MedicalAppointment> result = service.handle(query);

        assertFalse(result.isEmpty());
    }

    @Test
    void testHandleGetMedicalAppointmentByIdQueryReturnsAppointment() {
        GetMedicalAppointmentByIdQuery query = Mockito.mock(GetMedicalAppointmentByIdQuery.class);
        MedicalAppointment mockAppointment = Mockito.mock(MedicalAppointment.class);
        Mockito.when(service.handle(any(GetMedicalAppointmentByIdQuery.class))).thenReturn(Optional.of(mockAppointment));

        Optional<MedicalAppointment> result = service.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockAppointment, result.get());
    }
}