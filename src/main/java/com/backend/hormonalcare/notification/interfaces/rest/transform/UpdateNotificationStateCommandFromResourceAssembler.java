package com.backend.hormonalcare.notification.interfaces.rest.transform;

import com.backend.hormonalcare.notification.domain.model.commands.UpdateNotificationStateCommand;
import com.backend.hormonalcare.notification.interfaces.rest.resources.UpdateNotificationStateResource;

public class UpdateNotificationStateCommandFromResourceAssembler {
    public static UpdateNotificationStateCommand toCommandFromResource(Long id, UpdateNotificationStateResource resource) {
        return new UpdateNotificationStateCommand(
                id,
                resource.state());
    }
}
