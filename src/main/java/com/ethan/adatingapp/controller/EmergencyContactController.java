package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.EmergencyContactService;
import com.ethan.adatingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/emergency-contact")
@CrossOrigin(origins = "http://localhost:9999")
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;
    private final UserService userService;

    @Autowired
    public EmergencyContactController(EmergencyContactService emergencyContactService,
                                      UserService userService) {
        this.emergencyContactService = emergencyContactService;
        this.userService = userService;
    }

    // Create EmergencyContact
    @PostMapping("/create")
    public ResponseEntity<?> createEmergencyContact(@RequestBody EmergencyContact contact) {
        // If the client supplied a userId, validate it and attach the real User
        if (contact.getUser() != null && contact.getUser().getUserId() != null) {
            User user = userService.read(contact.getUser().getUserId());
            if (user == null) {
                return ResponseEntity.badRequest().body("User does not exist");
            }
            contact.setUser(user);
        } else {
            // no user supplied — persist contact with user = null
            contact.setUser(null);
        }

        EmergencyContact created = emergencyContactService.create(contact);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Read EmergencyContact by ID
    @GetMapping("/read/{id}")
    public ResponseEntity<EmergencyContact> read(@PathVariable Long id) {
        EmergencyContact contact = emergencyContactService.read(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }

    // Update EmergencyContact
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmergencyContact contact) {
        if (contact.getEmergencyContactId() == null) {
            return ResponseEntity.badRequest().body("EmergencyContact ID is required");
        }

        EmergencyContact updated = emergencyContactService.update(contact);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Delete EmergencyContact
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        boolean deleted = emergencyContactService.delete(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Emergency contact deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Emergency contact not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Get all EmergencyContacts for a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<EmergencyContact> contact = emergencyContactService.findByUser(userId);
        if (contact == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contact);
    }



}
