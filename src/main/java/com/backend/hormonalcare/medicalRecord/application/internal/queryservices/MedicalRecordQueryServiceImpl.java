package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalRecordByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordQueryServiceImpl implements MedicalRecordQueryService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordQueryServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query) {
        return medicalRecordRepository.findById(query.id());
    }
}
