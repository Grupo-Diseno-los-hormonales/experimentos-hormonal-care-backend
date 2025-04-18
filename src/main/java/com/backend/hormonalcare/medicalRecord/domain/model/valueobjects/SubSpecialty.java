package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SubSpecialty(String subSpecialty) {
    public SubSpecialty {
        if (subSpecialty == null || subSpecialty.isBlank()) {
            throw new IllegalArgumentException("SubSpecialty cannot be null or empty");
        }
    }


}
