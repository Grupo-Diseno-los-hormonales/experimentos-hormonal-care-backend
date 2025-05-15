package com.backend.core.integration.tests;

import com.backend.hormonalcare.iam.domain.model.entities.Role;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllRolesQuery;
import com.backend.hormonalcare.iam.domain.services.RoleQueryService;
import com.backend.hormonalcare.iam.interfaces.rest.RolesController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RolesController.class)
class RolesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleQueryService roleQueryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllRoles_ReturnsOk_WhenRolesExist() throws Exception {
        Mockito.when(roleQueryService.handle(Mockito.any(GetAllRolesQuery.class)))
                .thenReturn(List.of(Mockito.mock(Role.class)));

        mockMvc.perform(get("/api/v1/roles"))
                .andExpect(status().isOk());
    }
}