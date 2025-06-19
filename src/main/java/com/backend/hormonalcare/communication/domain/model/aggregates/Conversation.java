package com.backend.hormonalcare.communication.domain.model.aggregates;

import com.backend.hormonalcare.communication.domain.model.entities.Message;
import com.backend.hormonalcare.communication.domain.model.entities.Participant;
import com.backend.hormonalcare.communication.domain.model.events.MessageSentEvent;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.MessageContent;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.ParticipantType;
import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class Conversation extends AuditableAbstractAggregateRoot<Conversation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "conversation_participant_profile_ids",
            joinColumns = @JoinColumn(name = "conversation_id")
    )
    @Column(name = "participant_profile_ids")
    private List<Long> participantProfileIds = new ArrayList<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Participant> participants = new ArrayList<>();

    private LocalDateTime lastActivityAt;

    protected Conversation() {}

    public Conversation(List<Participant> participants) {
        this.participants = new ArrayList<>();

        for (Participant p : participants) {
            p.setConversation(this);
            this.participants.add(p);
        }

        this.lastActivityAt = LocalDateTime.now();

        this.participantProfileIds = participants.stream()
                .map(Participant::getUserId)
                .collect(Collectors.toList());

    }

    public Message sendMessage(Long senderId, Long recipientId, MessageContent content) {
        validateParticipant(senderId);
        validateParticipant(recipientId);

        var message = new Message(senderId, recipientId, content, this);
        this.messages.add(message);
        this.lastActivityAt = LocalDateTime.now();

        this.registerEvent(new MessageSentEvent(this.getId(), message.getId(), senderId, recipientId));

        return message;
    }

    public void markAsRead(Long messageId, Long userId) {
        var message = findMessage(messageId);
        if (message.getReceiverProfileId().equals(userId)) {
            message.markAsRead();
            this.lastActivityAt = LocalDateTime.now();
        }
    }

    public List<Message> getUnreadMessages(Long userId) {
        return messages.stream()
                .filter(m -> m.getReceiverProfileId().equals(userId))
                .filter(m -> m.getStatus().name().equals("UNREAD"))
                .toList();
    }

    private void validateParticipant(Long userId) {
        if (!isParticipantInConversation(userId)) {
            throw new IllegalArgumentException("User is not a participant in this conversation");
        }
    }

    private boolean isParticipantInConversation(Long userId) {
        return participants.stream()
                .anyMatch(p -> p.getUserId().equals(userId));
    }

    private Message findMessage(Long messageId) {
        return messages.stream()
                .filter(m -> m.getId().equals(messageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }

}
