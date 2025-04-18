package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record DrugName(String name) {
    public DrugName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Drug name cannot be null or empty");
        }
    }
}
