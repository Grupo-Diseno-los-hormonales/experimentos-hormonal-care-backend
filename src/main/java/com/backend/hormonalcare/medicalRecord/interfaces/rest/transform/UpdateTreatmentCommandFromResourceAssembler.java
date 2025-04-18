package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateTreatmentResource;

public class UpdateTreatmentCommandFromResourceAssembler {
    public static UpdateTreatmentCommand toCommandFromResource(Long id, UpdateTreatmentResource resource){
        return new UpdateTreatmentCommand(
                id,
                resource.description(),
                resource.medicalRecordId()
        );
    }
}
