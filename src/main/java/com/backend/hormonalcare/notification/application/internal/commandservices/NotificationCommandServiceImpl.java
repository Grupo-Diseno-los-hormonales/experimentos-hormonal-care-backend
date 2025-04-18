package com.backend.hormonalcare.notification.application.internal.commandservices;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import com.backend.hormonalcare.notification.domain.model.commands.CreateNotificationCommand;
import com.backend.hormonalcare.notification.domain.model.commands.DeleteNotificationCommand;
import com.backend.hormonalcare.notification.domain.model.commands.UpdateNotificationStateCommand;
import com.backend.hormonalcare.notification.domain.services.NotificationCommandService;
import com.backend.hormonalcare.notification.infrastructure.persistance.jpa.respositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        var notification = new Notification(command);
        notificationRepository.save(notification);
        return Optional.of(notification);
    }

    @Override
    public Optional<Notification> handle(UpdateNotificationStateCommand command) {
        var notificationOptional = notificationRepository.findById(command.id());
        if (notificationOptional.isEmpty()) {
            throw new IllegalArgumentException("Notification with id " + command.id() + " not found");
        }

        var notificationToUpdate = notificationOptional.get();
        notificationToUpdate.updateState(command.state());
        notificationRepository.save(notificationToUpdate);
        return Optional.of(notificationToUpdate);
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        notificationRepository.deleteById(command.id());
    }
}
