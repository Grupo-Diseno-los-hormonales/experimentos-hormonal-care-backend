package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class MedicalRecord extends AuditableAbstractAggregateRoot<MedicalRecord> {

    @Getter
    private Long patientId;

    public MedicalRecord() {
        // Default constructor for JPA
    }

    public MedicalRecord(Long patientId) {
        this.patientId = patientId;
    }
}