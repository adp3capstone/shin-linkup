package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.EmergencyContactRepository;
import com.ethan.adatingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService {

    private final EmergencyContactRepository repository;
    private final UserRepository userRepository;
    private final EmergencyContactRepository emergencyContactRepository;

    @Autowired
    public EmergencyContactService(EmergencyContactRepository repository, UserRepository userRepository, EmergencyContactRepository emergencyContactRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.emergencyContactRepository = emergencyContactRepository;
    }

    public EmergencyContact create(EmergencyContact emergencyContact) {
        if (emergencyContact == null)
            return null;
        return repository.save(emergencyContact);
    }

    public EmergencyContact read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<EmergencyContact> getAll() {
        return repository.findAll();
    }

    public EmergencyContact update(EmergencyContact emergencyContact) {
        if (emergencyContact == null || !repository.existsById(emergencyContact.getContactId()))
            return null;
        return repository.save(emergencyContact);
    }

    public List<EmergencyContact> getAllByUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        List<EmergencyContact> contacts = emergencyContactRepository.findAllByUser_userId(user.getUserId());
        return contacts;
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id))
            return false;
        repository.deleteById(id);
        return true;
    }
}