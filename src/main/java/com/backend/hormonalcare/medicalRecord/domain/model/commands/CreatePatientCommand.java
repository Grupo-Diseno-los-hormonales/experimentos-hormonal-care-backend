package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record CreatePatientCommand(
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
