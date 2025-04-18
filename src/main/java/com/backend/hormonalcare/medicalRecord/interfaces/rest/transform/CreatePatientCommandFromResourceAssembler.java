package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePatientCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreatePatientResource;

public class CreatePatientCommandFromResourceAssembler {
    public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource){
        return new CreatePatientCommand(
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday(),
                resource.userId(),
                resource.typeOfBlood(),
                resource.personalHistory(),
                resource.familyHistory(),
                resource.doctorId()
        );
    }
}
