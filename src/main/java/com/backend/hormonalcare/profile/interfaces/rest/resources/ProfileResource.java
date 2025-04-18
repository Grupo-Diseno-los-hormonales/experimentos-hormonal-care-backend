package com.backend.hormonalcare.profile.interfaces.rest.resources;

import java.util.Date;

public record ProfileResource
        (
        Long id,
        String fullName,
        String gender,
        String phoneNumber,
        String image,
        Date birthday,
        Long userId)
{
}
