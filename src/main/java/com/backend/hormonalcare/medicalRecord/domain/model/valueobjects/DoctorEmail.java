package com.backend.hormonalcare.medicalRecord.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record DoctorEmail(String email) {
    public DoctorEmail {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Doctor email cannot be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Doctor email is not valid");
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
