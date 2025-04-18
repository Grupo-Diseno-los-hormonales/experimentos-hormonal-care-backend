package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdateMedicationCommand(Long id,Long medicalRecordId, Long prescriptionId, Long medicationTypeId, String name, int amount, String unitQ, int value, String unit, int timesPerDay, String timePeriod) {
}
