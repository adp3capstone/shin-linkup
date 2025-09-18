package za.ac.cput.linkup.controller;

import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.PreferenceService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.PreferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pref")
@CrossOrigin(origins = "http://localhost:8081")
public class PreferenceController {
    private final PreferenceService preferenceService;
    private final UserService userService;

    @Autowired
    public PreferenceController(PreferenceService preferenceService, UserService userService) {
        this.preferenceService = preferenceService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PreferenceDTO> createPreference(@RequestBody Preference preference) {
        Long userId = preference.getUser().getUserId();
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        preference.setUser(user);
        Preference saved = preferenceService.create(preference);
        PreferenceDTO dto = new PreferenceDTO(saved);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferenceDTO> getPreference(@PathVariable Long id) {
        Preference preference = preferenceService.read(id);
        if (preference != null) {
            PreferenceDTO dto = new PreferenceDTO(preference);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<PreferenceDTO> getPreferenceByUser(@PathVariable Long id) {
        Preference preference = preferenceService.findByUser(id);
        if (preference != null) {
            PreferenceDTO dto = new PreferenceDTO(preference);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<PreferenceDTO> updatePreference(@RequestBody Preference preference) {
        Preference updatedPreference = preferenceService.createOrUpdatePreference(preference);
        if (updatedPreference != null) {
            PreferenceDTO dto = new PreferenceDTO(updatedPreference);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePreference(@RequestParam Long id) {
        preferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
