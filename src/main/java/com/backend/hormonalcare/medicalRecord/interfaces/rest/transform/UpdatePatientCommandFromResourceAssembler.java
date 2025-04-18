package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePatientCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdatePatientResource;

public class UpdatePatientCommandFromResourceAssembler {
    public static UpdatePatientCommand toCommandFromResource(Long id, UpdatePatientResource resource) {
        return new UpdatePatientCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday(),
                resource.typeOfBlood(),
                resource.personalHistory(),
                resource.familyHistory()
        );
    }
}
