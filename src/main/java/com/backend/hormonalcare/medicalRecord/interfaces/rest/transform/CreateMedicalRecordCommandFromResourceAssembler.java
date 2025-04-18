package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalRecordCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicalRecordResource;

public class CreateMedicalRecordCommandFromResourceAssembler {
    public static CreateMedicalRecordCommand toCommandFromResource(CreateMedicalRecordResource resource){
        return new CreateMedicalRecordCommand(resource.patientId());
    }
}
