package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.EmergencyContact;

import java.util.Optional;

public interface IEmergencyContactService extends IService<EmergencyContact, Long> {
    boolean delete(long id);
    EmergencyContact findById(Long id);
    Optional<EmergencyContact> findByUser(Long userId);
}
