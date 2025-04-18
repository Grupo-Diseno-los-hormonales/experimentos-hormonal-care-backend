package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdatePrescriptionResource;

public class UpdatePrescriptionCommandFromResourceAssembler {
    public static UpdatePrescriptionCommand toCommandFromResource(Long id, UpdatePrescriptionResource resource) {
        return new UpdatePrescriptionCommand(
                id,
                resource.medicalRecordId(),
                resource.prescriptionDate(),
                resource.notes()
        );
    }
}