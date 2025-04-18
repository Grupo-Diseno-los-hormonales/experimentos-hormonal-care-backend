package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.PrescriptionResource;

public class PrescriptionResourceFromEntityAssembler {
    public static PrescriptionResource toResourceFromEntity(Prescription entity) {
        return new PrescriptionResource(
                entity.getId(),
                entity.getMedicalRecord().getId(),
                entity.getPrescriptionDate(),
                entity.getNotes()
        );
    }
}