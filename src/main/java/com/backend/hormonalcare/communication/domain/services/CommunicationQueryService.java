package com.backend.hormonalcare.communication.domain.services;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.entities.Message;
import com.backend.hormonalcare.communication.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface CommunicationQueryService {
    List<Conversation> handle(GetAllConversationsQuery query);
    Optional<Conversation> handle(GetConversationByIdQuery query);
    List<Conversation> handle(GetConversationsByUserIdQuery query);
    List<Message> handle(GetMessagesByConversationIdQuery query);
    List<Message> handle(GetUnreadMessagesByUserIdQuery query);
    boolean doesConversationExist(GetConversationByIdQuery query);
    boolean isUserParticipant(GetUserParticipationQuery query);
}
