package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.util.Date;

public record CreatePrescriptionResource(
        Long medicalRecordId,
        Date prescriptionDate,
        String notes) { }

