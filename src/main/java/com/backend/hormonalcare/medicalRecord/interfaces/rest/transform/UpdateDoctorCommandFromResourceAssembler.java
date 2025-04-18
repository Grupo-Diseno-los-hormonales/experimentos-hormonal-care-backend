package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateDoctorCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateDoctorResource;

public class UpdateDoctorCommandFromResourceAssembler {
    public static UpdateDoctorCommand toCommandFromResource(Long id, UpdateDoctorResource resource) {
        return new UpdateDoctorCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday(),
                resource.professionalIdentificationNumber(),
                resource.subSpecialty()
        );
    }
}
