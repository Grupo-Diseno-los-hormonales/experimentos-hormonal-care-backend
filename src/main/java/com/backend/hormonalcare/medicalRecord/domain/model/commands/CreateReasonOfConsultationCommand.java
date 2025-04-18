package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record CreateReasonOfConsultationCommand(String description, String symptoms, Long medicalRecordId) {
}
