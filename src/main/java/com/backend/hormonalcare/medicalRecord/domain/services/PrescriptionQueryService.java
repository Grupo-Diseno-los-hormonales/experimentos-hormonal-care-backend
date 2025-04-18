package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PrescriptionQueryService {
    List<Prescription> handle(GetAllPrescriptionsQuery query);
    Optional<Prescription> handle(GetPrescriptionByIdQuery query);
    List<Prescription> handle(GetPrescriptionByMedicalRecordIdQuery query);
}