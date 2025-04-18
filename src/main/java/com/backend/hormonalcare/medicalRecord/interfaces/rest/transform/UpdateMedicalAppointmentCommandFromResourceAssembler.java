package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateMedicalAppointmentResource;

public class UpdateMedicalAppointmentCommandFromResourceAssembler {
    public static UpdateMedicalAppointmentCommand toCommandFromResource(Long id, UpdateMedicalAppointmentResource resource) {
        return new UpdateMedicalAppointmentCommand(
                id,
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

