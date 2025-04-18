package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record Frequency(int timesPerDay) {
    public Frequency {
        if (timesPerDay <= 0) {
            throw new IllegalArgumentException("Times per day must be greater than zero");
        }
    }
}