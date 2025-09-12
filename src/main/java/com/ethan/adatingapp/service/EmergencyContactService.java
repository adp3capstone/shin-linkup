package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.repository.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyContactService implements IEmergencyContactService {

    private final EmergencyContactRepository repository;

    @Autowired
    public EmergencyContactService(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmergencyContact create(EmergencyContact entity) {
        if (entity == null) return null;
        return repository.save(entity);
    }

    @Override
    public EmergencyContact read(Long id) {
        if (id == null) return null;
        Optional<EmergencyContact> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public EmergencyContact update(EmergencyContact emergencyContact) {
        if (emergencyContact == null || emergencyContact.getEmergencyContactId() == null) return null;
        if (!repository.existsById(emergencyContact.getEmergencyContactId())) return null;
        return repository.save(emergencyContact);
    }

    @Override
    public boolean delete(long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
    @Override
    public EmergencyContact findById(Long id) {
        if (id == null) return null;
        return repository.findById(id).orElse(null);
    }

    @Override
    public Optional<EmergencyContact> findByUser(Long userId) {
        if (userId == null || userId <= 0) return null;
        return repository.findByUserUserId(userId); // make sure this method exists in your repository
    }



}
