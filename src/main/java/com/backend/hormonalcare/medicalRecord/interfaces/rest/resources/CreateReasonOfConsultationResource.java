package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

public record CreateReasonOfConsultationResource(
        String description,
        String symptoms,
        Long medicalRecordId
) { }
