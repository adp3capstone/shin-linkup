package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import com.ethan.adatingapp.service.PreferenceService;
import com.ethan.adatingapp.service.UserService;
import com.ethan.adatingapp.util.AuthRequest;
import com.ethan.adatingapp.util.AuthResponse;
import com.ethan.adatingapp.util.JwtUtil;
import com.ethan.adatingapp.util.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    private final UserService userService;
    private final PreferenceService preferenceService;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, PreferenceService preferenceService,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.preferenceService = preferenceService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User foundUser = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

//        String token = jwtUtil.generateToken(request.getUsername());

        String token = foundUser.getUserId().toString();
        UserDTO userDTO = new UserDTO(foundUser);

        return ResponseEntity.ok(new AuthResponse(token, userDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        List<String> errors = new ArrayList<>();

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            errors.add("First name is required");
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            errors.add("Last name is required");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errors.add("A valid email is required");
        } else if (userService.findByEmail(user.getEmail()) != null) {
            errors.add("Email is already in use");
        }

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            errors.add("Username is required");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errors.add("Password is required");
        }

        if (user.getAge() < 1) {
            errors.add("Age must be at least 1");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long userId) {
        User user = userService.read(userId);
        Preference preferences = preferenceService.findByUser(userId);

        if (user != null) {
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user) {
        User updatedUser = userService.update(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @DeleteMapping("/{userId}")
public ResponseEntity<String> deleteUser(@PathVariable long userId) {
    boolean deleted = userService.delete(userId);
    if (deleted) {
        return ResponseEntity.ok("User deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
}

    @DeleteMapping("/{userId}/schedule-deletion")
    public ResponseEntity<String> scheduleUserDeletion(@PathVariable long userId) {
        User user = userService.read(userId);
        if (user == null) return ResponseEntity.notFound().build();

        if (user.getDeletionDueDate() != null) {
            return ResponseEntity.badRequest().body("Deletion already scheduled for " + user.getDeletionDueDate());
        }

        user.setDeletionDueDate(LocalDateTime.now().plusDays(5));
        userService.update(user);

        return ResponseEntity.ok("User account scheduled for deletion.");
    }

    @PostMapping("/{userId}/cancel-deletion")
    public ResponseEntity<String> cancelUserDeletion(@PathVariable long userId) {
        User user = userService.read(userId);
        if (user == null) return ResponseEntity.notFound().build();

        if (user.getDeletionDueDate() == null) {
            return ResponseEntity.badRequest().body("No scheduled deletion found.");
        }

        if (user.getDeletionDueDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Deletion date has already passed.");
        }

        user.setDeletionDueDate(null);
        userService.update(user);

        return ResponseEntity.ok("User deletion cancelled.");
    }
    //Users Filters for feed:
    @GetMapping("/by-course")
    public ResponseEntity<List<UserDTO>> getUsersByCourse(@RequestParam Course course) {
        List<User> users = userService.findAllByCourse(course);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-institution")
    public ResponseEntity<List<UserDTO>> getUsersByInstitution(@RequestParam Institution institution) {
        List<User> users = userService.findAllByInstitution(institution);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-age")
    public ResponseEntity<List<UserDTO>> getUsersByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        List<User> users = userService.findAllByAgeBetween(minAge, maxAge);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-gender")
    public ResponseEntity<List<UserDTO>> getUsersByGender(@RequestParam Gender gender) {
        List<User> users = userService.findAllByGender(gender);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/by-interests")
    public ResponseEntity<List<UserDTO>> getUsersByInterests(@RequestParam List<Interest> interests) {
        List<User> users = userService.findAllByInterestsIn(interests);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            Preference preferences = preferenceService.findByUser(user.getUserId());
            if (preferences != null) {
                user.setPreferences(preferences);
            }
            userDTOs.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }
}
