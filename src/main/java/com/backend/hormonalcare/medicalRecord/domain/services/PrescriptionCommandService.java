package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePrescriptionCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;

import java.util.Optional;

public interface PrescriptionCommandService {
    Optional<Prescription> handle(CreatePrescriptionCommand command);
    Optional<Prescription> handle(UpdatePrescriptionCommand command);
}