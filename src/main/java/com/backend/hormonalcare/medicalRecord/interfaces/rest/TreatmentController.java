package com.backend.hormonalcare.medicalRecord.interfaces.rest;

import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.model.queries.GetTreatmentByMedicalRecordIdQuery;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.TreatmentQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.CreateTreatmentResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.TreatmentResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.UpdateTreatmentResource;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.CreateTreatmentCommandFromResourceAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.TreatmentResourceFromEntityAssembler;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.UpdateTreatmentCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/medical-record/treatments",produces = MediaType.APPLICATION_JSON_VALUE)
public class TreatmentController {
    private final TreatmentCommandService treatmentCommandService;
    private final TreatmentQueryService treatmentQueryService;

    public TreatmentController(TreatmentCommandService treatmentCommandService, TreatmentQueryService treatmentQueryService) {
        this.treatmentCommandService = treatmentCommandService;
        this.treatmentQueryService = treatmentQueryService;
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> createTreatment(@RequestBody CreateTreatmentResource resource){
        var createTreatmentCommand = CreateTreatmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var treatment = treatmentCommandService.handle(createTreatmentCommand);
        if(treatment.isEmpty()) return ResponseEntity.badRequest().build();
        var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return new ResponseEntity<>(treatmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<TreatmentResource> getTreatmentById(@PathVariable Long treatmentId){
        var getTreatmentByIdQuery = new GetTreatmentByIdQuery(treatmentId);
        var treatment = treatmentQueryService.handle(getTreatmentByIdQuery);
        if(treatment.isEmpty()) return ResponseEntity.notFound().build();
        var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return ResponseEntity.ok(treatmentResource);
    }

    @PutMapping("/{treatmentId}")
    public ResponseEntity<TreatmentResource> updateTreatment(@PathVariable Long treatmentId, @RequestBody UpdateTreatmentResource updateTreatmentResource){
        var updateTreatmentCommand = UpdateTreatmentCommandFromResourceAssembler.toCommandFromResource(treatmentId, updateTreatmentResource);
        var updateTreatment = treatmentCommandService.handle(updateTreatmentCommand);
        if(updateTreatment.isEmpty()) return ResponseEntity.notFound().build();
        var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(updateTreatment.get());
        return ResponseEntity.ok(treatmentResource);

    }

    @GetMapping("/medicalRecordId/{medicalRecordId}")
    public ResponseEntity<List<TreatmentResource>> getTreatmentsByMedicalRecordId(@PathVariable Long medicalRecordId) {
        var getTreatmentsByMedicalRecordIdQuery = new GetTreatmentByMedicalRecordIdQuery(medicalRecordId);
        var treatments = treatmentQueryService.handle(getTreatmentsByMedicalRecordIdQuery);
        if (treatments.isEmpty()) return ResponseEntity.notFound().build();
        var treatmentResources = treatments.stream()
                .map(TreatmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatmentResources);
    }

}
