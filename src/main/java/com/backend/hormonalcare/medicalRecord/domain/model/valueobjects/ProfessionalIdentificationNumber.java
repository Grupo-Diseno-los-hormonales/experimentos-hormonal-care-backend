package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfessionalIdentificationNumber(Long professionalIdentificationNumber) {
    public ProfessionalIdentificationNumber {
        if (professionalIdentificationNumber == null || professionalIdentificationNumber == 0) {
            throw new IllegalArgumentException("Professional Identification Number cannot be null or empty");
        }
    }
}
