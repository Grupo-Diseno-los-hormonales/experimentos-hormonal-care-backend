package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreatePrescriptionResource;

public class CreatePrescriptionCommandFromResourceAssembler {
    public static CreatePrescriptionCommand toCommandFromResource(CreatePrescriptionResource resource) {
        return new CreatePrescriptionCommand(
                resource.medicalRecordId(),
                resource.prescriptionDate(),
                resource.notes()
        );
    }
}