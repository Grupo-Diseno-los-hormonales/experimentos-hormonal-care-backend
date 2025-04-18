package com.backend.hormonalcare.profile.infrastructure.persistence.jpa.repositories;

import com.backend.hormonalcare.profile.domain.model.aggregates.Profile;
import com.backend.hormonalcare.profile.domain.model.valueobjects.PersonName;
import com.backend.hormonalcare.profile.domain.model.valueobjects.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(PersonName name);
    Optional<Profile> findByPhoneNumber(PhoneNumber phoneNumber);
    boolean existsById(Long id);
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
    boolean existsByUserId(Long userId);
    Optional<Profile> findByUserId(Long userId);
}
