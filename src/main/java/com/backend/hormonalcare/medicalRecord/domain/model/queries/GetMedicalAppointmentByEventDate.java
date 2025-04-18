package com.backend.hormonalcare.medicalRecord.domain.model.queries;

import java.time.LocalDate;

public record GetMedicalAppointmentByEventDate(LocalDate date) {
}
