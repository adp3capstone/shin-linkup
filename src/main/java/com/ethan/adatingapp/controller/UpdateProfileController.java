package com.ethan.adatingapp.controller;
/* Update Profile.java
Author: Anita Lottering (222141395)
Date:10 July 2025
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/update-profile")
    @CrossOrigin(origins = "http://localhost:8081")
    public class UpdateProfileController {

        @Autowired
        private UpdateProfileService service;

        @PostMapping("/create")
        public ResponseEntity<UpdateProfile> create(@RequestBody UpdateProfile updateProfile) {
            return ResponseEntity.ok(service.save(updateProfile));
        }

        @PutMapping("/update")
        public ResponseEntity<UpdateProfile> update(@RequestBody UpdateProfile updateProfile) {
            // Directly save the incoming object
            return ResponseEntity.ok(service.update(updateProfile));
        }

        @GetMapping("/read/{userId}")
        public ResponseEntity<UpdateProfile> read(@PathVariable Long userId) {
            return service.read(userId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/delete/{userId}")
        public ResponseEntity<Void> delete(@PathVariable Long userId) {
            service.delete(userId);
            return ResponseEntity.noContent().build();
        }
    }

}
