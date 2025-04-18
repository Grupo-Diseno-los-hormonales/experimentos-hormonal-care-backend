package com.backend.hormonalcare.medicalRecord.interfaces.rest;

import com.backend.hormonalcare.medicalRecord.domain.model.queries.*;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PatientRecordId;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.PatientRecordId;
import com.backend.hormonalcare.medicalRecord.domain.model.valueobjects.ProfileId;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientCommandService;
import com.backend.hormonalcare.medicalRecord.domain.services.PatientQueryService;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.resources.*;
import com.backend.hormonalcare.medicalRecord.interfaces.rest.transform.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/medical-record/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;

    public PatientController(PatientCommandService patientCommandService, PatientQueryService patientQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
    }

    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource resource) {
        var createPatientCommand = CreatePatientCommandFromResourceAssembler.toCommandFromResource(resource);
        var optionalPatient = patientCommandService.handle(createPatientCommand);
        if (optionalPatient.isPresent()) {
            var patient = optionalPatient.get();
            if (patient.getPatientRecordId().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            var getPatientByPatientRecordIdQuery = new GetPatientByPatientRecordIdQuery(new PatientRecordId(patient.getPatientRecordId()));
            var patientQueryResult = patientQueryService.handle(getPatientByPatientRecordIdQuery);
            if (patientQueryResult.isEmpty()) return ResponseEntity.badRequest().build();
            var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patientQueryResult.get());
            return new ResponseEntity<>(patientResource, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{patientId}/profile-id")
    public ResponseEntity<Long> getProfileIdByPatientId(@PathVariable Long patientId) {
        var getProfileIdByPatientIdQuery = new GetProfileIdByPatientIdQuery(patientId);
        var profileId = patientQueryService.handle(getProfileIdByPatientIdQuery);
        if (profileId.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profileId.get());
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<PatientResource> getPatientByProfileId(@PathVariable Long profileId) {
        var getPatientByProfileIdQuery = new GetPatientByProfileIdQuery(new ProfileId(profileId));
        var patient = patientQueryService.handle(getPatientByProfileIdQuery);
        if (patient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return ResponseEntity.ok(patientResource);
    }

    @GetMapping("/record/{patientRecordId}")
    public ResponseEntity<PatientResource> getPatientByPatientRecordId(@PathVariable String patientRecordId) {
        var PatientRecordId = new PatientRecordId(patientRecordId);
        var getPatientByPatientRecordIdQuery = new GetPatientByPatientRecordIdQuery(PatientRecordId);
        var patient = patientQueryService.handle(getPatientByPatientRecordIdQuery);
        if (patient.isEmpty()) {return ResponseEntity.notFound().build();}
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return ResponseEntity.ok(patientResource);
    }


    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long patientId) {
        var getPatientByIdQuery = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientByIdQuery);
        if (patient.isEmpty()) return ResponseEntity.notFound().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return ResponseEntity.ok(patientResource);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PatientResource>> getAllPatientsByDoctorId(@PathVariable Long doctorId) {
        var getAllPatientsByDoctorIdQuery = new GetAllPatientsByDoctorIdQuery(doctorId);
        var patients = patientQueryService.handle(getAllPatientsByDoctorIdQuery);
        if (patients.isEmpty()) return ResponseEntity.notFound().build();
        var patientResources = patients.stream()
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientResources);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResource> updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource updatePatientResource) {
        var updatePatientCommand = UpdatePatientCommandFromResourceAssembler.toCommandFromResource(patientId, updatePatientResource);
        var updatedPatient = patientCommandService.handle(updatePatientCommand);
        if (updatedPatient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(updatedPatient.get());
        return ResponseEntity.ok(patientResource);
    }

    @PutMapping("/personal-history/{patientId}")
    public ResponseEntity<PatientResource> updatePatientPersonalHistory(@PathVariable Long patientId, @RequestBody UpdatePersonalHistoryPatientResource updatePersonalHistoryPatientResource) {
        var updatePersonalHistoryCommand = UpdatePersonalHistoryPatientCommandFromResourceAssembler.toCommandFromResource(patientId, updatePersonalHistoryPatientResource);
        var updatedPatient = patientCommandService.handle(updatePersonalHistoryCommand);
        if (updatedPatient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(updatedPatient.get());
        return ResponseEntity.ok(patientResource);
    }

    @PutMapping("/family-history/{patientId}")
    public ResponseEntity<PatientResource> updatePatientFamilyHistory(@PathVariable Long patientId, @RequestBody UpdateFamilyHistoryPatientResource updateFamilyHistoryPatientResource) {
        var updateFamilyHistoryCommand = UpdateFamilyHistoryPatientCommandFromResourceAssembler.toCommandFromResource(patientId, updateFamilyHistoryPatientResource);
        var updatedPatient = patientCommandService.handle(updateFamilyHistoryCommand);
        if (updatedPatient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(updatedPatient.get());
        return ResponseEntity.ok(patientResource);
    }

    @PutMapping("/doctor/{patientId}")
    public ResponseEntity<PatientResource> updatePatientDoctorId(@PathVariable Long patientId, @RequestBody UpdatePatientDoctorIdResource updatePatientDoctorIdResource) {
        var updatePatientDoctorIdCommand = UpdatePatientDoctorIdCommandFromResourceAssembler.toCommandFromResource(patientId, updatePatientDoctorIdResource);
        var updatedPatient = patientCommandService.handle(updatePatientDoctorIdCommand);
        if (updatedPatient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(updatedPatient.get());
        return ResponseEntity.ok(patientResource);
    }

}


