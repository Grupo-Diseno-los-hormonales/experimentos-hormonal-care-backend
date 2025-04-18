package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdatePersonalHistoryPatientCommand(
        Long id,
        String personalHistory
) {
}
