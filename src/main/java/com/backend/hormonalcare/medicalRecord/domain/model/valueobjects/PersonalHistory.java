package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record PersonalHistory(String personalHistory) {
    public PersonalHistory {
    }
}
