package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateReasonOfConsultationCommand;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ReasonOfConsultation extends AuditableAbstractAggregateRoot<ReasonOfConsultation> {
    private String description;

    private String symptoms;

    @Getter
    @ManyToOne
    @JoinColumn(name = "medicalRecord_id",referencedColumnName = "id" )
    private MedicalRecord medicalRecord;


    public ReasonOfConsultation() {
        this.description = "";
        this.symptoms = "";
    }

    public ReasonOfConsultation(String description, String symptoms, Long medicalRecord) {
        this.description = description;
        this.symptoms = symptoms;
    }

    public ReasonOfConsultation (CreateReasonOfConsultationCommand command, MedicalRecord medicalRecord) {
        this.description = command.description();
        this.symptoms = command.symptoms();
        this.medicalRecord = medicalRecord;
    }

    public ReasonOfConsultation updateInformation (String description, String symptoms, MedicalRecord medicalRecord) {
        this.description = description;
        this.symptoms = symptoms;
        this.medicalRecord = medicalRecord;
        return this;
    }

}
