package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.DeleteMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalExamCommand;

import java.util.Optional;

public interface MedicalExamCommandService {
    Optional<MedicalExam> handle(CreateMedicalExamCommand command);
    Optional<MedicalExam> handle(UpdateMedicalExamCommand command);
    void handle(DeleteMedicalExamCommand command);
}
