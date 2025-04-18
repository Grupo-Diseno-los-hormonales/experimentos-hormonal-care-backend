package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;


import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.TypeMedicalExam;

import java.time.LocalDate;

public record MedicalExamResource(Long id, String url, TypeMedicalExam typeMedicalExam, LocalDate uploadDate, Long medicalRecordId) {
}
