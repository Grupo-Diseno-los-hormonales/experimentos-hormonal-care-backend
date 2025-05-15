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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationCommandService notificationCommandService;

    @MockBean
    private NotificationQueryService notificationQueryService;

    @Test
    void getNotificationById_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(notificationQueryService.handle(any(GetNotificationByIdQuery.class)))
                .thenReturn(Optional.of(Mockito.mock(Notification.class)));

        mockMvc.perform(get("/api/v1/notification/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getNotificationById_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(notificationQueryService.handle(any(GetNotificationByIdQuery.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/notification/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getNotificationsByRecipientId_ReturnsOk() throws Exception {
        Mockito.when(notificationQueryService.handle(any(GetAllNotificationsByRecipientIdQuery.class)))
                .thenReturn(List.of(Mockito.mock(Notification.class)));

        mockMvc.perform(get("/api/v1/notification/notifications/recipient/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateNotificationState_ReturnsOk_WhenExists() throws Exception {
        Mockito.when(notificationCommandService.handle(any(UpdateNotificationStateCommand.class)))
                .thenReturn(Optional.of(Mockito.mock(Notification.class)));

        mockMvc.perform(put("/api/v1/notification/1/state")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\":\"READ\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateNotificationState_ReturnsNotFound_WhenNotExists() throws Exception {
        Mockito.when(notificationCommandService.handle(any(UpdateNotificationStateCommand.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/notification/1/state")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\":\"READ\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNotification_ReturnsNoContent() throws Exception {
        Mockito.doNothing().when(notificationCommandService).handle(any(DeleteNotificationCommand.class));

        mockMvc.perform(delete("/api/v1/notification/1"))
                .andExpect(status().isNoContent());
    }
}