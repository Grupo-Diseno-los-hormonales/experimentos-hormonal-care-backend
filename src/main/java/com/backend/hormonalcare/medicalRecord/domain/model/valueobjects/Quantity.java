package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Quantity(int amount, String unitQ) {
    public Quantity {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (unitQ == null || unitQ.isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
    }
}
