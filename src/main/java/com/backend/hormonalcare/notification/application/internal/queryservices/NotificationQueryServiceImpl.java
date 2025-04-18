package com.backend.hormonalcare.notification.application.internal.queryservices;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import com.backend.hormonalcare.notification.domain.model.queries.GetAllNotificationsByRecipientIdQuery;
import com.backend.hormonalcare.notification.domain.model.queries.GetNotificationByIdQuery;
import com.backend.hormonalcare.notification.domain.services.NotificationQueryService;
import com.backend.hormonalcare.notification.infrastructure.persistance.jpa.respositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsByRecipientIdQuery query) {
        return notificationRepository.findByRecipientId(query.recipientId());
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }
}
