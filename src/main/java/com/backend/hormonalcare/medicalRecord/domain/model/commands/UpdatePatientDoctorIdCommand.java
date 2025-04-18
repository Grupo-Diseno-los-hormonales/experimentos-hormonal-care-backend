package com.backend.hormonalcare.medicalRecord.domain.model.commands;

public record UpdatePatientDoctorIdCommand(Long id, Long doctorId) {
}
