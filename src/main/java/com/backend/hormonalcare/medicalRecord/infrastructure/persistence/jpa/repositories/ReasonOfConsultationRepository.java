package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonOfConsultationRepository extends JpaRepository<ReasonOfConsultation, Long> {
    List<ReasonOfConsultation> findByMedicalRecordId(Long medicalRecordId);
}
