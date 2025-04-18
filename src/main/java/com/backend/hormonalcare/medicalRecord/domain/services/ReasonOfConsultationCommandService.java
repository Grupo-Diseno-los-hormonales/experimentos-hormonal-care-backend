package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateReasonOfConsultationCommand;

import java.util.Optional;

public interface ReasonOfConsultationCommandService {
    Optional<ReasonOfConsultation> handle(CreateReasonOfConsultationCommand command);
    Optional<ReasonOfConsultation> handle(UpdateReasonOfConsultationCommand command);
}
