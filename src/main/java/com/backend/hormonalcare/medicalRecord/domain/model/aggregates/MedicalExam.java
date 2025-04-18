package com.backend.hormonalcare.medicalRecord.domain.model.aggregates;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.TypeMedicalExam;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class MedicalExam extends AuditableAbstractAggregateRoot<MedicalExam> {

    private String url;

    private LocalDate uploadDate;

    @Enumerated(EnumType.STRING)
    private TypeMedicalExam typeMedicalExam;


    @Getter
    @ManyToOne
    @JoinColumn(name = "medicalRecord_id",referencedColumnName = "id" )
    private MedicalRecord medicalRecord;


    public MedicalExam() {
    }

    public MedicalExam(String url, TypeMedicalExam typeMedicalExam, LocalDate uploadDate, MedicalRecord medicalRecord) {
        this.url = url;
        this.typeMedicalExam = typeMedicalExam;
        this.medicalRecord = medicalRecord;
        this.uploadDate = uploadDate;
    }

    public MedicalExam(CreateMedicalExamCommand command, TypeMedicalExam typeMedicalExam, MedicalRecord medicalRecord) {
        this.url = command.url();
        this.uploadDate = command.uploadDate();
        this.typeMedicalExam = typeMedicalExam;
        this.medicalRecord = medicalRecord;
    }

    public MedicalExam updateInformation(String url, TypeMedicalExam typeMedicalExam, MedicalRecord medicalRecord) {
        this.url = url;
        this.typeMedicalExam = typeMedicalExam;
        this.medicalRecord = medicalRecord;
        return this;
    }
}
