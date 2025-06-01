package com.backend.hormonalcare.communication.domain.model.valuesobjects;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;

@Embeddable
public class MessageContent {
    // puede ser texto o imagen
    @Nullable
    private String text;
    @Nullable
    private String imgaeUrl;
    private MessageType type;

    protected MessageContent() {}

    public MessageContent(String text, String imageUrl, MessageType type) {
        if (type == MessageType.TEXT && (text == null || text.isBlank())) {
            throw new IllegalArgumentException("Text must not be empty for text messages.");
        }
        if (type == MessageType.IMAGE && (imageUrl == null || imageUrl.isBlank())) {
            throw new IllegalArgumentException("Image URL must not be empty for image messages.");
        }
        this.text = text;
        this.imgaeUrl = imgaeUrl;
        this.type = type;
    }

    public static MessageContent createTextMessage(String text) {
        return new MessageContent(text, null, MessageType.TEXT);
    }

    public static MessageContent createImageMessage(String imageUrl) {
        return new MessageContent(null, imageUrl, MessageType.IMAGE);
    }

    public static MessageContent createTextWithImageMessage(String text, String imageUrl) {
        return new MessageContent(text, imageUrl, MessageType.IMAGE);
    }

    public String getText() {
        return this.text;
    }

    public String getImageUrl() {
        return this.imgaeUrl;
    }

    public String getType() {
        return this.type.toString();
    }
}
