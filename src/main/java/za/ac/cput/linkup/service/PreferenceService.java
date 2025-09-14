package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.repository.PreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService {
private final PreferenceRepository preferenceRepository;

    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public Preference create(Preference preference) {
        return preferenceRepository.save(preference);
    }

    public Preference read(Long id) {
        return preferenceRepository.findById(id).orElse(null);
    }

    public Preference findByUser(Long userid) {
        return preferenceRepository.findByUserId(userid);
    }

    public Preference update(Preference preference) {
        return preferenceRepository.save(preference);
    }

    public void delete(Long id) {
        preferenceRepository.deleteById(id);
    }

    public List<Preference> findAll() {
        return preferenceRepository.findAll();
    }
}
