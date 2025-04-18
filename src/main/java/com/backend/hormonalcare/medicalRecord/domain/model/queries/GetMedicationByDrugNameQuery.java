package com.backend.hormonalcare.medicalRecord.domain.model.queries;

import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.DrugName;

public record GetMedicationByDrugNameQuery(DrugName drugName) {
}
