package com.backend.core.entities.unit.tests;

import com.backend.hormonalcare.iam.domain.model.aggregates.User;
import com.backend.hormonalcare.iam.domain.model.commands.SignUpCommand;
import com.backend.hormonalcare.iam.domain.model.commands.SignInCommand;
import com.backend.hormonalcare.iam.domain.services.UserCommandService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserCommandServiceTest {
    private UserCommandService userCommandService;

    @BeforeEach
    void setUp() {
        userCommandService = Mockito.mock(UserCommandService.class);
    }

    @Test
    void testHandleSignUpCommandReturnsUser() {
        SignUpCommand command = new SignUpCommand("username", "password", List.of());
        User mockUser = Mockito.mock(User.class);
        Mockito.when(userCommandService.handle(any(SignUpCommand.class))).thenReturn(Optional.of(mockUser));

        Optional<User> result = userCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
    }

    @Test
    void testHandleSignInCommandReturnsPair() {
        SignInCommand command = new SignInCommand("username", "password");
        User mockUser = Mockito.mock(User.class);
        ImmutablePair<User, String> pair = new ImmutablePair<>(mockUser, "token");
        Mockito.when(userCommandService.handle(any(SignInCommand.class))).thenReturn(Optional.of(pair));

        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(pair, result.get());
    }
}