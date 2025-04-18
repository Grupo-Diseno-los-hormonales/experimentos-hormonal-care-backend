package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateDoctorCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateDoctorCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.*;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public class Doctor extends AuditableAbstractAggregateRoot<Doctor> {

    @Embedded
    private ProfessionalIdentificationNumber professionalIdentificationNumber;

    @Embedded
    private SubSpecialty subSpecialty;

    @Embedded
    private ProfileId profileId;

    @Embedded
    @Column(name = "doctorRecord_id")
    private  DoctorRecordId doctorRecordId;


    public Doctor() {
        
    }


    public Doctor(Long professionalIdentificationNumber, String subSpecialty, Long profileId) {
        this.professionalIdentificationNumber = new ProfessionalIdentificationNumber(professionalIdentificationNumber);
        this.subSpecialty = new SubSpecialty(subSpecialty);
        this.profileId = new ProfileId(profileId);
        this.doctorRecordId = new DoctorRecordId();
    }


    public Doctor(ProfessionalIdentificationNumber professionalIdentificationNumber, SubSpecialty subSpecialty, ProfileId profileId) {
        this.professionalIdentificationNumber = professionalIdentificationNumber;
        this.subSpecialty = subSpecialty;
        this.profileId = profileId;
        this.doctorRecordId = new DoctorRecordId();
    }


    public Doctor(CreateDoctorCommand command, ProfileId profileId){
        this.professionalIdentificationNumber = new ProfessionalIdentificationNumber(command.professionalIdentificationNumber());
        this.subSpecialty = new SubSpecialty(command.subSpecialty());
        this.profileId = profileId;
        this.doctorRecordId = new DoctorRecordId();
    }


    public void updateDoctor(UpdateDoctorCommand command, ProfileId profileId) {
        this.professionalIdentificationNumber = new ProfessionalIdentificationNumber(command.professionalIdentificationNumber());
        this.subSpecialty = new SubSpecialty(command.subSpecialty());
        this.profileId = profileId;
    }
    public Doctor updateInformation(Long appointmentFee, Long subscriptionId) {
        return this;
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public  String getDoctorRecordId() {
        return this.doctorRecordId.doctorRecordId();
    }


}
