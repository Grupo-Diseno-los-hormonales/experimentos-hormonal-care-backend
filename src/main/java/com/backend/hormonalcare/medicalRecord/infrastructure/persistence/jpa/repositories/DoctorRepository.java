package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Doctor;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.DoctorRecordId;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByDoctorRecordId(DoctorRecordId doctorRecordId);
    Optional<Doctor> findByProfileId(ProfileId profileId);
}
