package com.backend.hormonalcare.communication.interfaces.rest.transform;

import com.backend.hormonalcare.communication.domain.model.commands.SendMessageCommand;
import com.backend.hormonalcare.communication.interfaces.rest.resources.SendMessageResource;

public class SendMessageCommandFromResourceAssembler {
    public static SendMessageCommand toCommandFromResource(Long conversationId, SendMessageResource resource) {
        return new SendMessageCommand(
                conversationId,
                resource.senderProfileId(),
                resource.receiverProfileId(),
                resource.text(),
                resource.imageUrl()
        );
    }
}
