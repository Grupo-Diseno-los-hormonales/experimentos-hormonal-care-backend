package com.backend.hormonalcare.medicalRecord.domain.services;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.*;

import java.util.Optional;

public interface PatientCommandService {
    Optional<Patient> handle(CreatePatientCommand command);
    Optional<Patient> handle(UpdatePatientCommand command);
    Optional<Patient> handle(UpdatePatientDoctorIdCommand command);
    Optional<Patient> handle(UpdatePersonalHistoryPatientCommand command);
    Optional<Patient> handle(UpdateFamilyHistoryPatientCommand command);
}
