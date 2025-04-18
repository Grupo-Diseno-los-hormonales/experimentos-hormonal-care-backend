package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdateMedicationTypeCommand(Long id, String typeName) {
}
