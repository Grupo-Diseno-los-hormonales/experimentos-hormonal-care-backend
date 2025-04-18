package com.backend.hormonalcare.profile.domain.model.commands;

public record UpdateProfilePhoneNumberCommand(
        Long id,
        String phoneNumber
) {
}
