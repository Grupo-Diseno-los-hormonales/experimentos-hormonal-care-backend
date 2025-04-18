package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateTreatmentCommand;

import java.util.Optional;

public interface TreatmentCommandService {
    Optional<Treatment> handle(CreateTreatmentCommand command);
    Optional<Treatment> handle(UpdateTreatmentCommand command);
}
