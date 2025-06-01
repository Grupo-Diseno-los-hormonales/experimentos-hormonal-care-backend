package com.backend.hormonalcare.communication.domain.model.events;

public record MessageSentEvent(
        Long conversationId,
        Long messageId,
        Long senderId,
        Long receiverId
) {}
