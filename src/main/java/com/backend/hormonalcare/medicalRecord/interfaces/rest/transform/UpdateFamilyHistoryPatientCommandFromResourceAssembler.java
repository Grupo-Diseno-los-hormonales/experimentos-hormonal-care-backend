package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateFamilyHistoryPatientCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateFamilyHistoryPatientResource;

public class UpdateFamilyHistoryPatientCommandFromResourceAssembler {
    public static UpdateFamilyHistoryPatientCommand toCommandFromResource(Long id, UpdateFamilyHistoryPatientResource resource) {
        return new UpdateFamilyHistoryPatientCommand(
                id,
                resource.familyHistory()
        );
    }
}
