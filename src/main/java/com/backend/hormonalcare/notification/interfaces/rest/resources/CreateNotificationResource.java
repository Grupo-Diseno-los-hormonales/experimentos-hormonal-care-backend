package com.backend.hormonalcare.notification.interfaces.rest.resources;

import com.backend.hormonalcare.notification.domain.model.valueobjects.State;

public record CreateNotificationResource(
        String title,
        String description,
        State state,
        Long recipientId
) {
}
