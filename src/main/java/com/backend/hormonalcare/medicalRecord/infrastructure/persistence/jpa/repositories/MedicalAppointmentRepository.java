package com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

    List<MedicalAppointment> findByEventDate(LocalDate eventDate);
    List<MedicalAppointment> findByPatientId(Long patientId);
    List<MedicalAppointment> findByEventDateAndStartTime(LocalDate eventDate, String startTime);

    @Query("SELECT m FROM MedicalAppointment m WHERE m.doctor.id = :doctorId ORDER BY m.eventDate, m.startTime")
    List<MedicalAppointment> findByDoctorIdOrderByEventDateStartTime(Long doctorId);

    @Query("SELECT COUNT(m) > 0 FROM MedicalAppointment m WHERE m.doctor.id = :doctorId AND m.eventDate = :eventDate AND m.startTime = :startTime")
    boolean existsByDoctorIdAndEventDateAndStartTime(Long doctorId, LocalDate eventDate, String startTime);
}
