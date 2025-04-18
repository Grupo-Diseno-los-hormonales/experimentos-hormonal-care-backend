package com.backend.hormonalcare.notification.domain.model.commands;

import com.backend.hormonalcare.notification.domain.model.valueobjects.State;

public record CreateNotificationCommand(String title, String message, State state, Long recipientId) {
}
