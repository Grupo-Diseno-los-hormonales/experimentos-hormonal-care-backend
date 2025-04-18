package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Certification(String certification) {
    public Certification{
        if (certification == null || certification.isBlank()) {
            throw new IllegalArgumentException("Certification cannot be null or empty");
        }
    }
}
