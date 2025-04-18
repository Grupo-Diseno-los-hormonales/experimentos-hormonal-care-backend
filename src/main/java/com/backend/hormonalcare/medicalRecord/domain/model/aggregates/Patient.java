package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreatePatientCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateFamilyHistoryPatientCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePatientCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdatePersonalHistoryPatientCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.FamilyHistory;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PatientRecordId;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PersonalHistory;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfileId;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    private String typeOfBlood;

    @Embedded
    private PersonalHistory personalHistory;

    @Embedded
    private FamilyHistory familyHistory;

    @Embedded
    @Column(name = "patientRecord_id")
    private final PatientRecordId patientRecordId;

    @Embedded
    private ProfileId profileId;

    @Getter
    @Column(name = "doctor_id")
    private Long doctor;


    public Patient() {
        this.typeOfBlood = "";
        this.personalHistory = new PersonalHistory("");
        this.familyHistory = new FamilyHistory("");
        this.patientRecordId = new PatientRecordId();
        this.doctor = null;
    }
    public Patient(Long profileId, String typeOfBlood, String personalHistory, String familyHistory, Long doctor) {
        this.profileId = new ProfileId(profileId);
        this.typeOfBlood = typeOfBlood;
        this.personalHistory = new PersonalHistory(personalHistory);
        this.familyHistory = new FamilyHistory(familyHistory);
        this.patientRecordId = new PatientRecordId();
        this.doctor = doctor;
    }
    public Patient(ProfileId profileId, String typeOfBlood, PersonalHistory personalHistory, FamilyHistory familyHistory, Long doctor) {
        this.profileId = profileId;
        this.typeOfBlood = typeOfBlood;
        this.personalHistory = personalHistory;
        this.familyHistory = familyHistory;
        this.patientRecordId = new PatientRecordId();
        this.doctor = doctor;
    }

    public Patient(CreatePatientCommand command, ProfileId profileId, Long doctor) {
        this.typeOfBlood = command.typeOfBlood();
        this.personalHistory = new PersonalHistory(command.personalHistory());
        this.familyHistory = new FamilyHistory(command.familyHistory());
        this.profileId = profileId;
        this.patientRecordId = new PatientRecordId();
        this.doctor = doctor;
    }
    public void updatePatient(UpdatePatientCommand command, ProfileId profileId) {
        this.typeOfBlood = command.typeOfBlood();
        this.personalHistory = new PersonalHistory(command.personalHistory());
        this.familyHistory = new FamilyHistory(command.familyHistory());
        this.profileId = profileId;
    }
    public Patient updateDoctorId(Long doctor) {
        this.doctor = doctor;
        return this;
    }

    public Patient updatePersonalHistory(UpdatePersonalHistoryPatientCommand command) {
        this.personalHistory = new PersonalHistory(command.personalHistory());
        return this;
    }

    public Patient updateFamilyHistory(UpdateFamilyHistoryPatientCommand command) {
        this.familyHistory = new FamilyHistory(command.familyHistory());
        return this;
    }

    public String getPatientRecordId() {return this.patientRecordId.patientRecordId();}
    public Long getProfileId() {return this.profileId.profileId();}

    public Long getId() {
        return super.getId();
    }
}
