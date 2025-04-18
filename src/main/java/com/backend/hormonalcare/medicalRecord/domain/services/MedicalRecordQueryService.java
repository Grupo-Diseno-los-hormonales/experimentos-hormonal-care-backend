package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalRecordByIdQuery;

import java.util.Optional;

public interface MedicalRecordQueryService {
    Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query);
}
