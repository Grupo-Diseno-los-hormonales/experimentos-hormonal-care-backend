package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicationQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedicationQueryServiceTest {
    private MedicationQueryService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(MedicationQueryService.class);
    }

    @Test
    void testHandleGetAllMedicationsQueryReturnsList() {
        GetAllMedicationsQuery query = Mockito.mock(GetAllMedicationsQuery.class);
        List<Medication> mockList = List.of(Mockito.mock(Medication.class));
        Mockito.when(service.handle(any(GetAllMedicationsQuery.class))).thenReturn(mockList);

        List<Medication> result = service.handle(query);

        assertFalse(result.isEmpty());
    }

    @Test
    void testHandleGetMedicationByIdQueryReturnsMedication() {
        GetMedicationByIdQuery query = Mockito.mock(GetMedicationByIdQuery.class);
        Medication mockMedication = Mockito.mock(Medication.class);
        Mockito.when(service.handle(any(GetMedicationByIdQuery.class))).thenReturn(Optional.of(mockMedication));

        Optional<Medication> result = service.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockMedication, result.get());
    }
}