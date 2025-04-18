package com.backend.hormonalcare.medicalRecord.domain.events;

public class PatientCreatedEvent {
    private final Long patientId;

    public PatientCreatedEvent(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientId() {
        return patientId;
    }
}
