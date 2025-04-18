package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record FamilyHistory(String familyHistory) {
    public FamilyHistory {
    }
}
