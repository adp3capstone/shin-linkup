package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

    // Optional: find by User ID
    Optional<EmergencyContact> findByUserUserId(Long userId);

    // Optional: delete by User ID
    void deleteByUserUserId(Long userId);
}
