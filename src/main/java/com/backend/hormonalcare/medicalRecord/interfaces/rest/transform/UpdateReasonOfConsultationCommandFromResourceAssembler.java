package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateReasonOfConsultationResource;

public class UpdateReasonOfConsultationCommandFromResourceAssembler {
    public static UpdateReasonOfConsultationCommand toCommandFromResource(Long id, UpdateReasonOfConsultationResource resource) {
        return new UpdateReasonOfConsultationCommand(
                id,
                resource.description(),
                resource.symptoms(),
                resource.medicalRecordId()
        );
    }
}
