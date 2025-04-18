package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record CreateMedicationCommand(Long medicalRecordId, Long medicalTypeId, Long prescriptionId, String name, int amount, String unitQ, int value, String unit, int timesPerDay, String timePeriod) {
}

