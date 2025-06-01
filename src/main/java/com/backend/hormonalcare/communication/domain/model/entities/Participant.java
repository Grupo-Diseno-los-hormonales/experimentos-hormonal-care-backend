package com.backend.hormonalcare.communication.domain.model.entities;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.valuesobjects.ParticipantType;
import com.backend.hormonalcare.iam.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private ParticipantType type;

    private LocalDateTime joinedAt;
    private LocalDateTime lastSeenAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    protected Participant() {}

    public Participant(Long userId, ParticipantType type) {
        this.userId = userId;
        this.type = type;
        this.joinedAt = LocalDateTime.now();
        this.lastSeenAt = LocalDateTime.now();
    }

    public void updateLastSeen() {
        this.lastSeenAt = LocalDateTime.now();
    }

}
