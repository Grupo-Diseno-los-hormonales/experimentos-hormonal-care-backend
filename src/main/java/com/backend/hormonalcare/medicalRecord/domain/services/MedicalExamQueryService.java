package com.backend.hormonalcare.medicalRecord.domain.services;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByMedicalRecordIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalExamQueryService {
    Optional<MedicalExam> handle(GetMedicalExamByIdQuery query);
    List<MedicalExam> handle(GetMedicalExamByMedicalRecordIdQuery query);
}
