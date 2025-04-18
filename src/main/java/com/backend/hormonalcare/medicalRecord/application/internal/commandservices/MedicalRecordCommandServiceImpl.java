package com.backend.hormonalcare.medicalRecord.application.internal.commandservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Patient;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalRecordCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordCommandService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.ReasonOfConsultationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicalRecordCommandServiceImpl implements MedicalRecordCommandService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordCommandServiceImpl(MedicalRecordRepository medicalRecordRepository, ReasonOfConsultationRepository reasonOfConsultationRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Optional<MedicalRecord> handle(CreateMedicalRecordCommand command) {
        Optional<Patient> optionalPatient = patientRepository.findById(command.patientId());

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            MedicalRecord medicalRecord = new MedicalRecord(patient.getId());
            medicalRecordRepository.save(medicalRecord);
            return Optional.of(medicalRecord);
        } else {
            throw new IllegalArgumentException("Patient with id "+ command.patientId() +"does not exists")   ;     }
    }
}
