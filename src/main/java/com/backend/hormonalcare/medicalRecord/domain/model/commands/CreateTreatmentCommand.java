package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record CreateTreatmentCommand(String description, Long medicalRecordId) {

}
