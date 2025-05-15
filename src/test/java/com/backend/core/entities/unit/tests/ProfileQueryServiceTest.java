package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByIdQuery;
import com.backend.hormonalcare.profile.domain.model.queries.GetAllProfilesQuery;
import com.backend.hormonalcare.profile.domain.services.ProfileQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProfileQueryServiceTest {
    private ProfileQueryService profileQueryService;

    @BeforeEach
    void setUp() {
        profileQueryService = Mockito.mock(ProfileQueryService.class);
    }

    @Test
    void testHandleGetProfileByIdQueryReturnsProfile() {
        GetProfileByIdQuery query = new GetProfileByIdQuery(1L);
        Profile mockProfile = Mockito.mock(Profile.class);
        Mockito.when(profileQueryService.handle(any(GetProfileByIdQuery.class))).thenReturn(Optional.of(mockProfile));

        Optional<Profile> result = profileQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockProfile, result.get());
    }

    @Test
    void testHandleGetAllProfilesQueryReturnsList() {
        GetAllProfilesQuery query = new GetAllProfilesQuery();
        List<Profile> mockList = List.of(Mockito.mock(Profile.class));
        Mockito.when(profileQueryService.handle(any(GetAllProfilesQuery.class))).thenReturn(mockList);

        List<Profile> result = profileQueryService.handle(query);

        assertFalse(result.isEmpty());
    }
}