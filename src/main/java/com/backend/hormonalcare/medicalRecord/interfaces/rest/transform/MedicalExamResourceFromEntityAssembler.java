package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;


import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicalExamResource;

public class MedicalExamResourceFromEntityAssembler {
    public static MedicalExamResource toResourceFromEntity(MedicalExam entity) {
        return new MedicalExamResource(
                entity.getId(),
                entity.getUrl(),
                entity.getTypeMedicalExam(),
                entity.getUploadDate(),
                entity.getMedicalRecord().getId()
        );
    }
}
