package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReasonOfConsultationQueryServiceTest {
    private ReasonOfConsultationQueryService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(ReasonOfConsultationQueryService.class);
    }

    @Test
    void testHandleGetReasonOfConsultationByIdQueryReturnsReason() {
        GetReasonOfConsultationByIdQuery query = Mockito.mock(GetReasonOfConsultationByIdQuery.class);
        ReasonOfConsultation mockReason = Mockito.mock(ReasonOfConsultation.class);
        Mockito.when(service.handle(any(GetReasonOfConsultationByIdQuery.class))).thenReturn(Optional.of(mockReason));

        Optional<ReasonOfConsultation> result = service.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockReason, result.get());
    }

    @Test
    void testHandleGetReasonOfConsultationByMedicalRecordIdQueryReturnsList() {
        GetReasonOfConsultationByMedicalRecordIdQuery query = Mockito.mock(GetReasonOfConsultationByMedicalRecordIdQuery.class);
        List<ReasonOfConsultation> mockList = List.of(Mockito.mock(ReasonOfConsultation.class));
        Mockito.when(service.handle(any(GetReasonOfConsultationByMedicalRecordIdQuery.class))).thenReturn(mockList);

        List<ReasonOfConsultation> result = service.handle(query);

        assertFalse(result.isEmpty());
    }
}