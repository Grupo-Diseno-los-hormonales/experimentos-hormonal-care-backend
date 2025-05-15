package com.backend.core.integration.tests;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllUsersQuery;
import com.backend.hormonalcare.iam.domain.model.queries.GetUserByIdQuery;
import com.backend.hormonalcare.iam.domain.services.UserQueryService;
import com.backend.hormonalcare.iam.interfaces.rest.UsersController;
import com.backend.hormonalcare.iam.interfaces.rest.resources.UserResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserQueryService userQueryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers_ReturnsOk_WhenUsersExist() throws Exception {
        Mockito.when(userQueryService.handle(Mockito.any(GetAllUsersQuery.class)))
                .thenReturn(List.of(Mockito.mock(User.class)));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_ReturnsOk_WhenUserExists() throws Exception {
        Mockito.when(userQueryService.handle(Mockito.any(GetUserByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(User.class)));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_ReturnsNotFound_WhenUserNotExists() throws Exception {
        Mockito.when(userQueryService.handle(Mockito.any(GetUserByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }
}