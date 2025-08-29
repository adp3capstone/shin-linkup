package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.PreferenceService;
import com.ethan.adatingapp.service.UserService;
import com.ethan.adatingapp.util.PreferenceDTO;
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
    public ResponseEntity<Preference> createPreference(@RequestBody Preference preference) {
        Long userId = preference.getUser().getUserId();
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        preference.setUser(user);
        Preference saved = preferenceService.create(preference);
        return ResponseEntity.ok(saved);
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

    @PutMapping("/{userId}")
    public ResponseEntity<Preference> updatePreference(
            @PathVariable Long userId,
            @RequestBody Preference preferenceDetails) {

        // 1. Check if user exists
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // 2. Get the user's existing preference
        Preference existingPreference = preferenceService.findByUser(userId);
        if (existingPreference == null) {
            return ResponseEntity.notFound().build();
        }

        // 3. Update fields (example: gender, age range, interests, etc.)
        existingPreference.setPreferredGender(preferenceDetails.getPreferredGender());
        existingPreference.setMinAge(preferenceDetails.getMinAge());
        existingPreference.setMaxAge(preferenceDetails.getMaxAge());
        existingPreference.setPreferredInterests(preferenceDetails.getPreferredInterests());
        existingPreference.setRelationshipType(preferenceDetails.getRelationshipType());
        existingPreference.setPreferredCourses(preferenceDetails.getPreferredCourses());
        existingPreference.setMaxDistance(preferenceDetails.getMaxDistance());
        existingPreference.setSmokingPreference(preferenceDetails.isSmokingPreference());
        existingPreference.setDrinkingPreference(preferenceDetails.isDrinkingPreference());


        // 4. Save updated preference
        Preference updatedPreference = preferenceService.update(existingPreference);
        return ResponseEntity.ok(updatedPreference);
    }


    @DeleteMapping
    public ResponseEntity<Void> deletePreference(@RequestParam Long id) {
        preferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
