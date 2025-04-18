package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateTreatmentResource;

public class CreateTreatmentCommandFromResourceAssembler {
    public static CreateTreatmentCommand toCommandFromResource(CreateTreatmentResource resource){
        return new CreateTreatmentCommand(resource.description(), resource.medicalRecordId());
    }
}
