package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByMedicalRecordIdQuery;

import java.util.List;
import java.util.Optional;

public interface TreatmentQueryService {
    Optional<Treatment> handle(GetTreatmentByIdQuery query);
    List<Treatment> handle(GetTreatmentByMedicalRecordIdQuery query);
}
