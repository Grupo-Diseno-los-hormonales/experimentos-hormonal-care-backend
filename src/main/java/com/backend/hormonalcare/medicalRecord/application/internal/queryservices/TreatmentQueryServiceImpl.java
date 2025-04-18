package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentQueryServiceImpl implements TreatmentQueryService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {
        return treatmentRepository.findById(query.id());
    }

    @Override
    public List<Treatment> handle(GetTreatmentByMedicalRecordIdQuery query) {
        return treatmentRepository.findByMedicalRecordId(query.medicalRecordId());
    }
}
