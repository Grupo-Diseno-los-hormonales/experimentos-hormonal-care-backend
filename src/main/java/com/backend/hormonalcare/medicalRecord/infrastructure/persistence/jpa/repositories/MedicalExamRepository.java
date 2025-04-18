package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;


import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalExamRepository extends JpaRepository<MedicalExam, Long> {
    List<MedicalExam> findByMedicalRecordId(Long medicalRecordId);
}
