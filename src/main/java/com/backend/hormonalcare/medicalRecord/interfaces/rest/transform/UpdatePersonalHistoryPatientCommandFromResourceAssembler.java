package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePersonalHistoryPatientCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdatePersonalHistoryPatientResource;

public class UpdatePersonalHistoryPatientCommandFromResourceAssembler {
    public static UpdatePersonalHistoryPatientCommand toCommandFromResource(Long id, UpdatePersonalHistoryPatientResource resource) {
        return new UpdatePersonalHistoryPatientCommand(
                id,
                resource.personalHistory()
        );
    }
}
