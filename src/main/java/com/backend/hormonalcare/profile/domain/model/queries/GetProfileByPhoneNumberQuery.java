package com.backend.hormonalcare.profile.domain.model.queries;

import com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber;

public record GetProfileByPhoneNumberQuery(PhoneNumber phoneNumber) {
}
