package com.backend.hormonalcare.profile.interfaces.rest.transform;

import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileCommand;
import com.backend.hormonalcare.profile.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long id, UpdateProfileResource resource){
        return new UpdateProfileCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday()
        );
    }
}
