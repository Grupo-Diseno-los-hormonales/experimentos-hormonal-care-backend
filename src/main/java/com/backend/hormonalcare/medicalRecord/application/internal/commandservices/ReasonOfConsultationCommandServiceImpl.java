package com.backend.hormonalcare.medicalRecord.application.internal.commandservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.ReasonOfConsultation;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateReasonOfConsultationCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationCommandService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.ReasonOfConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReasonOfConsultationCommandServiceImpl implements ReasonOfConsultationCommandService {

    private final ReasonOfConsultationRepository reasonOfConsultationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public ReasonOfConsultationCommandServiceImpl(ReasonOfConsultationRepository reasonOfConsultationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.reasonOfConsultationRepository = reasonOfConsultationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Optional<ReasonOfConsultation> handle(CreateReasonOfConsultationCommand command) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecordId()).orElseThrow(() -> new RuntimeException("MedicalRecord no encontrado"));
        var reasonOfConsultation = new ReasonOfConsultation(command, medicalRecord);
        reasonOfConsultationRepository.save(reasonOfConsultation);
        return Optional.of(reasonOfConsultation);
    }

    @Override
    public Optional<ReasonOfConsultation> handle(UpdateReasonOfConsultationCommand command) {
        var id = command.id();
        if (!reasonOfConsultationRepository.existsById(id)) {
            throw new IllegalArgumentException("ReasonOfConsultation with id "+ command.id() +"does not exists");
        }
        MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecordId()).orElseThrow(() -> new RuntimeException("MedicalRecord no existe"));
        var result = reasonOfConsultationRepository.findById(id);
        var reasonOfConsultationToUpdate = result.get();
        try {
            var updatedReasonOfConsultation = reasonOfConsultationRepository.save(reasonOfConsultationToUpdate.updateInformation(command.description(),command.symptoms(), medicalRecord));
            return Optional.of(updatedReasonOfConsultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating reasonOfConsultation: " + e.getMessage());
        }

    }
}
