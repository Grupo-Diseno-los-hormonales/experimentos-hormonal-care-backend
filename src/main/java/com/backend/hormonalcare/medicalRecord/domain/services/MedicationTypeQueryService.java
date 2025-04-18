package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface MedicationTypeQueryService {
    List<MedicationType> handle(GetAllMedicationTypesQuery query);
    Optional<MedicationType> handle(GetMedicationTypeByIdQuery query);
}