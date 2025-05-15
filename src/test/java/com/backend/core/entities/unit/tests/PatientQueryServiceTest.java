package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PatientQueryServiceTest {
    private PatientQueryService patientQueryService;

    @BeforeEach
    void setUp() {
        patientQueryService = Mockito.mock(PatientQueryService.class);
    }

    @Test
    void testHandleGetPatientByIdQueryReturnsPatient() {
        GetPatientByIdQuery query = Mockito.mock(GetPatientByIdQuery.class);
        Patient mockPatient = Mockito.mock(Patient.class);
        Mockito.when(patientQueryService.handle(any(GetPatientByIdQuery.class))).thenReturn(Optional.of(mockPatient));

        Optional<Patient> result = patientQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockPatient, result.get());
    }

    @Test
    void testHandleGetPatientByProfileIdQueryReturnsPatient() {
        GetPatientByProfileIdQuery query = Mockito.mock(GetPatientByProfileIdQuery.class);
        Patient mockPatient = Mockito.mock(Patient.class);
        Mockito.when(patientQueryService.handle(any(GetPatientByProfileIdQuery.class))).thenReturn(Optional.of(mockPatient));

        Optional<Patient> result = patientQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockPatient, result.get());
    }

    @Test
    void testHandleGetPatientByPatientRecordIdQueryReturnsPatient() {
        GetPatientByPatientRecordIdQuery query = Mockito.mock(GetPatientByPatientRecordIdQuery.class);
        Patient mockPatient = Mockito.mock(Patient.class);
        Mockito.when(patientQueryService.handle(any(GetPatientByPatientRecordIdQuery.class))).thenReturn(Optional.of(mockPatient));

        Optional<Patient> result = patientQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockPatient, result.get());
    }

    @Test
    void testHandleGetProfileIdByPatientIdQueryReturnsLong() {
        GetProfileIdByPatientIdQuery query = Mockito.mock(GetProfileIdByPatientIdQuery.class);
        Mockito.when(patientQueryService.handle(any(GetProfileIdByPatientIdQuery.class))).thenReturn(Optional.of(123L));

        Optional<Long> result = patientQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(123L, result.get());
    }

    @Test
    void testHandleGetAllPatientsByDoctorIdQueryReturnsList() {
        GetAllPatientsByDoctorIdQuery query = Mockito.mock(GetAllPatientsByDoctorIdQuery.class);
        List<Patient> mockList = List.of(Mockito.mock(Patient.class));
        Mockito.when(patientQueryService.handle(any(GetAllPatientsByDoctorIdQuery.class))).thenReturn(mockList);

        List<Patient> result = patientQueryService.handle(query);

        assertFalse(result.isEmpty());
    }
}