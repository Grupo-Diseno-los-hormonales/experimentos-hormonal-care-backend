package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

public record UpdateReasonOfConsultationResource(
        String description,
        String symptoms,
        Long medicalRecordId
) { }
