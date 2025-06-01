package com.backend.hormonalcare.communication.domain.model.commands;

public record MarkMessageAsReadCommand(
        Long conversationId,
        Long messageId,
        Long userId
) {
}
