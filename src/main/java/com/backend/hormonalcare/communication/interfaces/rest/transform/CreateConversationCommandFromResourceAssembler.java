package com.backend.hormonalcare.communication.interfaces.rest.transform;

import com.backend.hormonalcare.communication.domain.model.commands.CreateConversationCommand;
import com.backend.hormonalcare.communication.interfaces.rest.resources.CreateConversationResource;

public class CreateConversationCommandFromResourceAssembler {

    public static CreateConversationCommand toCommandFromResource(CreateConversationResource resource) {
        return new CreateConversationCommand(
                resource.participantIds()
        );
    }
}
