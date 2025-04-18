package com.backend.hormonalcare.medicalRecord.domain.model.queries;

import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfileId;

public record GetDoctorByProfileIdQuery(ProfileId profileId) {
}
