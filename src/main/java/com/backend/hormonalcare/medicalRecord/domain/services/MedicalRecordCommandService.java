package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalRecordCommand;

import java.util.Optional;

public interface MedicalRecordCommandService {
    Optional<MedicalRecord> handle(CreateMedicalRecordCommand command);
}
