package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.domain.model.queries.GetUserByIdQuery;
import com.backend.hormonalcare.iam.domain.model.queries.GetUserByUsernameQuery;
import com.backend.hormonalcare.iam.domain.model.queries.GetAllUsersQuery;
import com.backend.hormonalcare.iam.domain.services.UserQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserQueryServiceTest {
    private UserQueryService userQueryService;

    @BeforeEach
    void setUp() {
        userQueryService = Mockito.mock(UserQueryService.class);
    }

    @Test
    void testHandleGetUserByIdQueryReturnsUser() {
        GetUserByIdQuery query = new GetUserByIdQuery(1L);
        User mockUser = Mockito.mock(User.class);
        Mockito.when(userQueryService.handle(any(GetUserByIdQuery.class))).thenReturn(Optional.of(mockUser));

        Optional<User> result = userQueryService.handle(query);

        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
    }

    @Test
    void testHandleGetAllUsersQueryReturnsList() {
        GetAllUsersQuery query = new GetAllUsersQuery();
        List<User> mockList = List.of(Mockito.mock(User.class));
        Mockito.when(userQueryService.handle(any(GetAllUsersQuery.class))).thenReturn(mockList);

        List<User> result = userQueryService.handle(query);

        assertFalse(result.isEmpty());
    }
}