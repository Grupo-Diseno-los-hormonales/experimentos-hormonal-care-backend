package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateMedicalExamResource;

public class UpdateMedicalExamCommandFromResourceAssembler {
    public static UpdateMedicalExamCommand toCommandFromResource(Long id, UpdateMedicalExamResource resource) {
        return new UpdateMedicalExamCommand(
                id,
                resource.url(),
                resource.typeMedicalExam(),
                resource.uploadDate(),
                resource.medicalRecordId()
        );
    }
}

