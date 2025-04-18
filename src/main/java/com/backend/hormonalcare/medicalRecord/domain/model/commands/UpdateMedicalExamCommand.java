package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.TypeMedicalExam;

import java.time.LocalDate;

public record UpdateMedicalExamCommand(
        Long id,
        String url,
        TypeMedicalExam typeMedicalExam,
        LocalDate uploadDate,
        Long medicalRecordId) {
}
