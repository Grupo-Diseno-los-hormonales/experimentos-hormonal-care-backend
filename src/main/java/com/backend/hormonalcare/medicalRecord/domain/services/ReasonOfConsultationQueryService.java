package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByMedicalRecordIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReasonOfConsultationQueryService {
    Optional<ReasonOfConsultation> handle(GetReasonOfConsultationByIdQuery query);
    List<ReasonOfConsultation> handle(GetReasonOfConsultationByMedicalRecordIdQuery query);
}
