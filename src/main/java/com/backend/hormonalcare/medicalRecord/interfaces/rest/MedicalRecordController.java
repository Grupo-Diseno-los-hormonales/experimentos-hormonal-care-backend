package com.backend.hormonalcare.medicalRecord.interfaces.rest;

import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalRecordByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalRecordQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicalRecordResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicalRecordResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.CreateMedicalRecordCommandFromResourceAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.MedicalRecordResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/medicalRecords", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {
    private final MedicalRecordCommandService medicalRecordCommandService;
    private final MedicalRecordQueryService medicalRecordQueryService;

    public MedicalRecordController(MedicalRecordCommandService medicalRecordCommandService, MedicalRecordQueryService medicalRecordQueryService) {
        this.medicalRecordCommandService = medicalRecordCommandService;
        this.medicalRecordQueryService = medicalRecordQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResource> createMedicalRecord(@RequestBody CreateMedicalRecordResource resource) {
        var createMedicalRecordCommand = CreateMedicalRecordCommandFromResourceAssembler.toCommandFromResource(resource);
        var medicalRecord = medicalRecordCommandService.handle(createMedicalRecordCommand);
        if (medicalRecord.isEmpty()) return ResponseEntity.badRequest().build();
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return new ResponseEntity<>(medicalRecordResource, HttpStatus.CREATED);
    }

    @GetMapping("/{medicalRecordId}")
    public ResponseEntity<MedicalRecordResource> getMedicalRecordById(@PathVariable Long medicalRecordId) {
        var getMedicalRecordByIdQuery = new GetMedicalRecordByIdQuery(medicalRecordId);
        var medicalRecord = medicalRecordQueryService.handle(getMedicalRecordByIdQuery);
        if (medicalRecord.isEmpty()) return ResponseEntity.notFound().build();
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return ResponseEntity.ok(medicalRecordResource);
    }
}
