package com.backend.hormonalcare.profile.application.internal.commandservices;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileImageCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfilePhoneNumberCommand;
import com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber;
import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import com.backend.hormonalcare.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        User user = userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User with id " + command.userId() + " does not exist"));
        PhoneNumber phoneNumber = new PhoneNumber(command.phoneNumber());
        if (profileRepository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Profile with phone number " + command.phoneNumber() + " already exists");
        }
        // Check if the user already has a profile
        if (profileRepository.existsByUserId(command.userId())) {
            throw new IllegalArgumentException("User with id " + command.userId() + " already has a profile");
        }
        var profile = new Profile(command, user);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public Optional<Profile> handle(UpdateProfilePhoneNumberCommand command) {
        var id = command.id();
        if (!profileRepository.existsById(id))
            throw new IllegalArgumentException("Profile with id " + id + " does not exist");
        var result = profileRepository.findById(id);
        var profileToUpdate = result.get();
        try {
            PhoneNumber phoneNumber = new PhoneNumber(command.phoneNumber());
            var updateProfile = profileRepository.save(profileToUpdate.upsetPhoneNumber(phoneNumber));
            return Optional.of(updateProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating profile with id " + id);
        }
    }

    @Override
    public Optional<Profile> handle(UpdateProfileImageCommand command) {
        var id = command.id();
        if (!profileRepository.existsById(id))
            throw new IllegalArgumentException("Profile with id "+ id +" does not exist");
        var result = profileRepository.findById(id);
        var profileToUpdate = result.get();
        try {
            var updateProfile = profileRepository.save(profileToUpdate.upsetImage(command.image()));
            return Optional.of(updateProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating profile with id "+ id);
        }
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var id = command.id();
        if (!profileRepository.existsById(id))
            throw new IllegalArgumentException("Profile with id "+ id +" does not exist");
        var result = profileRepository.findById(id);
        var profileToUpdate = result.get();
        try {
            var updateProfile = profileRepository.save(profileToUpdate.updateProfile(command.firstName(), command.lastName(), command.gender(), command.phoneNumber(), command.image(), command.birthday()));
            return Optional.of(updateProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating profile with id "+ id);
        }
    }
}
