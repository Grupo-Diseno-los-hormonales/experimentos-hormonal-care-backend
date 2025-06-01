package com.backend.hormonalcare.communication.domain.model.queries;

public record GetUserParticipationQuery(
    Long conversationId,
    Long userId
) {
}
