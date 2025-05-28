package com.backend.hormonalcare.communication.domain.model.valuesobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Participant {
    private Long profileId;
    private String name;

    protected Participant() {}

    public Participant(Long profileId, String name) {
        if (profileId == null || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Profile ID and name must not be null or empty.");
        }
        this.profileId = profileId;
        this.name = name;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }
}
