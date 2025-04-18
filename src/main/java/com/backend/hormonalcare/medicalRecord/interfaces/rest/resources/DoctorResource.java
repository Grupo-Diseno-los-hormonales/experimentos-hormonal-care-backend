package com.backend.hormonalcare.medicalRecord.interfaces.rest.resources;

public record DoctorResource(
        Long id,
        Long professionalIdentificationNumber,
        String subSpecialty,
        Long profileId,
        String doctorRecordId
) {
}
