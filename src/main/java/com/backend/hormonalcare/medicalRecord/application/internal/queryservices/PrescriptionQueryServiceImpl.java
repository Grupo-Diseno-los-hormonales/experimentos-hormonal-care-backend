package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetAllPrescriptionsQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetPrescriptionByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetPrescriptionByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.PrescriptionQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionQueryServiceImpl implements PrescriptionQueryService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionQueryServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Optional<Prescription> handle(GetPrescriptionByIdQuery query) {
        return prescriptionRepository.findById(query.prescriptionId());
    }

    @Override
public List<Prescription> handle(GetPrescriptionByMedicalRecordIdQuery query) {
    return prescriptionRepository.findByMedicalRecordId(query.medicalRecordId());
}

    @Override
    public List<Prescription> handle(GetAllPrescriptionsQuery query) {
        return prescriptionRepository.findAll();
    }
}