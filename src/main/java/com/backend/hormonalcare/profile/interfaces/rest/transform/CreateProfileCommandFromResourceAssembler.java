package com.backend.hormonalcare.profile.interfaces.rest.transform;

import com.backend.hormonalcare.profile.domain.model.commands.CreateProfileCommand;
import com.backend.hormonalcare.profile.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource){
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.gender(),
                resource.phoneNumber(),
                resource.image(),
                resource.birthday(),
                resource.userId()
        );
    }
}


