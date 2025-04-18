package com.backend.hormonalcare.medicalRecord.domain.services;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentQueryService {
    List<MedicalAppointment> handle(GetAllMedicalAppointmentQuery query);
    Optional<MedicalAppointment> handle(GetMedicalAppointmentByIdQuery query);
    List<MedicalAppointment> handle(GetMedicalAppointmentByEventDate query);
    List<MedicalAppointment> handle(GetMedicalAppointmentByDoctorIdQuery query);
}
