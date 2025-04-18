package com.backend.hormonalcare.profile.interfaces.rest.transform;

import com.backend.hormonalcare.profile.domain.model.commands.UpdateProfilePhoneNumberCommand;
import com.backend.hormonalcare.profile.interfaces.rest.resources.UpdateProfilePhoneNumberResource;

public class UpdateProfilePhoneNumberCommandFromResourceAssembler {
    public static UpdateProfilePhoneNumberCommand toCommandFromResource(Long id, UpdateProfilePhoneNumberResource resource){
        return new UpdateProfilePhoneNumberCommand(
                id,
                resource.phoneNumber()
        );
    }
}
