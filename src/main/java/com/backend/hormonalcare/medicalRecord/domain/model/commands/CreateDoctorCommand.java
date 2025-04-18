package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record CreateDoctorCommand(
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        Long userId,
        Long professionalIdentificationNumber,
        String subSpecialty
) {
}

