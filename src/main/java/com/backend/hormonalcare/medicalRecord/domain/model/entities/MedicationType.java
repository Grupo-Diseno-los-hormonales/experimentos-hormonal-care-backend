package com.backend.hormonalcare.medicalRecord.domain.model.entities;


import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationTypeCommand;
import com.backend.hormonalcare.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
public class MedicationType extends AuditableModel {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String typeName;

    public MedicationType() {
    }
    public MedicationType(CreateMedicationTypeCommand command) {
        this.typeName = command.typeName();
    }

    public MedicationType update(UpdateMedicationTypeCommand command){
    this.typeName =command.typeName();
    return this;
    }

    public String getName() {
        return typeName;
    }
}