package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.EmergencyContact;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.repository.EmergencyContactRepository;
import za.ac.cput.linkup.repository.UserRepository;
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