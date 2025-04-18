package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{

}
