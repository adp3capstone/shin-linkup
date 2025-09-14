package com.ethan.adatingapp.controller;


import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.EmergencyContactService;
import com.ethan.adatingapp.service.UserService;
import com.ethan.adatingapp.util.EmergencyContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emergency-contacts")
@CrossOrigin(origins = "http://localhost:8081")
public class EmergencyContactController {

    private final EmergencyContactService service;
    private final UserService userService;

    @Autowired
    public EmergencyContactController(EmergencyContactService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<EmergencyContactDTO> create(@RequestBody EmergencyContact contact) {
        if (contact.getUser() == null || contact.getUser().getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }

        User emUser = userService.read(contact.getUser().getUserId());
        if (emUser == null) {
            return ResponseEntity.notFound().build();
        }

        contact.setUser(emUser);

        EmergencyContact created = service.create(contact);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        EmergencyContactDTO dto = new EmergencyContactDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmergencyContactDTO> read(@PathVariable Long id) {
        EmergencyContact contact = service.read(id);
        EmergencyContactDTO dto = new EmergencyContactDTO(contact);
        if (contact == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<EmergencyContactDTO>> getAll() {
        List<EmergencyContact> contacts = service.getAll();
        List<EmergencyContactDTO> dtos = contacts.stream().map(cntact -> new EmergencyContactDTO(cntact)).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/byuser/{id}")
    public ResponseEntity<List<EmergencyContactDTO>> getAll(@PathVariable Long id) {
        List<EmergencyContact> contacts = service.getAllByUser(id);
        List<EmergencyContactDTO> dtos = contacts.stream().map(cntact -> new EmergencyContactDTO(cntact)).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<EmergencyContactDTO> update(@RequestBody EmergencyContact contact) {
        EmergencyContact updated = service.update(contact);
        EmergencyContactDTO dto = new EmergencyContactDTO(updated);

        if (updated == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
