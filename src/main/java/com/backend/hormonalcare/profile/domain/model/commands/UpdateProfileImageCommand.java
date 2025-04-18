package com.backend.hormonalcare.profile.domain.model.commands;

public record UpdateProfileImageCommand(
        Long id, String image)
{
}
