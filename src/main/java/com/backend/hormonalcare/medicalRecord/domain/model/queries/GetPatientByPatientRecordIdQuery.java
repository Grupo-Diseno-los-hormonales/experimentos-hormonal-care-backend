package com.backend.hormonalcare.medicalRecord.domain.model.queries;

import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PatientRecordId;

public record GetPatientByPatientRecordIdQuery(PatientRecordId patientRecordId) {
}
