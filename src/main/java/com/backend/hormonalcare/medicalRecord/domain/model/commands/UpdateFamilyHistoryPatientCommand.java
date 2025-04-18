package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdateFamilyHistoryPatientCommand(
        Long id,
        String familyHistory
) {
}
