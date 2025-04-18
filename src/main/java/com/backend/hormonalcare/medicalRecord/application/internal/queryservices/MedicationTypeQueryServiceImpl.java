package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetAllMedicationTypesQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicationTypeByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicationTypeQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationTypeQueryServiceImpl implements MedicationTypeQueryService {
    private final MedicationTypeRepository medicationTypeRepository;

    public MedicationTypeQueryServiceImpl(MedicationTypeRepository medicationTypeRepository) {
        this.medicationTypeRepository = medicationTypeRepository;
    }

    @Override
    public Optional<MedicationType> handle(GetMedicationTypeByIdQuery query) {
        return medicationTypeRepository.findById(query.medicationTypeId());
    }

    @Override
    public List<MedicationType> handle(GetAllMedicationTypesQuery query) {
        return medicationTypeRepository.findAll();
    }
}