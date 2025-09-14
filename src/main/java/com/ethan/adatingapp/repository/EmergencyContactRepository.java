package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    public List<EmergencyContact> findAllByUser_userId(Long  userId);
}
