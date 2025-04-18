package com.backend.hormonalcare.medicalRecord.application.internal.outboundservices.acl;

import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfileId;
import com.backend.hormonalcare.profile.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class ExternalProfileService {
    private final ProfileContextFacade profilesContextFacade;

    public ExternalProfileService(ProfileContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }
    public Optional<ProfileId> fetchProfileIdByPhoneNumber(String phoneNumber) {
        var profileId = profilesContextFacade.fetchProfileIdByPhoneNumber(phoneNumber);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }
    public Optional<ProfileId> createProfile(
            String firstName,
            String lastName,
            String gender,
            String phoneNumber,
            String image,
            Date birthday,
            Long userId) {
        var profileId = profilesContextFacade.createProfile(
                firstName,
                lastName,
                gender,
                phoneNumber,
                image,
                birthday,
                userId);
        if (profileId == 0L) {
            return Optional.empty();
        }
        return Optional.of(new ProfileId(profileId));
    }

    public boolean updateProfile(Long profileId, String firstName, String lastName, String gender, String phoneNumber, String image, Date birthday) {
        return profilesContextFacade.updateProfile(profileId, firstName, lastName, gender, phoneNumber, image, birthday);
    }

}
