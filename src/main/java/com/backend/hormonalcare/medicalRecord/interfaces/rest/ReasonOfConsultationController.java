package com.backend.hormonalcare.medicalRecord.interfaces.rest;


import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetReasonOfConsultationByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.ReasonOfConsultationQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateReasonOfConsultationResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.ReasonOfConsultationResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateReasonOfConsultationResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.CreateReasonOfConsultationCommandFromResourceAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.ReasonOfConsultationResourceFromEntityAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.UpdateReasonOfConsultationCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/medical-record/reasons-of-consultation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReasonOfConsultationController {
    private final ReasonOfConsultationCommandService reasonOfConsultationCommandService;
    private final ReasonOfConsultationQueryService reasonOfConsultationQueryService;

    public ReasonOfConsultationController(ReasonOfConsultationCommandService reasonOfConsultationCommandService, ReasonOfConsultationQueryService reasonOfConsultationQueryService) {
        this.reasonOfConsultationCommandService = reasonOfConsultationCommandService;
        this.reasonOfConsultationQueryService = reasonOfConsultationQueryService;
    }

    @PostMapping
    public ResponseEntity<ReasonOfConsultationResource> createReasonOfConsultation(@RequestBody CreateReasonOfConsultationResource resource) {
        var createReasonOfConsultationCommand = CreateReasonOfConsultationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reasonOfConsultation = reasonOfConsultationCommandService.handle(createReasonOfConsultationCommand);
        if (reasonOfConsultation.isEmpty()) return ResponseEntity.badRequest().build();
        var reasonOfConsultationResource = ReasonOfConsultationResourceFromEntityAssembler.toResourceFromEntity(reasonOfConsultation.get());
        return new ResponseEntity<>(reasonOfConsultationResource, HttpStatus.CREATED);
    }


    @GetMapping("/medicalRecordId/{medicalRecordId}")
    public ResponseEntity<List<ReasonOfConsultationResource>> getReasonsOfConsultationByMedicalRecordId(@PathVariable Long medicalRecordId) {
        var getReasonsOfConsultationByMedicalRecordIdQuery = new GetReasonOfConsultationByMedicalRecordIdQuery(medicalRecordId);
        var reasonsOfConsultation = reasonOfConsultationQueryService.handle(getReasonsOfConsultationByMedicalRecordIdQuery);
        if (reasonsOfConsultation.isEmpty()) return ResponseEntity.notFound().build();
        var reasonOfConsultationResources = reasonsOfConsultation.stream()
                .map(ReasonOfConsultationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reasonOfConsultationResources);
    }




    @GetMapping("/{reasonOfConsultationId}")
    public ResponseEntity<ReasonOfConsultationResource> getReasonOfConsultationById(@PathVariable Long reasonOfConsultationId) {
        var getReasonOfConsultationByIdQuery = new GetReasonOfConsultationByIdQuery(reasonOfConsultationId);
        var reasonOfConsultation = reasonOfConsultationQueryService.handle(getReasonOfConsultationByIdQuery);
        if (reasonOfConsultation.isEmpty()) return ResponseEntity.notFound().build();
        var reasonOfConsultationResource = ReasonOfConsultationResourceFromEntityAssembler.toResourceFromEntity(reasonOfConsultation.get());
        return ResponseEntity.ok(reasonOfConsultationResource);
    }

    @PutMapping("/{reasonOfConsultationId}")
    public ResponseEntity<ReasonOfConsultationResource> updateReasonOfConsultation(@PathVariable Long reasonOfConsultationId, @RequestBody UpdateReasonOfConsultationResource updateReasonOfConsultationResource) {
        var updateReasonOfConsultationCommand = UpdateReasonOfConsultationCommandFromResourceAssembler.toCommandFromResource(reasonOfConsultationId, updateReasonOfConsultationResource);
        var updatedReasonOfConsultation = reasonOfConsultationCommandService.handle(updateReasonOfConsultationCommand);
        if (updatedReasonOfConsultation.isEmpty()) return ResponseEntity.badRequest().build();
        var reasonOfConsultationResource = ReasonOfConsultationResourceFromEntityAssembler.toResourceFromEntity(updatedReasonOfConsultation.get());
        return ResponseEntity.ok(reasonOfConsultationResource);
    }
}
