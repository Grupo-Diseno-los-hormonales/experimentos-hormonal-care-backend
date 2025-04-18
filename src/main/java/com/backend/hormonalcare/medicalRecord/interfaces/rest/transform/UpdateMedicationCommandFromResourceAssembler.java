package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateMedicationResource;

public class UpdateMedicationCommandFromResourceAssembler {
    public static UpdateMedicationCommand toCommandFromResource(Long id, UpdateMedicationResource resource) {
        return new UpdateMedicationCommand(
                id,
                resource.medicalRecordId(),
                resource.prescriptionId(),
                resource.medicationTypeId(),
                resource.name(),
                resource.amount(),
                resource.unitQ(),
                resource.value(),
                resource.unit(),
                resource.timesPerDay(),
                resource.timePeriod()
        );
    }
}

