package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.DeleteMedicalAppointmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalAppointmentCommand;

import java.util.Optional;

public interface MedicalAppointmentCommandService {
    Optional<MedicalAppointment> handle(CreateMedicalAppointmentCommand command);
    Optional<MedicalAppointment> handle(UpdateMedicalAppointmentCommand command);
    void handle(DeleteMedicalAppointmentCommand command);
}
