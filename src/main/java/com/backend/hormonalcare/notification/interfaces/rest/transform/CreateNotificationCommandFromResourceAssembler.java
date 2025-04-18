package com.backend.hormonalcare.notification.interfaces.rest.transform;

import com.backend.hormonalcare.notification.domain.model.commands.CreateNotificationCommand;
import com.backend.hormonalcare.notification.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.title(),
                resource.description(),
                resource.state(),
                resource.recipientId()
        );
    }
}
