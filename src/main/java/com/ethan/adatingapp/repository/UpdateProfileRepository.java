package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.UpdateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpdateProfileRepository extends JpaRepository<UpdateProfile, Long> {

    Optional<UpdateProfile> findByUserId(Long userId);

    List<UpdateProfile> findByUpdateType(String updateType);

    List<UpdateProfile> findByUserIdOrderByCreatedAtDesc(Long userId);
}