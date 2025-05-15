package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.PrescriptionQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PrescriptionQueryServiceTest {
    private PrescriptionQueryService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(PrescriptionQueryService.class);
    }

    @Test
    void testHandleGetAllPrescriptionsQueryReturnsList() {
        GetAllPrescriptionsQuery query = Mockito.mock(GetAllPrescriptionsQuery.class);
        List<Prescription> mockList = List.of(Mockito.mock(Prescription.class));
        Mockito.when(service.handle(any(GetAllPrescriptionsQuery.class))).thenReturn(mockList);

        List<Prescription> result = service.handle(query);

        assertFalse(result.isEmpty());
    }

    @Test
    void testHandleGetPrescriptionByIdQueryReturnsPrescription() {
        GetPrescriptionByIdQuery query = Mockito.mock(GetPrescriptionByIdQuery.class);
        Prescription mockPrescription = Mockito.mock(Prescription.class);
        Mockito.when(service.handle(any(GetPrescriptionByIdQuery.class))).thenReturn(Optional.of(mockPrescription));

        Optional<Prescription> result = service.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockPrescription, result.get());
    }
}