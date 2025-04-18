package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateMedicationTypeResource;

public class UpdateMedicationTypeCommandFromResourceAssembler {
    public static UpdateMedicationTypeCommand toCommandFromResource(Long id, UpdateMedicationTypeResource resource) {
        return new UpdateMedicationTypeCommand(
                id,
                resource.typeName()
        );
    }
}