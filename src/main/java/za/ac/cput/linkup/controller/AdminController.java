package za.ac.cput.linkup.controller;

/**
 * AdminController.java
 * Author: Ethan Le Roux (222622172)
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Role;
import za.ac.cput.linkup.factory.AdminFactory;
import za.ac.cput.linkup.factory.UserFactory;
import za.ac.cput.linkup.service.AdminService;
import za.ac.cput.linkup.util.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createAdminUser(@RequestBody AdminSignUpRequest user) {
        Admin buildUser = AdminFactory.createAdminUserForSignup(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );

        if(buildUser==null){
            return ResponseEntity.badRequest().body("Not all fields were completed correctly. Please try signing up again.");
        }

        buildUser.setRole(Role.ADMIN);

        Admin createdUser = adminService.save(buildUser);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDTO> login(@RequestBody AuthRequest request) {
        Admin foundUser = adminService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<TicketDTO> assignedTicketDTOS = foundUser.getAssignedTickets().stream().map(
                ticket -> new TicketDTO(
                        ticket.getTicketId(),
                        ticket.getUser().getUserId(),
                        ticket.getIssueType(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getCreatedAt(),
                        ticket.getUpdatedAt(),
                        ticket.getResolvedAt(),
                        ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null
        )).toList();
        List<TicketDTO> resolvedTicketDTOS = foundUser.getResolvedTickets().stream().map(
                ticket -> new TicketDTO(
                        ticket.getTicketId(),
                        ticket.getUser().getUserId(),
                        ticket.getIssueType(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getCreatedAt(),
                        ticket.getUpdatedAt(),
                        ticket.getResolvedAt(),
                        ticket.getResolvedBy() != null ? ticket.getResolvedBy().getUserId() : null
        )).toList();

        AdminDTO adminDTO = new AdminDTO(
                foundUser,
                assignedTicketDTOS,
                resolvedTicketDTOS
        );

        String token = foundUser.getUserId().toString();

        return ResponseEntity.ok(adminDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedDetails = adminService.findById(id).orElse(null);

        if(updatedDetails == null) {
            return ResponseEntity.notFound().build();
        }

        if(admin.getFirstName() != null) {
            updatedDetails.setFirstName(admin.getFirstName());
        }
        if(admin.getLastName() != null) {
            updatedDetails.setLastName(admin.getLastName());
        }
        if(admin.getEmail() != null) {
            updatedDetails.setEmail(admin.getEmail());
        }
        if(admin.getUsername() != null) {
            updatedDetails.setUsername(admin.getUsername());
        }
        if(admin.getPassword() != null) {
            updatedDetails.setPassword(admin.getPassword());
        }

        updatedDetails.setRole(Role.ADMIN);

        Admin updatedAdmin = adminService.update(updatedDetails);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
