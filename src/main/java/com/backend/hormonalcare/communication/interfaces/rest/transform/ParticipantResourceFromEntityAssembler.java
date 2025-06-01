package com.backend.hormonalcare.communication.interfaces.rest.transform;

import com.backend.hormonalcare.communication.domain.model.entities.Participant;
import com.backend.hormonalcare.communication.interfaces.rest.resources.ParticipantResource;

public class ParticipantResourceFromEntityAssembler {

    public static ParticipantResource toResourceFromEntity(final Participant entity) {
        return new ParticipantResource(
                entity.getId(),
                entity.getUserId(),
                entity.getType().toString(),
                entity.getJoinedAt(),
                entity.getLastSeenAt()
        );
    }
}
