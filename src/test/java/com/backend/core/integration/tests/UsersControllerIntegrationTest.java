package com.backend.core.integration.tests;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllUsersQuery;
import com.backend.hormonalcare.iam.domain.model.queries.GetUserByIdQuery;
import com.backend.hormonalcare.iam.domain.services.UserQueryService;
import com.backend.hormonalcare.iam.interfaces.rest.UsersController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class UsersControllerIntegrationTest {

    private UserQueryService userQueryService;
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        userQueryService = Mockito.mock(UserQueryService.class);
        usersController = new UsersController(userQueryService);
    }

    @Test
    void getAllUsers_ReturnsOk_WhenUsersExist() {
        User mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getUsername()).thenReturn("testuser");
        Mockito.when(userQueryService.handle(any(GetAllUsersQuery.class)))
                .thenReturn(List.of(mockUser));

        var response = usersController.getAllUsers();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals("testuser", response.getBody().get(0).username());
    }

    @Test
    void getUserById_ReturnsOk_WhenUserExists() {
        User mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getUsername()).thenReturn("testuser");
        Mockito.when(userQueryService.handle(any(GetUserByIdQuery.class)))
                .thenReturn(Optional.of(mockUser));

        var response = usersController.getUserById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().username());
    }

    @Test
    void getUserById_ReturnsNotFound_WhenUserNotExists() {
        Mockito.when(userQueryService.handle(any(GetUserByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = usersController.getUserById(1L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}