package com.backend.core.integration.tests;

import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import com.backend.hormonalcare.profile.domain.services.ProfileQueryService;
import com.backend.hormonalcare.profile.interfaces.rest.ProfileController;
import com.backend.hormonalcare.profile.interfaces.rest.resources.CreateProfileResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ProfileControllerIntegrationTest {

    private ProfileCommandService profileCommandService;
    private ProfileQueryService profileQueryService;
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        profileCommandService = Mockito.mock(ProfileCommandService.class);
        profileQueryService = Mockito.mock(ProfileQueryService.class);
        profileController = new ProfileController(profileCommandService, profileQueryService);
    }

    /**
     * Test que verifica que al crear un perfil correctamente,
     * el controlador retorna HTTP 201 y el body contiene el nombre completo esperado.
     */
    @Test
    void createProfile_ReturnsCreated_WhenProfileIsCreated() {
        // Se mockean todos los value objects requeridos por el assembler
        Profile mockProfile = Mockito.mock(Profile.class);
        Mockito.when(mockProfile.getId()).thenReturn(1L);
        var mockName = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.PersonName.class);
        Mockito.when(mockName.getFullName()).thenReturn("Juan Pérez");
        Mockito.when(mockProfile.getName()).thenReturn(mockName);
        var mockGender = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.Gender.class);
        Mockito.when(mockGender.getGender()).thenReturn("M");
        Mockito.when(mockProfile.getGender()).thenReturn(mockGender);
        var mockPhone = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber.class);
        Mockito.when(mockPhone.getPhoneNumber()).thenReturn("999999999");
        Mockito.when(mockProfile.getPhoneNumber()).thenReturn(mockPhone);
        Mockito.when(mockProfile.getImage()).thenReturn("img.jpg");
        var mockBirthday = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.Birthday.class);
        Mockito.when(mockBirthday.birthday()).thenReturn(new Date());
        Mockito.when(mockProfile.getBirthday()).thenReturn(mockBirthday);
        var mockUser = Mockito.mock(com.backend.hormonalcare.iam.domain.model.aggregates.User.class);
        Mockito.when(mockUser.getId()).thenReturn(1L);
        Mockito.when(mockProfile.getUser()).thenReturn(mockUser);

        // Especifica el tipo para evitar ambigüedad
        Mockito.when(profileCommandService.handle(Mockito.any(CreateProfileCommand.class))).thenReturn(Optional.of(mockProfile));

        CreateProfileResource resource = new CreateProfileResource(
                "Juan", "Pérez", "M", "999999999", "img.jpg", new Date(), 1L
        );

        var response = profileController.createProfile(resource);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan Pérez", response.getBody().fullName());
    }

    /**
     * Test que verifica que si no se puede crear el perfil,
     * el controlador retorna HTTP 400 y el body es null.
     */
    @Test
    void createProfile_ReturnsBadRequest_WhenProfileNotCreated() {
        // Especifica el tipo para evitar ambigüedad
        Mockito.when(profileCommandService.handle(Mockito.any(CreateProfileCommand.class))).thenReturn(Optional.empty());

        CreateProfileResource resource = new CreateProfileResource(
                "Juan", "Pérez", "M", "999999999", "img.jpg", new Date(), 1L
        );

        var response = profileController.createProfile(resource);

        assertEquals(400, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    /**
     * Test que verifica que la existencia de un perfil por userId retorna HTTP 200 y true.
     */
    @Test
    void doesProfileExistByUserId_ReturnsOk() {
        Mockito.when(profileQueryService.doesProfileExist(any(GetProfileByUserIdQuery.class))).thenReturn(true);

        var response = profileController.doesProfileExistByUserId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody());
    }

    /**
     * Test que verifica que al buscar un perfil por userId existente,
     * el controlador retorna HTTP 200 y el body contiene el nombre completo esperado.
     */
    @Test
    void getProfileByUserId_ReturnsOk_WhenProfileExists() {
        Profile mockProfile = Mockito.mock(Profile.class);
        // Se mockean los value objects requeridos por el assembler
        Mockito.when(mockProfile.getId()).thenReturn(1L);
        var mockName = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.PersonName.class);
        Mockito.when(mockName.getFullName()).thenReturn("Juan Pérez");
        Mockito.when(mockProfile.getName()).thenReturn(mockName);
        var mockGender = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.Gender.class);
        Mockito.when(mockGender.getGender()).thenReturn("M");
        Mockito.when(mockProfile.getGender()).thenReturn(mockGender);
        var mockPhone = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber.class);
        Mockito.when(mockPhone.getPhoneNumber()).thenReturn("999999999");
        Mockito.when(mockProfile.getPhoneNumber()).thenReturn(mockPhone);
        Mockito.when(mockProfile.getImage()).thenReturn("img.jpg");
        var mockBirthday = Mockito.mock(com.backend.hormonalcare.profile.domain.model.valueobjects.Birthday.class);
        Mockito.when(mockBirthday.birthday()).thenReturn(new Date());
        Mockito.when(mockProfile.getBirthday()).thenReturn(mockBirthday);
        var mockUser = Mockito.mock(com.backend.hormonalcare.iam.domain.model.aggregates.User.class);
        Mockito.when(mockUser.getId()).thenReturn(1L);
        Mockito.when(mockProfile.getUser()).thenReturn(mockUser);

        Mockito.when(profileQueryService.handle(any(GetProfileByUserIdQuery.class)))
                .thenReturn(Optional.of(mockProfile));

        var response = profileController.getProfileByUserId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan Pérez", response.getBody().fullName());
    }
}