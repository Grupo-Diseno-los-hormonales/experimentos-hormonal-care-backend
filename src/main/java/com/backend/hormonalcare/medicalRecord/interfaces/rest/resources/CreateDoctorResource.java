package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.util.Date;

public record CreateDoctorResource(
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
