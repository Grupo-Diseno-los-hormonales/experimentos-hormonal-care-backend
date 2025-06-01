package com.backend.hormonalcare.communication.domain.model.queries;

public record GetMessagesByConversationIdQuery(
        Long conversationId,
        Long userId,
        Integer page,
        Integer size
) {
}
