package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.iam.domain.model.commands.SeedRolesCommand;
import com.backend.hormonalcare.iam.domain.services.RoleCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoleCommandServiceTest {
    private RoleCommandService roleCommandService;

    @BeforeEach
    void setUp() {
        roleCommandService = Mockito.mock(RoleCommandService.class);
    }

    @Test
    void testHandleSeedRolesCommand() {
        SeedRolesCommand command = new SeedRolesCommand();
        roleCommandService.handle(command);
        Mockito.verify(roleCommandService).handle(command);
    }
}