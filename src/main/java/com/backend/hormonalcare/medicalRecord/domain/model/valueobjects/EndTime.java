package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EndTime(String time) {
    public EndTime {
        if (time == null || time.isBlank()) {
            throw new IllegalArgumentException("End time cannot be null or empty");
        }
    }
}