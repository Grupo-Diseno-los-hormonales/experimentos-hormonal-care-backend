package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record CreateMedicalRecordCommand(
        Long patientId
) {
}
