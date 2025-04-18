package com.backend.hormonalcare.iam.domain.model.queries;

import com.backend.hormonalcare.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
