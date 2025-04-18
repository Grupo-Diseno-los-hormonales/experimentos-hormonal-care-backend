package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.time.LocalDate;

public record UpdateMedicalAppointmentCommand(
        Long id,
        LocalDate eventDate,
        String startTime,
        String endTime,
        String title,
        String description,
        Long doctorId,
        Long patientId,
        String color

) {
}