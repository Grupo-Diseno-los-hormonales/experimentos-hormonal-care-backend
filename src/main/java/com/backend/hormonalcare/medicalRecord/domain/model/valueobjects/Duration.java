package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Duration(String timePeriod) {
    public Duration {
        if (timePeriod == null || timePeriod.isBlank()) {
            throw new IllegalArgumentException("Duration time period cannot be null or empty");
        }
    }
}