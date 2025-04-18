package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

public record ReasonOfConsultationResource(
        String description,
        String symptoms,
        Long medicalRecordId
) {
}
