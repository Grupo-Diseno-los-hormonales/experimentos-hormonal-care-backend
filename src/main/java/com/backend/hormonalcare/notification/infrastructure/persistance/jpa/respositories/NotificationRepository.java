package com.backend.hormonalcare.notification.infrastructure.persistance.jpa.respositories;

import com.backend.hormonalcare.notification.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long recipientId);
}
