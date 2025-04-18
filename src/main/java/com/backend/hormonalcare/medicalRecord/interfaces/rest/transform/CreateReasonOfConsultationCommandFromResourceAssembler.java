package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateReasonOfConsultationResource;

public class CreateReasonOfConsultationCommandFromResourceAssembler {
    public static CreateReasonOfConsultationCommand toCommandFromResource(CreateReasonOfConsultationResource resource) {
        return new CreateReasonOfConsultationCommand(
                resource.description(),
                resource.symptoms(),
                resource.medicalRecordId()
        );
    }
}
