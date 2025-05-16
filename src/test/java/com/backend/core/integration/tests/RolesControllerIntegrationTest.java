package com.backend.core.integration.tests;

import com.backend.hormonalcare.iam.domain.model.entities.Role;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllRolesQuery;
import com.backend.hormonalcare.iam.domain.model.valueobjects.Roles;
import com.backend.hormonalcare.iam.domain.services.RoleQueryService;
import com.backend.hormonalcare.iam.interfaces.rest.RolesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class RolesControllerIntegrationTest {

    private RoleQueryService roleQueryService;
    private RolesController rolesController;

    @BeforeEach
    void setUp() {
        roleQueryService = Mockito.mock(RoleQueryService.class);
        rolesController = new RolesController(roleQueryService);
    }

    /**
     * Test que verifica que el endpoint retorna HTTP 200 y una lista no vacía de roles.
     */
@Test
void getAllRoles_ReturnsOk_AndRoleResourceIsNotNull() {
    Role mockRole = Mockito.mock(Role.class);
    Mockito.when(mockRole.getId()).thenReturn(1L);
    Mockito.when(mockRole.getName()).thenReturn(Roles.ROLE_DOCTOR);

    Mockito.when(roleQueryService.handle(any(GetAllRolesQuery.class)))
            .thenReturn(List.of(mockRole));

    var response = rolesController.getAllRoles();

    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
    // Solo verifica que el recurso no es nulo y el id es correcto
    assertNotNull(response.getBody().get(0));
    assertEquals(1L, response.getBody().get(0).id());
}
}