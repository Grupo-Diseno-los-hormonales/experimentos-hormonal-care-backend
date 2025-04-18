package com.backend.hormonalcare.profile.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String phoneNumber) {
    public PhoneNumber() {
        this(null);
    }
    public PhoneNumber{
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
