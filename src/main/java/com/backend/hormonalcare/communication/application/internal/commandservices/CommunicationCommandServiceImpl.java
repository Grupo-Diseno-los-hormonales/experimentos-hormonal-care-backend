package com.backend.hormonalcare.communication.application.internal.commandservices;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.commands.CreateConversationCommand;
import com.backend.hormonalcare.communication.domain.model.commands.MarkMessageAsReadCommand;
import com.backend.hormonalcare.communication.domain.model.commands.SendMessageCommand;
import com.backend.hormonalcare.communication.domain.model.entities.Participant;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.MessageContent;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.MessageType;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.ParticipantType;
import com.backend.hormonalcare.communication.domain.services.CommunicationCommandService;
import com.backend.hormonalcare.communication.infrastrucutre.persistence.jpa.repositories.CommunicationRepository;
import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunicationCommandServiceImpl implements CommunicationCommandService {

    private final CommunicationRepository communicationRepository;
    private final UserRepository userRepository;

    public CommunicationCommandServiceImpl(CommunicationRepository communicationRepository, UserRepository userRepository) {
        this.communicationRepository = communicationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<Conversation> handle(CreateConversationCommand command) {
        // Validar que todos los usuarios existen
        for (Long userId : command.participantIds()) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));
        }

        // Validar que hay al menos 2 participantes
        if (command.participantIds().size() < 2) {
            throw new IllegalArgumentException("A conversation must have at least 2 participants");
        }

        // Crear participantes
        var participants = command.participantIds().stream()
                .map(userId -> {
                    var user = userRepository.findById(userId).get();
                    var participantType = determineParticipantType(user);
                    return new Participant(userId, participantType);
                })
                .toList();


        // Crear conversación
        var conversation = new Conversation(participants);
        communicationRepository.save(conversation);

        return Optional.of(conversation);
    }

    @Override
    public Optional<Conversation> handle(SendMessageCommand command) {
        // Validar que la conversación existe
        if (!communicationRepository.existsById(command.conversationId())) {
            throw new IllegalArgumentException("Conversation with id " + command.conversationId() + " does not exist");
        }

        var result = communicationRepository.findById(command.conversationId());
        var conversation = result.get();

        try {
            // Crear contenido del mensaje
            MessageContent content;
            if (command.imageUrl() != null && !command.imageUrl().isEmpty()) {
                content = new MessageContent(command.text(), command.imageUrl(), MessageType.TEXT);
            } else {
                content = new MessageContent(command.text(), null, MessageType.TEXT);
            }

            // Enviar mensaje
            var message = conversation.sendMessage(
                    command.senderId(),
                    command.receiverId(),
                    content
            );

            communicationRepository.save(conversation);
            return Optional.of(conversation);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error sending message in conversation with id " + command.conversationId() + ": " + e.getMessage());
        }
    }

    @Override
    public Optional<Conversation> handle(MarkMessageAsReadCommand command) {
        // Validar que la conversación existe
        if (!communicationRepository.existsById(command.conversationId())) {
            throw new IllegalArgumentException("Conversation with id " + command.conversationId() + " does not exist");
        }

        var result = communicationRepository.findById(command.conversationId());
        var conversation = result.get();

        try {
            conversation.markAsRead(command.messageId(), command.userId());
            var updatedConversation = communicationRepository.save(conversation);
            return Optional.of(updatedConversation);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error marking message as read in conversation with id " + command.conversationId() + ": " + e.getMessage());
        }
    }

    private ParticipantType determineParticipantType(User user) {
        String userRole = user.getRole();
        if (userRole.contains("DOCTOR")) {
            return ParticipantType.DOCTOR;
        } else if (userRole.contains("ADMIN")) {
            return ParticipantType.ADMINISTRATOR;
        } else {
            return ParticipantType.PATIENT;
        }
    }
}
