package com.backend.hormonalcare.communication.infrastrucutre.persistence.jpa.repositories;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunicationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findById(Long id);
    boolean existsById(Long id);
    void delete(Conversation conversation);

    @Query("SELECT c FROM Conversation c JOIN c.participants p WHERE p.userId = :userId")
    List<Conversation> findByParticipantUserId(Long userId);
}
