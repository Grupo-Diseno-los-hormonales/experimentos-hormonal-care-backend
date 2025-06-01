package com.backend.hormonalcare.communication.domain.model.commands;

import java.util.List;

public record CreateConversationCommand(
        List<Long> participantIds
) {
}
