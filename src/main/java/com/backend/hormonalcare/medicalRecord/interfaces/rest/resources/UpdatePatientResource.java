package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

import java.util.Date;

public record UpdatePatientResource(
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        String typeOfBlood,
        String personalHistory,
        String familyHistory
) { }
