package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateMedicalAppointmentResource(
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
