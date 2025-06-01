package com.backend.hormonalcare.communication.interfaces.rest.transform;

import com.backend.hormonalcare.communication.domain.model.entities.Message;
import com.backend.hormonalcare.communication.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {

    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(
                entity.getId(),
                entity.getSenderProfileId(),
                entity.getReceiverProfileId(),
                entity.getText(),
                entity.getMessageType(),
                entity.getImageUrl(),
                entity.getStatus().toString(),
                entity.getSentAt()
        );
    }
}
