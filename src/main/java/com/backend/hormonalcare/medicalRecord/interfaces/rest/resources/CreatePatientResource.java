package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.util.Date;

public record CreatePatientResource(
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        Long userId,
        String typeOfBlood,
        String personalHistory,
        String familyHistory,
        Long doctorId) {
}
