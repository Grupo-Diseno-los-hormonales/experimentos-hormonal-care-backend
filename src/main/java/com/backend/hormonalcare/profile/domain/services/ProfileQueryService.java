package com.backend.hormonalcare.profile.domain.services;

import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByNameQuery query);
    Optional<Profile> handle(GetProfileByPhoneNumberQuery query);
    boolean doesProfileExist(GetProfileByUserIdQuery query);
    Optional<Profile> handle(GetProfileByUserIdQuery query);
}
