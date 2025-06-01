package com.backend.hormonalcare.communication.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;

public record ConversationResource(
    Long id,
    List<ParticipantResource> participants,
    MessageResource lastMessage,
    LocalDateTime lastActivityAt,
    Integer messagesCount,
    Integer unreadCount
) {
}
