package com.backend.hormonalcare.communication.domain.services;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.commands.CreateConversationCommand;
import com.backend.hormonalcare.communication.domain.model.commands.MarkMessageAsReadCommand;
import com.backend.hormonalcare.communication.domain.model.commands.SendMessageCommand;

import java.util.Optional;

public interface CommunicationCommandService {
    Optional<Conversation> handle(CreateConversationCommand command);
    Optional<Conversation> handle(SendMessageCommand command);
    Optional<Conversation> handle(MarkMessageAsReadCommand command);
}
