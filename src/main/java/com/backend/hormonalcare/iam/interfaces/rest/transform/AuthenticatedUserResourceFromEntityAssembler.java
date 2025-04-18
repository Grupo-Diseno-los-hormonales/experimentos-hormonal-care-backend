package com.backend.hormonalcare.iam.interfaces.rest.transform;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token, String role) {
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token, role);
    }
}
