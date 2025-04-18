package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record UpdateDoctorCommand(
        Long id,
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        Long professionalIdentificationNumber,
        String subSpecialty
)
{
}
