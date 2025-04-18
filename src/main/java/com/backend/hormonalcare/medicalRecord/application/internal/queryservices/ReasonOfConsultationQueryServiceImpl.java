package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.ReasonOfConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReasonOfConsultationQueryServiceImpl implements ReasonOfConsultationQueryService {
    private final ReasonOfConsultationRepository reasonOfConsultationRepository;

    public ReasonOfConsultationQueryServiceImpl(ReasonOfConsultationRepository reasonOfConsultationRepository) {
        this.reasonOfConsultationRepository = reasonOfConsultationRepository;
    }

    @Override
    public Optional<ReasonOfConsultation> handle(GetReasonOfConsultationByIdQuery query) {
        return reasonOfConsultationRepository.findById(query.id());
    }


    @Override
    public List<ReasonOfConsultation> handle(GetReasonOfConsultationByMedicalRecordIdQuery query) {
        return reasonOfConsultationRepository.findByMedicalRecordId(query.medicalRecordId());
    }
}
