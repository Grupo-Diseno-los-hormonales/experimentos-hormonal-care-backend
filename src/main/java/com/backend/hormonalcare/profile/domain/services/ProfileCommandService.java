package com.backend.hormonalcare.profile.domain.services;
import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileImageCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfilePhoneNumberCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfilePhoneNumberCommand command);
    Optional<Profile> handle(UpdateProfileImageCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);
}
