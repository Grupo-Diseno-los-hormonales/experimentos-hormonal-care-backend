package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;
import com.backend.hormonalcare.medicalRecord.domain.model.entities.Prescription;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.*;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
@Getter
@Entity
public class Medication extends AuditableAbstractAggregateRoot<Medication> {

    @Getter
    @ManyToOne
    @JoinColumn(name = "medicalRecord_id",referencedColumnName = "id" )
    private MedicalRecord medicalRecord;

    @Getter
    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private Prescription prescription;

    @Getter
    @ManyToOne
    @JoinColumn(name = "medication_type_id", referencedColumnName = "id")
    private MedicationType medicationType;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")) })
    private DrugName drugName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
        @AttributeOverride(name = "unitQ", column = @Column(name = "unitQ")) })
    private Quantity quantity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "value_concentration")),
            @AttributeOverride(name = "unit", column = @Column(name = "unit_concentration")) })
    private Concentration concentration;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timesPerDay", column = @Column(name = "timesPerDay"))})
    private Frequency frequency;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "timePeriod", column = @Column(name = "timePeriod"))})
    private Duration duration;

    public Medication() {
    }

    public Medication(CreateMedicationCommand command, MedicalRecord medicalRecord, Prescription prescription, MedicationType medicationType) {
        this.drugName = new DrugName(command.name());
        this.quantity = new Quantity(command.amount(), command.unitQ());
        this.concentration = new Concentration(command.value(), command.unit());
        this.frequency = new Frequency(command.timesPerDay());
        this.duration = new Duration(command.timePeriod());
        this.prescription = prescription;
        this.medicationType = medicationType;
        this.medicalRecord = medicalRecord;
    }

    public Medication( MedicalRecord medicalRecordId, Prescription prescriptionId, MedicationType medicationTypeId, String name, int amount, String unitQ, int value_concentration, String unit_concentration, int timesPerDay, String timePeriod) {
       this.medicalRecord= medicalRecordId;
        this.prescription= prescriptionId;
        this.medicationType= medicationTypeId;
        this.drugName = new DrugName(name);
        this.quantity = new Quantity(amount, unitQ);
        this.concentration = new Concentration(value_concentration, unit_concentration);
        this.frequency = new Frequency(timesPerDay);
        this.duration = new Duration(timePeriod);
    }


      public String getDrugName() {return drugName.name();}
      public String getQuantity() {return quantity.amount() + " " + quantity.unitQ();}
      public String getConcentration() {return concentration.value_concentration() + " " + concentration.unit_concentration();}
      public String getFrequency() {return frequency.timesPerDay() + " times per day";}
      public String getDuration() {return duration.timePeriod();}

    public Medication update(UpdateMedicationCommand command, MedicalRecord medicalRecord, Prescription prescription, MedicationType medicationType) {
        this.drugName = new DrugName(command.name());
        this.quantity = new Quantity(command.amount(), command.unitQ());
        this.concentration = new Concentration(command.value(), command.unit());
        this.frequency = new Frequency(command.timesPerDay());
        this.duration = new Duration(command.timePeriod());
        this.prescription = prescription;
        this.medicationType = medicationType;
        this.medicalRecord = medicalRecord;
        return this;
    }

    public void setPrescription(Prescription prescription) {
    }

    public void setMedicationType(MedicationType medicationType) {
    }
}




