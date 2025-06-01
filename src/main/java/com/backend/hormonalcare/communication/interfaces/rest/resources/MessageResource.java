package com.backend.hormonalcare.communication.interfaces.rest.resources;

import java.time.LocalDateTime;

public record MessageResource(
        Long id,
        Long senderProfileId,
        Long receiverProfileId,
        String text,
        String messageType,
        String imageUrl,
        String status,
        LocalDateTime sentAt
) {
}
