package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.TreatmentResource;

public class TreatmentResourceFromEntityAssembler {
    public static TreatmentResource toResourceFromEntity(Treatment entity){
        return new TreatmentResource(entity.getDescription(), entity.getMedicalRecord().getId());
    }
}
