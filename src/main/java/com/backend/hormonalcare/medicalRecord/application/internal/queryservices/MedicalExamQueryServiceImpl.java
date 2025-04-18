package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;


import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicalExamQueryServiceImpl implements MedicalExamQueryService {

    private final MedicalExamRepository medicalExamRepository;

    public MedicalExamQueryServiceImpl(MedicalExamRepository medicalExamRepository) {
        this.medicalExamRepository = medicalExamRepository;
    }

    @Override
    public Optional<MedicalExam> handle(GetMedicalExamByIdQuery query) {
        return medicalExamRepository.findById(query.id());
    }

    @Override
    public List<MedicalExam> handle(GetMedicalExamByMedicalRecordIdQuery query) {
        return medicalExamRepository.findByMedicalRecordId(query.medicalRecordId());
    }

}

