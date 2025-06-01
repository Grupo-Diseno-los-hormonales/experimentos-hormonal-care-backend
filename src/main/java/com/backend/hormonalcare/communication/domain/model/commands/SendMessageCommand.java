package com.backend.hormonalcare.communication.domain.model.commands;

public record SendMessageCommand(
        Long conversationId,
        Long senderId,
        Long receiverId,
        String text,
        String imageUrl
) {
}
