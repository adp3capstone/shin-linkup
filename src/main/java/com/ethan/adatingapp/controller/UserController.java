package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.User;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
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

        if (user.getEmail() == null) {
            errors.add("A valid email is required");
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

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam long userId) {
        userService.delete(userId);
        if (userService.read(userId) != null) {
            return ResponseEntity
                    .status(409)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
