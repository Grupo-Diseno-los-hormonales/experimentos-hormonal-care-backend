package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicalRecordResource;

public class MedicalRecordResourceFromEntityAssembler {
    public static MedicalRecordResource toResourceFromEntity(MedicalRecord entity){
        return new MedicalRecordResource(
                entity.getId(),
                entity.getPatientId()
        );
    }
}
