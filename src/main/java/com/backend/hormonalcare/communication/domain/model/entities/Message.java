package com.backend.hormonalcare.communication.domain.model.entities;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.MessageContent;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.MessageStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderProfileId;
    private Long receiverProfileId;

    @Embedded
    private MessageContent message;

    private MessageStatus status;

    private LocalDateTime sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    protected Message() {}

    public Message(Long senderId, Long receiverId, MessageContent message, Conversation conversation) {
        this.senderProfileId = senderId;
        this.receiverProfileId = receiverId;
        this.message = message;
        this.status = MessageStatus.SENT; // Default status when created
        this.sentAt = LocalDateTime.now();
        this.conversation = conversation;
    }

    public String getText() {
        return message.getText();
    }

    public String getImageUrl() {
        return message.getImageUrl();
    }

    public String getMessageType() {
        return message.getType();
    }

    public void markAsRead() {
        this.status = MessageStatus.READ;
    }

    public void markAsDelivered() {
        this.status = MessageStatus.DELIVERED;
    }
}
