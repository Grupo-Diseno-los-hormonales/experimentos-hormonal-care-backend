package com.backend.hormonalcare.notification.domain.services;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import com.backend.hormonalcare.notification.domain.model.queries.GetAllNotificationsByRecipientIdQuery;
import com.backend.hormonalcare.notification.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsByRecipientIdQuery query);
    Optional<Notification> handle(GetNotificationByIdQuery query);

}
