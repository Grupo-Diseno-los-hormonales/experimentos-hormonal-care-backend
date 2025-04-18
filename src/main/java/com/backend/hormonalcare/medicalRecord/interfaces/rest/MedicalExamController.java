package com.backend.hormonalcare.medicalRecord.interfaces.rest;
import com.backend.hormonalcare.medicalRecord.domain.model.commands.DeleteMedicalExamCommand;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetMedicalExamByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.MedicalExamQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateMedicalExamResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.MedicalExamResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateMedicalExamResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.CreateMedicalExamCommandFromResourceAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.MedicalExamResourceFromEntityAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.UpdateMedicalExamCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/medical-record/medical-exam", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalExamController {


    private final MedicalExamCommandService medicalExamCommandService;
    private final MedicalExamQueryService medicalExamQueryService;

    public MedicalExamController(MedicalExamCommandService medicalExamCommandService, MedicalExamQueryService medicalExamQueryService) {
        this.medicalExamCommandService = medicalExamCommandService;
        this.medicalExamQueryService = medicalExamQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicalExamResource> createMedicalExam(@RequestBody CreateMedicalExamResource resource) {
        var createMedicalExamCommand = CreateMedicalExamCommandFromResourceAssembler.toCommandFromResource(resource);
        var medicalExam = medicalExamCommandService.handle(createMedicalExamCommand);
        if (medicalExam.isEmpty()) return ResponseEntity.badRequest().build();
        var medicalExamResource = MedicalExamResourceFromEntityAssembler.toResourceFromEntity(medicalExam.get());
        return new ResponseEntity<>(medicalExamResource, HttpStatus.CREATED);
    }

    @GetMapping("/medicalRecordId/{medicalRecordId}")
    public ResponseEntity<List<MedicalExamResource>> getMedicalExamsByMedicalRecordId(@PathVariable Long medicalRecordId) {
        var getMedicalExamsByMedicalRecordIdQuery = new GetMedicalExamByMedicalRecordIdQuery(medicalRecordId);
        var medicalExams = medicalExamQueryService.handle(getMedicalExamsByMedicalRecordIdQuery);
        if (medicalExams.isEmpty()) return ResponseEntity.notFound().build();
        var medicalExamResources = medicalExams.stream()
                .map(MedicalExamResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(medicalExamResources);
    }

    @DeleteMapping("/{medicalExamId}")
    public ResponseEntity<Void> deleteMedicalExam(@PathVariable Long medicalExamId) {
        var deleteMedicalExamCommand = new DeleteMedicalExamCommand(medicalExamId);
        medicalExamCommandService.handle(deleteMedicalExamCommand);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{medicalExamId}")
    public ResponseEntity<MedicalExamResource> getMedicalExamById(@PathVariable Long medicalExamId) {
        var getMedicalExamByIdQuery = new GetMedicalExamByIdQuery(medicalExamId);
        var medicalExam = medicalExamQueryService.handle(getMedicalExamByIdQuery);
        if (medicalExam.isEmpty()) return ResponseEntity.notFound().build();
        var medicalExamResource = MedicalExamResourceFromEntityAssembler.toResourceFromEntity(medicalExam.get());
        return ResponseEntity.ok(medicalExamResource);
    }

    @PutMapping("/{medicalExamId}")
    public ResponseEntity<MedicalExamResource> updateMedicalExam(@PathVariable Long medicalExamId, @RequestBody UpdateMedicalExamResource updateMedicalExamResource) {
        var updateMedicalExamCommand = UpdateMedicalExamCommandFromResourceAssembler.toCommandFromResource(medicalExamId, updateMedicalExamResource);
        var updatedMedicalExam = medicalExamCommandService.handle(updateMedicalExamCommand);
        if (updatedMedicalExam.isEmpty()) return ResponseEntity.badRequest().build();
        var medicalExamResource = MedicalExamResourceFromEntityAssembler.toResourceFromEntity(updatedMedicalExam.get());
        return ResponseEntity.ok(medicalExamResource);
    }
}
