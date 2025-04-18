package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalAppointmentQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalAppointmentQueryServiceImpl implements MedicalAppointmentQueryService {
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentQueryServiceImpl(MedicalAppointmentRepository medicalAppointmentRepository) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public List<MedicalAppointment> handle(GetAllMedicalAppointmentQuery query) {
        return medicalAppointmentRepository.findAll();
    }

    @Override
    public Optional<MedicalAppointment> handle(GetMedicalAppointmentByIdQuery query) {
        return medicalAppointmentRepository.findById(query.id());
    }

    @Override
    public List<MedicalAppointment> handle(GetMedicalAppointmentByEventDate query) {
        return medicalAppointmentRepository.findByEventDate(query.date());

    }

    @Override
    public List<MedicalAppointment> handle(GetMedicalAppointmentByDoctorIdQuery query) {
        return medicalAppointmentRepository.findByDoctorIdOrderByEventDateStartTime(query.doctorId());

    }

}
