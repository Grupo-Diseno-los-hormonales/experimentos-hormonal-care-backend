package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicalAppointmentResource;

public class CreateMedicalAppointmentCommandFromResourceAssembler {
    public static CreateMedicalAppointmentCommand toCommandFromResource(CreateMedicalAppointmentResource resource) {
        return new CreateMedicalAppointmentCommand(
                resource.eventDate(),
                resource.startTime(),
                resource.endTime(),
                resource.title(),
                resource.description(),
                resource.doctorId(),
                resource.patientId(),
                resource.color()
        );
    }
}


