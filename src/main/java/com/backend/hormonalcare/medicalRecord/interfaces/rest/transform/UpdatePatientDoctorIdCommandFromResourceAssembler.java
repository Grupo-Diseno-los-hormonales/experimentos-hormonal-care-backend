package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePatientDoctorIdCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdatePatientDoctorIdResource;

public class UpdatePatientDoctorIdCommandFromResourceAssembler {
    public static UpdatePatientDoctorIdCommand toCommandFromResource(Long id, UpdatePatientDoctorIdResource resource) {
        return new UpdatePatientDoctorIdCommand(
                id,
                resource.doctorId()
        );
    }
}
