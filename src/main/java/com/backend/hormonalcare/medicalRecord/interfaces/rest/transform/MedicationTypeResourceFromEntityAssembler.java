package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicationTypeResource;

public class MedicationTypeResourceFromEntityAssembler {
    public static MedicationTypeResource toResourceFromEntity(MedicationType entity) {
        return new MedicationTypeResource(entity.getName());
    }
}