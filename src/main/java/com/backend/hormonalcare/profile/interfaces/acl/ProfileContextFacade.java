package com.backend.hormonalcare.profile.interfaces.acl;

import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByPhoneNumberQuery;
import com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber;
import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import com.backend.hormonalcare.profile.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;

    }

    public Long createProfile(String firstName, String lastName, String gender, String phoneNumber, String image, Date birthday, Long userId){
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, gender, phoneNumber, image, birthday, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    public boolean updateProfile(Long profileId, String firstName, String lastName, String gender, String phoneNumber, String image, Date birthday) {
        var updateProfileCommand = new UpdateProfileCommand(profileId, firstName, lastName, gender, phoneNumber, image, birthday);
        var updatedProfile = profileCommandService.handle(updateProfileCommand);
        return updatedProfile.isPresent();
    }

    public Long fetchProfileIdByPhoneNumber(String phoneNumber) {
        var getProfileByPhoneNumberQuery = new GetProfileByPhoneNumberQuery(new PhoneNumber(phoneNumber));
        var profile = profileQueryService.handle(getProfileByPhoneNumberQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }
}
