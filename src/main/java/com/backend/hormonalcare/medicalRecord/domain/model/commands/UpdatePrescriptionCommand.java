package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record UpdatePrescriptionCommand(
        Long id,
        Long medicalRecordId,
        Date prescriptionDate,
        String notes) {
}
