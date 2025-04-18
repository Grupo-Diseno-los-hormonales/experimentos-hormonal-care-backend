package com.backend.hormonalcare.profile.interfaces.rest.resources;

import java.util.Date;

public record UpdateProfileResource(
        String firstName,
        String lastName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday
) {
}
