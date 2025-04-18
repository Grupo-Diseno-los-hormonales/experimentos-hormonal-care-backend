package com.backend.hormonalcare.notification.interfaces.rest.resources;

import com.backend.hormonalcare.notification.domain.model.valueobjects.State;

import java.util.Date;

public record NotificationResource(
        Long id,
        Date created_at,
        String title,
        String description,
        State state,
        Long recipientId
) {
}
