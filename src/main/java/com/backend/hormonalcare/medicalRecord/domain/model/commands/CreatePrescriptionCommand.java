package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record CreatePrescriptionCommand(
        Long medicalRecord,
        Date prescriptionDate,
        String notes) {
}