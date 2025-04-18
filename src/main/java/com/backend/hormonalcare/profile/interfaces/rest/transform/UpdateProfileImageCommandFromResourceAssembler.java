package com.backend.hormonalcare.profile.interfaces.rest.transform;

import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfileImageCommand;
import com.backend.hormonalcare.profile.interfaces.rest.resources.UpdateProfileImageResource;

public class UpdateProfileImageCommandFromResourceAssembler {
    public static UpdateProfileImageCommand toCommandFromResource(Long id, UpdateProfileImageResource resource){
        return new UpdateProfileImageCommand(
                id,
                resource.image()
        );
    }
}
