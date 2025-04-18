package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateTreatmentCommand;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;


@Getter
@Entity
public class Treatment extends AuditableAbstractAggregateRoot<Treatment> {
    private String description;


    @Getter
    @ManyToOne
    @JoinColumn(name = "medicalRecord_id",referencedColumnName = "id" )
    private MedicalRecord medicalRecord;

    public Treatment(){
    }

    public Treatment(String description, Long medicalRecord){
        this.description = description;

    }

    public Treatment(CreateTreatmentCommand command, MedicalRecord medicalRecord){

        this.description = command.description();
        this.medicalRecord = medicalRecord;
    }

    public Treatment updateInformation (String description, MedicalRecord medicalRecord){
        this.description = description;
        this.medicalRecord = medicalRecord;
        return this;
    }
}
