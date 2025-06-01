package com.backend.hormonalcare.communication.interfaces.rest.resources;

import java.util.List;

public record CreateConversationResource(
        List<Long> participantIds
) {
}
