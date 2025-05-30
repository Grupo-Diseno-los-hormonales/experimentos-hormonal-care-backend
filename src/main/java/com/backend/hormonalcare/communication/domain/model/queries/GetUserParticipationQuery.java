package com.backend.hormonalcare.communication.domain.model.queries;

public record GetUserParticipantionQuery(
    Long conversationId,
    Long userId
) {
}
