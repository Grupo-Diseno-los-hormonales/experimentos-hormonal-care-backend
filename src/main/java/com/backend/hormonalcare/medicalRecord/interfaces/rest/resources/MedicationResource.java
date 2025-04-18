package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

public record MedicationResource(
        Long id,
        Long medicalRecordId,
        Long prescriptionId,
        Long medicationTypeId,
        String drugName,
        String quantity,
        String concentration,
        String frequency,
        String duration) {
}