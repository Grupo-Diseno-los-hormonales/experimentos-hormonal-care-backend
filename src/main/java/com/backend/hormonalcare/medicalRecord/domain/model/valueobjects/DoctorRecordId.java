package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record DoctorRecordId(String doctorRecordId) {
    public DoctorRecordId() {
        this(UUID.randomUUID().toString());
    }

    public DoctorRecordId {
        if (doctorRecordId == null || doctorRecordId.isBlank()) {
            throw new IllegalArgumentException("Patient record profileId cannot be null or blank");
        }
    }
}