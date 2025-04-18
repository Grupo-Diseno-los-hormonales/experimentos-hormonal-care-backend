package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record PatientRecordId(String patientRecordId) {
    public PatientRecordId() {
        this(UUID.randomUUID().toString());
    }

    public PatientRecordId {
        if (patientRecordId == null || patientRecordId.isBlank()) {
            throw new IllegalArgumentException("Patient record profileId cannot be null or blank");
        }
    }
}