package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;

import java.util.Optional;

public interface MedicationTypeCommandService {
    Optional<MedicationType> handle(CreateMedicationTypeCommand command);
    Optional<MedicationType> handle(UpdateMedicationTypeCommand command);
}