package com.backend.hormonalcare.communication.interfaces.rest.resources;

public record SendMessageResource(
        Long senderProfileId,
        Long receiverProfileId,
        String text,
        String imageUrl
) {
}
