package com.backend.hormonalcare.communication.application.internal.queryservices;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.entities.Message;
import com.backend.hormonalcare.communication.domain.model.queries.*;
import com.backend.hormonalcare.communication.domain.services.CommunicationQueryService;
import com.backend.hormonalcare.communication.infrastrucutre.persistence.jpa.repositories.CommunicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunicationQueryServiceImpl implements CommunicationQueryService {

    private final CommunicationRepository communicationRepository;

    public CommunicationQueryServiceImpl(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    @Override
    public List<Conversation> handle(GetAllConversationsQuery query) {
        return communicationRepository.findAll();
    }

    @Override
    public Optional<Conversation> handle(GetConversationByIdQuery query) {
        return communicationRepository.findById(query.conversationId());
    }

    @Override
    public List<Conversation> handle(GetConversationsByUserIdQuery query) {
        return communicationRepository.findByParticipantUserId(query.userId());
    }

    @Override
    public List<Message> handle(GetMessagesByConversationIdQuery query) {
        var conversation = communicationRepository.findById(query.conversationId())
                .orElseThrow(() -> new IllegalArgumentException("Conversation with id " + query.conversationId() + " does not exist"));

        boolean isParticipant = conversation.getParticipants().stream()
                .anyMatch(p -> p.getUserId().equals(query.userId()));

        if (!isParticipant) {
            throw new IllegalArgumentException("User with id " + query.userId() + " is not a participant in this conversation");
        }

        return conversation.getMessages().stream()
                .skip((long) query.page() * query.size())
                .limit(query.size())
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> handle(GetUnreadMessagesByUserIdQuery query) {
        var userConversations = communicationRepository.findByParticipantUserId(query.userId());

        return userConversations.stream()
                .flatMap(conversation -> conversation.getUnreadMessages(query.userId()).stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean doesConversationExist(GetConversationByIdQuery query) {
        return communicationRepository.existsById(query.conversationId());
    }

    @Override
    public boolean isUserParticipant(GetUserParticipationQuery query) {
        var conversation = communicationRepository.findById(query.conversationId());

        if (conversation.isEmpty()) {
            return false;
        }

        return conversation.get().getParticipants().stream()
                .anyMatch(p -> p.getUserId().equals(query.userId()));
    }
}
