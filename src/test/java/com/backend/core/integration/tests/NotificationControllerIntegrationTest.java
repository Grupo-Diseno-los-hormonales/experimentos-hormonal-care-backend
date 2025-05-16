package com.backend.core.integration.tests;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import com.backend.hormonalcare.notification.domain.model.commands.DeleteNotificationCommand;
import com.backend.hormonalcare.notification.domain.model.commands.UpdateNotificationStateCommand;
import com.backend.hormonalcare.notification.domain.model.queries.GetAllNotificationsByRecipientIdQuery;
import com.backend.hormonalcare.notification.domain.model.queries.GetNotificationByIdQuery;
import com.backend.hormonalcare.notification.domain.model.valueobjects.State;
import com.backend.hormonalcare.notification.domain.services.NotificationCommandService;
import com.backend.hormonalcare.notification.domain.services.NotificationQueryService;
import com.backend.hormonalcare.notification.interfaces.rest.NotificationController;
import com.backend.hormonalcare.notification.interfaces.rest.resources.UpdateNotificationStateResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class NotificationControllerIntegrationTest {

    private NotificationCommandService notificationCommandService;
    private NotificationQueryService notificationQueryService;
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        notificationCommandService = Mockito.mock(NotificationCommandService.class);
        notificationQueryService = Mockito.mock(NotificationQueryService.class);
        notificationController = new NotificationController(notificationCommandService, notificationQueryService);
    }

    /**
     * Test que verifica que si existe una notificación con el ID dado,
     * el controlador retorna HTTP 200 y el body no es null.
     */
    @Test
    void getNotificationById_ReturnsOk_WhenExists() {
        Notification mockNotification = Mockito.mock(Notification.class);
        // Mock necesario para evitar NullPointerException en el assembler
        Mockito.when(mockNotification.getId()).thenReturn(1L);
        Mockito.when(mockNotification.getCreatedAt()).thenReturn(new Date());
        Mockito.when(mockNotification.getTitle()).thenReturn("Titulo");
        Mockito.when(mockNotification.getMessage()).thenReturn("Mensaje");
        Mockito.when(mockNotification.getState()).thenReturn(State.UNREAD);
        Mockito.when(mockNotification.getRecipientId()).thenReturn(2L);

        Mockito.when(notificationQueryService.handle(any(GetNotificationByIdQuery.class)))
                .thenReturn(Optional.of(mockNotification));

        var response = notificationController.getNotificationById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    /**
     * Test que verifica que si NO existe una notificación con el ID dado,
     * el controlador retorna HTTP 404 y el body es null.
     */
    @Test
    void getNotificationById_ReturnsNotFound_WhenNotExists() {
        Mockito.when(notificationQueryService.handle(any(GetNotificationByIdQuery.class)))
                .thenReturn(Optional.empty());

        var response = notificationController.getNotificationById(1L);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }

    /**
     * Test que verifica que se retorna HTTP 200 y una lista no vacía al buscar por recipientId.
     */
    @Test
    void getNotificationsByRecipientId_ReturnsOk() {
        Notification mockNotification = Mockito.mock(Notification.class);
        Mockito.when(mockNotification.getId()).thenReturn(1L);
        Mockito.when(mockNotification.getCreatedAt()).thenReturn(new Date());
        Mockito.when(mockNotification.getTitle()).thenReturn("Titulo");
        Mockito.when(mockNotification.getMessage()).thenReturn("Mensaje");
        Mockito.when(mockNotification.getState()).thenReturn(State.UNREAD);
        Mockito.when(mockNotification.getRecipientId()).thenReturn(2L);

        Mockito.when(notificationQueryService.handle(any(GetAllNotificationsByRecipientIdQuery.class)))
                .thenReturn(List.of(mockNotification));

        var response = notificationController.getNotificationsByRecipientId(2L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }

    /**
     * Test que verifica que actualizar el estado de una notificación existente retorna HTTP 200.
     */
    @Test
    void updateNotificationState_ReturnsOk_WhenExists() {
        Notification mockNotification = Mockito.mock(Notification.class);
        Mockito.when(mockNotification.getId()).thenReturn(1L);
        Mockito.when(mockNotification.getCreatedAt()).thenReturn(new Date());
        Mockito.when(mockNotification.getTitle()).thenReturn("Titulo");
        Mockito.when(mockNotification.getMessage()).thenReturn("Mensaje");
        Mockito.when(mockNotification.getState()).thenReturn(State.READ);
        Mockito.when(mockNotification.getRecipientId()).thenReturn(2L);

        Mockito.when(notificationCommandService.handle(any(UpdateNotificationStateCommand.class)))
                .thenReturn(Optional.of(mockNotification));

        var resource = new UpdateNotificationStateResource(State.READ);
        var response = notificationController.updateNotificationState(1L, resource);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(State.READ, response.getBody().state());
    }

    /**
     * Test que verifica que actualizar el estado de una notificación inexistente retorna HTTP 404.
     */
    @Test
    void updateNotificationState_ReturnsNotFound_WhenNotExists() {
        Mockito.when(notificationCommandService.handle(any(UpdateNotificationStateCommand.class)))
                .thenReturn(Optional.empty());

        var resource = new UpdateNotificationStateResource(State.READ);
        var response = notificationController.updateNotificationState(1L, resource);

        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }

    /**
     * Test que verifica que eliminar una notificación retorna HTTP 204 (No Content).
     */
    @Test
    void deleteNotification_ReturnsNoContent() {
        Mockito.doNothing().when(notificationCommandService).handle(any(DeleteNotificationCommand.class));

        var response = notificationController.deleteNotification(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}