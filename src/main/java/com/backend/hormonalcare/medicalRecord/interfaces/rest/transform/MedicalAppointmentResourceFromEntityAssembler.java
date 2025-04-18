package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicalAppointmentResource;

public class MedicalAppointmentResourceFromEntityAssembler {
    public static MedicalAppointmentResource toResourceFromEntity(MedicalAppointment entity) {
        return new MedicalAppointmentResource(
                entity.getId(),
                entity.getEventDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDoctor().getId(),
                entity.getPatient().getId(),
                entity.getColor()
        );
    }
}
