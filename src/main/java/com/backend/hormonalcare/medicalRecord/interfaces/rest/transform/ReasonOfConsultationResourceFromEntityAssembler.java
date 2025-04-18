package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.ReasonOfConsultationResource;

public class ReasonOfConsultationResourceFromEntityAssembler {
    public static ReasonOfConsultationResource toResourceFromEntity(ReasonOfConsultation entity) {
        return new ReasonOfConsultationResource(
                entity.getDescription(),
                entity.getSymptoms(),
                entity.getMedicalRecord().getId()
        );
    }
}
