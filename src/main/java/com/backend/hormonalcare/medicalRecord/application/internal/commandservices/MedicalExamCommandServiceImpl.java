package com.backend.hormonalcare.medicalRecord.application.internal.commandservices;


import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalExam;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.DeleteMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamCommandService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalExamRepository;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MedicalExamCommandServiceImpl implements MedicalExamCommandService {

    private final MedicalExamRepository medicalExamRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalExamCommandServiceImpl(MedicalExamRepository medicalExamRepository, MedicalRecordRepository medicalRecordRepository) {
        this.medicalExamRepository = medicalExamRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
public Optional<MedicalExam> handle(CreateMedicalExamCommand command) {
    MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecord()).orElseThrow(() -> new RuntimeException("MedicalRecord no encontrado"));
    var medicalExam = new MedicalExam(command.url(), command.typeMedicalExam(), command.uploadDate(), medicalRecord);
    medicalExamRepository.save(medicalExam);
    return Optional.of(medicalExam);
}

    @Override
    public Optional<MedicalExam> handle(UpdateMedicalExamCommand command) {
        var id = command.id();
        if (!medicalExamRepository.existsById(id)) {
            throw new IllegalArgumentException("MedicalExam with id " + command.id() + " does not exist");
        }
        MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecordId()).orElseThrow(() -> new RuntimeException("MedicalRecord no existe"));
        var result = medicalExamRepository.findById(id);
        var medicalExamToUpdate = result.get();
        try {
            var updatedMedicalExam = medicalExamRepository.save(medicalExamToUpdate.updateInformation(command.url(), command.typeMedicalExam(), medicalRecord));
            return Optional.of(updatedMedicalExam);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medicalExam: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMedicalExamCommand command) {
        if (!medicalExamRepository.existsById(command.id())) {
            throw new IllegalArgumentException("MedicalExam with id " + command.id() + " does not exist");
        }
        medicalExamRepository.deleteById(command.id());
    }
}
