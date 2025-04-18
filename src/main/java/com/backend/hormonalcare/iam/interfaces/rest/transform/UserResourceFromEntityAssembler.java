package com.backend.hormonalcare.iam.interfaces.rest.transform;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.domain.model.entities.Role;
import com.backend.hormonalcare.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}
