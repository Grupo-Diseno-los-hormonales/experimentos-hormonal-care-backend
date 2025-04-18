package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.time.LocalDate;

public record CreateMedicalAppointmentCommand(
        LocalDate eventDate,
        String startTime,
        String endTime,
        String title,
        String description,
        Long doctorId,
        Long patientId,
        String color
) {}

