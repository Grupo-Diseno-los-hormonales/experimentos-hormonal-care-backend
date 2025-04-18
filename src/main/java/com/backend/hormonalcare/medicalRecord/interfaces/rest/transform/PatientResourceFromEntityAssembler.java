package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.PatientResource;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient entity){
        return new PatientResource(
                entity.getId(),
                entity.getTypeOfBlood(),
                entity.getPersonalHistory().personalHistory(),
                entity.getFamilyHistory().familyHistory(),
                entity.getPatientRecordId(),
                entity.getProfileId(),
                entity.getDoctor());
    }
}
