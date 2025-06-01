package com.backend.hormonalcare.communication.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ParticipantResource(
        Long id,
        Long userId,
        String participantType,
        LocalDateTime joinedAt,
        LocalDateTime lastSeenAt
) {
}
