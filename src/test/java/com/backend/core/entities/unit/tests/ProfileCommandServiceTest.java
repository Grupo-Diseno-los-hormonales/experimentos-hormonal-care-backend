package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileImageCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfilePhoneNumberCommand;
import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProfileCommandServiceTest {
    private ProfileCommandService profileCommandService;

    @BeforeEach
    void setUp() {
        profileCommandService = Mockito.mock(ProfileCommandService.class);
    }

    @Test
    void testHandleCreateProfileCommandReturnsProfile() {
        CreateProfileCommand command = Mockito.mock(CreateProfileCommand.class);
        Profile mockProfile = Mockito.mock(Profile.class);
        Mockito.when(profileCommandService.handle(any(CreateProfileCommand.class))).thenReturn(Optional.of(mockProfile));

        Optional<Profile> result = profileCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockProfile, result.get());
    }
}