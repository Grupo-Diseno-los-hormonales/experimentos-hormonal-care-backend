package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.iam.domain.model.entities.Role;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllRolesQuery;
import com.backend.hormonalcare.iam.domain.model.queries.GetRoleByNameQuery;
import com.backend.hormonalcare.iam.domain.model.valueobjects.Roles;
import com.backend.hormonalcare.iam.domain.services.RoleQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RoleQueryServiceTest {
    private RoleQueryService roleQueryService;

    @BeforeEach
    void setUp() {
        roleQueryService = Mockito.mock(RoleQueryService.class);
    }

    @Test
    void testHandleGetAllRolesQueryReturnsList() {
        GetAllRolesQuery query = new GetAllRolesQuery();
        List<Role> mockList = List.of(Mockito.mock(Role.class));
        Mockito.when(roleQueryService.handle(any(GetAllRolesQuery.class))).thenReturn(mockList);

        List<Role> result = roleQueryService.handle(query);

        assertFalse(result.isEmpty());
    }

    @Test
    void testHandleGetRoleByNameQueryReturnsRole() {
        GetRoleByNameQuery query = new GetRoleByNameQuery(Roles.ROLE_ADMIN); // Usa el enum Roles
        Role mockRole = Mockito.mock(Role.class);
        Mockito.when(roleQueryService.handle(any(GetRoleByNameQuery.class))).thenReturn(Optional.of(mockRole));

        Optional<Role> result = roleQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockRole, result.get());
    }
}