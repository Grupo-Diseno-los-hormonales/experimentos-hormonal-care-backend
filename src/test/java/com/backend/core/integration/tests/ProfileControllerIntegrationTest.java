package com.backend.core.integration.tests;

import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import com.backend.hormonalcare.profile.domain.services.ProfileQueryService;
import com.backend.hormonalcare.profile.interfaces.rest.ProfileController;
import com.backend.hormonalcare.profile.interfaces.rest.resources.CreateProfileResource;
import com.backend.hormonalcare.profile.interfaces.rest.resources.ProfileResource;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.model.queries.GetAllProfilesQuery; // <-- IMPORTA ESTO
import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByUserIdQuery; // <-- IMPORTA ESTO
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.Optional;
import java.util.List; 
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
class ProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileCommandService profileCommandService;

    @MockBean
    private ProfileQueryService profileQueryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createProfile_ReturnsCreated_WhenProfileIsCreated() throws Exception {
        CreateProfileResource resource = new CreateProfileResource(
                "Juan", "Pérez", "M", "999999999", "img.jpg", new Date(), 1L
        );
        Mockito.when(profileCommandService.handle(Mockito.any(CreateProfileCommand.class)))
                .thenReturn(Optional.of(Mockito.mock(com.backend.hormonalcare.profile.domain.model.aggregates.Profile.class)));

        mockMvc.perform(post("/api/v1/profile/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated());
    }

    @Test
    void createProfile_ReturnsBadRequest_WhenProfileNotCreated() throws Exception {
        CreateProfileResource resource = new CreateProfileResource(
                "Juan", "Pérez", "M", "999999999", "img.jpg", new Date(), 1L
        );
        Mockito.when(profileCommandService.handle(Mockito.any(CreateProfileCommand.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/profile/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void doesProfileExistByUserId_ReturnsOk() throws Exception {
        Mockito.when(profileQueryService.doesProfileExist(any())).thenReturn(true);

        mockMvc.perform(get("/api/v1/profile/profile/userId/exists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getAllProfiles_ReturnsOk_WhenProfilesExist() throws Exception {
        Mockito.when(profileQueryService.handle(Mockito.any(GetAllProfilesQuery.class)))
               .thenReturn(List.of(Mockito.mock(com.backend.hormonalcare.profile.domain.model.aggregates.Profile.class)));

        mockMvc.perform(get("/api/v1/profile/profile"))
               .andExpect(status().isOk());
    }

    @Test
    void getProfileByUserId_ReturnsOk_WhenProfileExists() throws Exception {
        Mockito.when(profileQueryService.handle(Mockito.any(GetProfileByUserIdQuery.class)))
               .thenReturn(Optional.of(Mockito.mock(com.backend.hormonalcare.profile.domain.model.aggregates.Profile.class)));

        mockMvc.perform(get("/api/v1/profile/profile/userId/1"))
               .andExpect(status().isOk());
    }
}