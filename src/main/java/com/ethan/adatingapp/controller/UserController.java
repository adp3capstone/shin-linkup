package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.UserService;
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
@RequestMapping("/user/signup")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
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


//    @PostMapping
//    public ResponseEntity<User> createUser(User user) {
//
////        if(result.hasErrors()){
////            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
////        }
//
//        User createdUser = userService.create(user);
//        return ResponseEntity
//                .created(URI.create("/user/" + createdUser.getUserId()))
//                .body(createdUser);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId) {
        User user = userService.read(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
