package com.backend.hormonalcare.medicalRecord.domain.model.commands;

import java.util.Date;

public record UpdatePatientCommand(
        Long id,
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        String typeOfBlood,
        String personalHistory,
        String familyHistory
) {
}
