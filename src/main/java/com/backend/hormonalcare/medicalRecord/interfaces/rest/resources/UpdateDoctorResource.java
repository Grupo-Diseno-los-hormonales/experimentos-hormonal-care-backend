package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.util.Date;

public record UpdateDoctorResource(
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        Long professionalIdentificationNumber,
        String subSpecialty
) {
}
