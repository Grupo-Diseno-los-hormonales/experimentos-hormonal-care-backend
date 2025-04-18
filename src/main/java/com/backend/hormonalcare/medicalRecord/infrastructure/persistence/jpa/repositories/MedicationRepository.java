package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Medication;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.DrugName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> findByDrugName(DrugName drugName);
   Optional<Medication> findByPrescriptionId(Long prescriptionId);
   Optional<Medication> findByMedicationTypeId(Long typeId);
    Optional<Medication> findByMedicalRecordId(Long medicalRecordId);
}