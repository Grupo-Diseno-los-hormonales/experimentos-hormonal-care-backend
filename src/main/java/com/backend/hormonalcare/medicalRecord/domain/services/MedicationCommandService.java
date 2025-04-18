package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationCommand;

import java.util.Optional;

public interface MedicationCommandService {
    Optional<Medication> handle(CreateMedicationCommand command);
    Optional<Medication> handle(UpdateMedicationCommand command);

}
