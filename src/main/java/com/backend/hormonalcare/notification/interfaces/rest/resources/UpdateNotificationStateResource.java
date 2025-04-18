package com.backend.hormonalcare.notification.interfaces.rest.resources;

import com.backend.hormonalcare.notification.domain.model.valueobjects.State;

public record UpdateNotificationStateResource(
        State state
) {
}
