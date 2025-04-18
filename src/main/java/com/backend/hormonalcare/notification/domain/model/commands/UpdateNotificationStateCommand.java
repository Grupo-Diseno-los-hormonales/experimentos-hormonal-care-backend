package com.backend.hormonalcare.notification.domain.model.commands;

import com.backend.hormonalcare.notification.domain.model.valueobjects.State;

public record UpdateNotificationStateCommand(Long id, State state) {

}
