
package com.backend.hormonalcare.medicalRecord.interfaces.rest.transform;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicationResource;

public class CreateMedicationCommandFromResourceAssembler {
    public static CreateMedicationCommand toCommandFromResource(CreateMedicationResource resource) {
        return new CreateMedicationCommand(
                resource.medicalRecordId(),
                resource.medicalTypeId(),
                resource.prescriptionId(),
                resource.name(),
                resource.amount(),
                resource.unitQ(),
                resource.value(),
                resource.unit(),
                resource.timesPerDay(),
                resource.timePeriod());
    }
}