package com.backend.hormonalcare.notification.interfaces.rest.transform;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import com.backend.hormonalcare.notification.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification notification) {
        return new NotificationResource(
                notification.getId(),
                notification.getCreatedAt(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getState(),
                notification.getRecipientId()
        );
    }
}
