package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdateTreatmentCommand(Long id, String description, Long medicalRecordId) {
}
