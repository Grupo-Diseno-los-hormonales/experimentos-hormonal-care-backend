package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateDoctorCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateDoctorCommand;

import java.util.Optional;

public interface DoctorCommandService {
    Optional<Doctor> handle(CreateDoctorCommand command);
    Optional<Doctor> handle(UpdateDoctorCommand command);
}
