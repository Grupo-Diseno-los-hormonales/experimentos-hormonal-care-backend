package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicationTypeResource;

public class CreateMedicationTypeCommandFromResourceAssembler {
    public static CreateMedicationTypeCommand toCommandFromResource(CreateMedicationTypeResource resource) {
        return new CreateMedicationTypeCommand(resource.typeName());
    }
}