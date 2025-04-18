package com.backend.hormonalcare.medicalRecord.application.internal.commandservices;

import com.backend.hormonalcare.medicalRecord.domain.model.commands.UpdateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.entities.MedicationType;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.CreateMedicationTypeCommand;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicationTypeCommandService;
import com.backend.hormonalcare.medicalRecord.infrastructure.persistence.jpa.repositories.MedicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationTypeCommandServiceImpl implements MedicationTypeCommandService {
    private final MedicationTypeRepository medicationTypeRepository;

    public MedicationTypeCommandServiceImpl(MedicationTypeRepository medicationTypeRepository) {
        this.medicationTypeRepository = medicationTypeRepository;
    }

    @Override
    public Optional<MedicationType> handle(CreateMedicationTypeCommand command) {
        var medicationType = new MedicationType(command);
        medicationTypeRepository.save(medicationType);
        return Optional.of(medicationType);
    }

    @Override
    public Optional<MedicationType> handle(UpdateMedicationTypeCommand command) {
        var medicationType = medicationTypeRepository.findById(command.id());

        if (medicationType.isEmpty()) {
            return Optional.empty();
        }
        medicationType.get().update(command);
        medicationTypeRepository.save(medicationType.get());
        return Optional.of(medicationType.get());
    }
}