package com.backend.hormonalcare.notification.interfaces.rest;


import com.backend.hormonalcare.notification.domain.model.commands.DeleteNotificationCommand;
import com.backend.hormonalcare.notification.domain.model.queries.GetAllNotificationsByRecipientIdQuery;
import com.backend.hormonalcare.notification.domain.model.queries.GetNotificationByIdQuery;
import com.backend.hormonalcare.notification.domain.services.NotificationCommandService;
import com.backend.hormonalcare.notification.domain.services.NotificationQueryService;
import com.backend.hormonalcare.notification.interfaces.rest.resources.CreateNotificationResource;
import com.backend.hormonalcare.notification.interfaces.rest.resources.NotificationResource;
import com.backend.hormonalcare.notification.interfaces.rest.resources.UpdateNotificationStateResource;
import com.backend.hormonalcare.notification.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.backend.hormonalcare.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.backend.hormonalcare.notification.interfaces.rest.transform.UpdateNotificationStateCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource){
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            var notification = notificationCommandService.handle(createNotificationCommand);
            if(notification.isEmpty()) return ResponseEntity.badRequest().build();
            var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
            return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/notifications/recipient/{recipientId}")
    public ResponseEntity<List<NotificationResource>> getNotificationsByRecipientId(@PathVariable Long recipientId) {
        var query = new GetAllNotificationsByRecipientIdQuery(recipientId);
        var notifications = notificationQueryService.handle(query);
        var notificationResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationResources);
    }

    @GetMapping("/{notificationId}")
public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
    var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
    var notification = notificationQueryService.handle(getNotificationByIdQuery);
    if (notification.isEmpty()) return ResponseEntity.notFound().build();
    var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
    return ResponseEntity.ok(notificationResource);
}


@PutMapping("/{notificationId}/state")
public ResponseEntity<NotificationResource> updateNotificationState(@PathVariable Long notificationId, @RequestBody UpdateNotificationStateResource resource) {
    var updateNotificationStateCommand = UpdateNotificationStateCommandFromResourceAssembler.toCommandFromResource(notificationId, resource);
    var updatedNotification = notificationCommandService.handle(updateNotificationStateCommand);
    if (updatedNotification.isEmpty()) return ResponseEntity.notFound().build();
    var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(updatedNotification.get());
    return ResponseEntity.ok(notificationResource);
}

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        var deleteNotificationCommand = new DeleteNotificationCommand(notificationId);
        notificationCommandService.handle(deleteNotificationCommand);
        return ResponseEntity.noContent().build();
    }
}
