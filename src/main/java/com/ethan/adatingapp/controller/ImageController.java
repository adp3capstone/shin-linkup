package com.ethan.adatingapp.controller;

/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.UserService;
import com.ethan.adatingapp.util.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.service.ImageService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:8081")
public class ImageController {
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public ImageController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDTO> getImageById(@PathVariable long imageId) {
        Image image = imageService.read(imageId);
        if (image != null) {
            ImageDTO imageDTO = new ImageDTO(image.getImageId(), image.getUser().getUserId(), image.getImageUrl());
            return ResponseEntity.ok(imageDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        List<Image> existingImages = imageService.findByUserId(image.getUser().getUserId());
        if (!existingImages.isEmpty()) {
            return ResponseEntity
                    .status(409)
                    .body(null); // Conflict: User already has an image
        }

        Long userId = image.getUser().getUserId();
        User user = userService.read(userId);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        image.setUser(user);

        Image createdImage = imageService.create(image);
        return ResponseEntity
                .created(URI.create("/image/"+ createdImage.getImageId()))
                .body(createdImage);
    }

    @PatchMapping("/update")
    @Transactional
    public ResponseEntity<ImageDTO> patchImage(@RequestBody Map<String, Object> payload) {
        // Extract userId and new image info from request body
        Long userId = Long.valueOf(payload.get("userId").toString());
        String newImageUrl = payload.containsKey("imageUrl") ? (String) payload.get("imageUrl") : null;
        String newBase64 = payload.containsKey("base64String") ? (String) payload.get("base64String") : null;

        // Find existing image for the user
        List<Image> existingImages = imageService.findByUserId(userId);
        if (existingImages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Image existingImage = existingImages.get(0); // assume 1 image per user

        // Update only the fields provided
        if (newImageUrl != null) {
            existingImage.setImageUrl(newImageUrl.getBytes());
        }
        if (newBase64 != null) {
            existingImage.setImageUrl(newBase64.getBytes());
        }

        Image updatedImage = imageService.update(existingImage);
        ImageDTO dto = new ImageDTO(updatedImage.getUser().getUserId(), updatedImage.getImageId(), updatedImage.getImageUrl());
        return ResponseEntity.ok(dto);
    }

    @PatchMapping
    public ResponseEntity<Image> updateImage(@RequestBody Image image) {
        Image updatedImage = imageService.update(image);
        if (updatedImage != null) {
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteImage(@RequestParam long imageId) {
        imageService.delete(imageId);
        if (imageService.read(imageId) != null) {
            return ResponseEntity
                    .status(409)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
