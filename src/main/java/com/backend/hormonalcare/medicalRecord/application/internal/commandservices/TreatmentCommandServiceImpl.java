package com.backend.hormonalcare.medicalRecord.application.internal.commandservices;

import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.MedicalRecord;
import com.backend.hormonalcare.medicalRecord.domain.model.aggregates.Treatment;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateTreatmentCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentCommandService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentCommandServiceImpl implements TreatmentCommandService {
    private final TreatmentRepository treatmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public TreatmentCommandServiceImpl(TreatmentRepository treatmentRepository, MedicalRecordRepository medicalRecordRepository) {
        this.treatmentRepository = treatmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Optional<Treatment> handle(CreateTreatmentCommand command) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecordId()).orElseThrow(() -> new RuntimeException("MedicalRecord does not exits"));
        var treatment = new Treatment(command, medicalRecord);
        treatmentRepository.save(treatment);
        return Optional.of(treatment);
    }

    @Override
    public Optional<Treatment> handle(UpdateTreatmentCommand command) {
        var id = command.id();
        if (!treatmentRepository.existsById(id))
            throw new IllegalArgumentException("Treatment with id "+ id +" does not exist");
        MedicalRecord medicalRecord = medicalRecordRepository.findById(command.medicalRecordId()).orElseThrow(() -> new RuntimeException("MedicalRecord does not exits"));
        var result = treatmentRepository.findById(id);
        var treatmentToUpdate = result.get();
        try{
            var updatedTreatment = treatmentRepository.save(treatmentToUpdate.updateInformation(command.description(), medicalRecord));
            return Optional.of(updatedTreatment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating treatment with id "+ id);
        }
    }
}
