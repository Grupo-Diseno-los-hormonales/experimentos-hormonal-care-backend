package com.backend.hormonalcare.communication.interfaces.rest.transform;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.interfaces.rest.resources.ConversationResource;

import java.util.Comparator;

public class ConversationResourceFromEntityAssembler {

    public static ConversationResource toResourceFromEntity(Conversation entity) {
        var participantResources = entity.getParticipants().stream()
                .map(ParticipantResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        var lastMessageResources = entity.getMessages().stream()
                .max(Comparator.comparing(m -> m.getSentAt()))
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .orElse(null);

        Integer messageCount = entity.getMessages().size();

        Integer unreadCount = Math.toIntExact(entity.getMessages().stream()
                .filter(m -> m.getStatus().name().equals("SENT") || m.getStatus().name().equals("DELIVERED"))
                .count());

        return new ConversationResource(
                entity.getId(),
                participantResources,
                lastMessageResources,
                entity.getLastActivityAt(),
                messageCount,
                unreadCount
        );
    }
}
