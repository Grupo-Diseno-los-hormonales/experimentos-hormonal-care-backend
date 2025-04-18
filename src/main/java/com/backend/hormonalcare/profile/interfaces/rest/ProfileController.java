package com.backend.hormonalcare.profile.interfaces.rest;

import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByIdQuery;
import com.backend.hormonalcare.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.backend.hormonalcare.profile.domain.services.ProfileCommandService;
import com.backend.hormonalcare.profile.domain.services.ProfileQueryService;
import com.backend.hormonalcare.profile.interfaces.rest.resources.*;
import com.backend.hormonalcare.profile.interfaces.rest.transform.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profile/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }


    @GetMapping("/userId/exists/{userId}")
    public ResponseEntity<Boolean> doesProfileExistByUserId(@PathVariable Long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
        var doesProfileExist = profileQueryService.doesProfileExist(getProfileByUserIdQuery);
        return ResponseEntity.ok(doesProfileExist);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
        var profile = profileQueryService.handle(getProfileByUserIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId){
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/full-update")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileResource updateProfileResource){
        var updateProfileCommand = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(profileId, updateProfileResource);
        var updateProfile = profileCommandService.handle(updateProfileCommand);
        if(updateProfile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateProfile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/image")
    public ResponseEntity<ProfileResource> updateProfileImage(@PathVariable Long profileId, @RequestBody UpdateProfileImageResource updateProfileImageResource){
        var updateProfileImageCommand = UpdateProfileImageCommandFromResourceAssembler.toCommandFromResource(profileId, updateProfileImageResource);
        var updateProfileImage = profileCommandService.handle(updateProfileImageCommand);
        if(updateProfileImage.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateProfileImage.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/phoneNumber")
    public ResponseEntity<ProfileResource> updateProfilePhoneNumber(@PathVariable Long profileId, @RequestBody UpdateProfilePhoneNumberResource updateProfilePhoneNumberResource){
        var updateProfilePhoneNumberCommand = UpdateProfilePhoneNumberCommandFromResourceAssembler.toCommandFromResource(profileId, updateProfilePhoneNumberResource);
        var updateProfilePhoneNumber = profileCommandService.handle(updateProfilePhoneNumberCommand);
        if(updateProfilePhoneNumber.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateProfilePhoneNumber.get());
        return ResponseEntity.ok(profileResource);

    }


}

