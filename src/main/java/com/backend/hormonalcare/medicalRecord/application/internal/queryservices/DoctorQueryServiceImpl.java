package com.backend.hormonalcare.medicalRecord.application.internal.queryservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.DoctorQueryService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorQueryServiceImpl implements DoctorQueryService {
    private final DoctorRepository doctorRepository;

    public DoctorQueryServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByIdQuery query) {
        
        return doctorRepository.findById(query.id());
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByProfileIdQuery query) {
        return doctorRepository.findByProfileId(query.profileId());
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByDoctorRecordIdQuery query) {
        return doctorRepository.findByDoctorRecordId(query.doctorRecordId());
    }

    @Override
    public Optional<Long> handle(GetProfileIdByDoctorIdQuery query) {
        return doctorRepository.findById(query.doctorId())
                .map(Doctor::getProfileId) ;
    }

    @Override
    public List<Doctor> handle(GetAllDoctorsQuery query) {
        return doctorRepository.findAll();
    }

}
