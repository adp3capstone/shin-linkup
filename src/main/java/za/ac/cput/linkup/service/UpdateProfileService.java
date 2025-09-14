package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.UpdateProfile;
import za.ac.cput.linkup.repository.UpdateProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateProfileService {

    private final UpdateProfileRepository repository;

    @Autowired
    public UpdateProfileService(UpdateProfileRepository repository) {
        this.repository = repository;
    }

    public UpdateProfile save(UpdateProfile updateProfile) {
        updateProfile.setCreatedAt(LocalDateTime.now());
        updateProfile.setUpdatedAt(LocalDateTime.now());
        return repository.save(updateProfile);
    }

    public UpdateProfile update(UpdateProfile updateProfile) {
        updateProfile.setUpdatedAt(LocalDateTime.now());
        return repository.save(updateProfile);
    }

    public Optional<UpdateProfile> read(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(Long userId) {
        Optional<UpdateProfile> updateProfile = repository.findByUserId(userId);
        updateProfile.ifPresent(repository::delete);
    }

    public List<UpdateProfile> findAll() {
        return repository.findAll();
    }

    public List<UpdateProfile> findByUpdateType(String updateType) {
        return repository.findByUpdateType(updateType);
    }
}