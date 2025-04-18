package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Concentration(int value_concentration, String unit_concentration) {
    public Concentration {
        if (value_concentration <= 0) {
            throw new IllegalArgumentException("Concentration value must be greater than zero");
        }
        if (unit_concentration == null || unit_concentration.isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
    }
}
