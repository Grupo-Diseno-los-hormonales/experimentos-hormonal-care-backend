package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateDoctorCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateDoctorResource;

public class CreateDoctorCommandFromResourceAssembler {
    public static CreateDoctorCommand toCommandFromResource(CreateDoctorResource resource) {
        return new CreateDoctorCommand(
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday(),
                resource.userId(),
                resource.professionalIdentificationNumber(),
                resource.subSpecialty()
        );
    }
}
