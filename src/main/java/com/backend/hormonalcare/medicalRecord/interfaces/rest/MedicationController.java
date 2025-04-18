package com.backend.hormonalcare.medicalRecord.interfaces.rest;

import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.services.*;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.*;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/medical-record/medications", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicationController {
    private final MedicationCommandService medicationCommandService;
    private final MedicationQueryService medicationQueryService;
    private final MedicationTypeCommandService medicationTypeCommandService;
    private final MedicationTypeQueryService medicationTypeQueryService;
    private final PrescriptionCommandService prescriptionCommandService;
    private final PrescriptionQueryService prescriptionQueryService;


    public MedicationController(MedicationCommandService medicationCommandService, MedicationQueryService medicationQueryService, MedicationTypeCommandService medicationTypeCommandService, MedicationTypeQueryService medicationTypeQueryService, PrescriptionCommandService prescriptionCommandService, PrescriptionQueryService prescriptionQueryService) {
        this.medicationCommandService = medicationCommandService;
        this.medicationQueryService = medicationQueryService;
        this.medicationTypeCommandService = medicationTypeCommandService;
        this.medicationTypeQueryService = medicationTypeQueryService;
        this.prescriptionCommandService = prescriptionCommandService;
        this.prescriptionQueryService = prescriptionQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicationResource> createMedication(@RequestBody CreateMedicationResource resource) {
        var createMedicationCommand = CreateMedicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var medication = medicationCommandService.handle(createMedicationCommand);
        if (medication.isEmpty()) return ResponseEntity.badRequest().build();
        var medicationResource = MedicationResourceFromEntityAssembler.toResourceFromEntity(medication.get());
        return new ResponseEntity<>(medicationResource, HttpStatus.CREATED);
    }

    @PutMapping("/{medicationId}")
    public ResponseEntity<MedicationResource> updateMedication(@PathVariable Long medicationId, @RequestBody UpdateMedicationResource updateMedicationResource) {
        var updateMedicationCommand = UpdateMedicationCommandFromResourceAssembler.toCommandFromResource(medicationId, updateMedicationResource);
        var updatedMedication = medicationCommandService.handle(updateMedicationCommand);
        if (updatedMedication.isEmpty()) return ResponseEntity.badRequest().build();
        var medicationResource = MedicationResourceFromEntityAssembler.toResourceFromEntity(updatedMedication.get());
        return ResponseEntity.ok(medicationResource);
    }


    @GetMapping("/{medicationId}")
    public ResponseEntity<MedicationResource> getMedicationById(@PathVariable Long medicationId) {
        var getMedicationByIdQuery = new GetMedicationByIdQuery(medicationId);
        var medication = medicationQueryService.handle(getMedicationByIdQuery);
        if (medication.isEmpty()) return ResponseEntity.notFound().build();
        var medicationResource = MedicationResourceFromEntityAssembler.toResourceFromEntity(medication.get());
        return ResponseEntity.ok(medicationResource);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResource>> getAllMedications() {
        var getAllMedicationsQuery = new GetAllMedicationsQuery();
        var medications = medicationQueryService.handle(getAllMedicationsQuery);
        var medicationResources = medications.stream()
                .map(MedicationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(medicationResources);
    }


    @PostMapping("/medicationTypes")
    public ResponseEntity<MedicationTypeResource> createMedicationType(@RequestBody CreateMedicationTypeResource resource) {
        var createMedicationTypeCommand = CreateMedicationTypeCommandFromResourceAssembler.toCommandFromResource(resource);
        var medicationType = medicationTypeCommandService.handle(createMedicationTypeCommand);
        if (medicationType.isEmpty()) return ResponseEntity.badRequest().build();
        var medicationTypeResource = MedicationTypeResourceFromEntityAssembler.toResourceFromEntity(medicationType.get());
        return new ResponseEntity<>(medicationTypeResource, HttpStatus.CREATED);
    }

    @GetMapping("/medicationTypes")
    public ResponseEntity<List<MedicationTypeResource>> getAllMedicationTypes() {
        var getAllMedicationTypesQuery = new GetAllMedicationTypesQuery();
        var medicationTypes = medicationTypeQueryService.handle(getAllMedicationTypesQuery);
        var medicationTypeResources = medicationTypes.stream()
                .map(MedicationTypeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(medicationTypeResources);
    }
    @PutMapping("/medicationTypes/{medicationTypeId}")
    public ResponseEntity<MedicationTypeResource> updateMedicationType(@PathVariable Long medicationTypeId, @RequestBody UpdateMedicationTypeResource updateMedicationTypeResource) {
        var updateMedicationTypeCommand = UpdateMedicationTypeCommandFromResourceAssembler.toCommandFromResource(medicationTypeId, updateMedicationTypeResource);
        var updatedMedicationType = medicationTypeCommandService.handle(updateMedicationTypeCommand);
        if (updatedMedicationType.isEmpty()) return ResponseEntity.badRequest().build();
        var medicationTypeResource = MedicationTypeResourceFromEntityAssembler.toResourceFromEntity(updatedMedicationType.get());
        return ResponseEntity.ok(medicationTypeResource);
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<PrescriptionResource> createPrescription(@RequestBody CreatePrescriptionResource resource) {
        var createPrescriptionCommand = CreatePrescriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        var prescription = prescriptionCommandService.handle(createPrescriptionCommand);
        if (prescription.isEmpty()) return ResponseEntity.badRequest().build();
        var prescriptionResource = PrescriptionResourceFromEntityAssembler.toResourceFromEntity(prescription.get());
        return new ResponseEntity<>(prescriptionResource, HttpStatus.CREATED);
    }

    @GetMapping("/prescriptions")
    public ResponseEntity<List<PrescriptionResource>> getAllPrescriptions() {
        var getAllPrescriptionsQuery = new GetAllPrescriptionsQuery();
        var prescriptions = prescriptionQueryService.handle(getAllPrescriptionsQuery);
        var prescriptionResources = prescriptions.stream()
                .map(PrescriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(prescriptionResources);
    }
    @GetMapping("/medicationTypes/{medicationTypeId}")
    public ResponseEntity<MedicationTypeResource> getMedicationTypeById(@PathVariable Long medicationTypeId) {
        var getMedicationTypeByIdQuery = new GetMedicationTypeByIdQuery(medicationTypeId);
        var medicationType = medicationTypeQueryService.handle(getMedicationTypeByIdQuery);
        if (medicationType.isEmpty()) return ResponseEntity.notFound().build();
        var medicationTypeResource = MedicationTypeResourceFromEntityAssembler.toResourceFromEntity(medicationType.get());
        return ResponseEntity.ok(medicationTypeResource);
    }

    @GetMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<PrescriptionResource> getPrescriptionById(@PathVariable Long prescriptionId) {
        var getPrescriptionByIdQuery = new GetPrescriptionByIdQuery(prescriptionId);
        var prescription = prescriptionQueryService.handle(getPrescriptionByIdQuery);
        if (prescription.isEmpty()) return ResponseEntity.notFound().build();
        var prescriptionResource = PrescriptionResourceFromEntityAssembler.toResourceFromEntity(prescription.get());
        return ResponseEntity.ok(prescriptionResource);
    }

    @GetMapping("/prescriptions/medicalRecordId/{medicalRecordId}")
public ResponseEntity<List<PrescriptionResource>> getPrescriptionsByMedicalRecordId(@PathVariable Long medicalRecordId) {
    var getPrescriptionsByMedicalRecordIdQuery = new GetPrescriptionByMedicalRecordIdQuery(medicalRecordId);
    var prescriptions = prescriptionQueryService.handle(getPrescriptionsByMedicalRecordIdQuery);
    if (prescriptions.isEmpty()) return ResponseEntity.notFound().build();
    var prescriptionResources = prescriptions.stream()
            .map(PrescriptionResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(prescriptionResources);
}


    @PutMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<PrescriptionResource> updatePrescription(@PathVariable Long prescriptionId, @RequestBody UpdatePrescriptionResource updatePrescriptionResource) {
        var updatePrescriptionCommand = UpdatePrescriptionCommandFromResourceAssembler.toCommandFromResource(prescriptionId, updatePrescriptionResource);
        var updatedPrescription = prescriptionCommandService.handle(updatePrescriptionCommand);
        if (updatedPrescription.isEmpty()) return ResponseEntity.badRequest().build();
        var prescriptionResource = PrescriptionResourceFromEntityAssembler.toResourceFromEntity(updatedPrescription.get());
        return ResponseEntity.ok(prescriptionResource);
    }
}