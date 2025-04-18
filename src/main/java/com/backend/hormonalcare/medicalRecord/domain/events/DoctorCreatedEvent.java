package com.backend.hormonalcare.medicalRecord.domain.events;

public class DoctorCreatedEvent {
    private final Long doctorId;

    public DoctorCreatedEvent(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}
