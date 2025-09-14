package za.ac.cput.linkup.controller;

/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.ImageService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("userId") Long userId,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            User user = new User();
            user.setUserId(userId);
            Image savedImage = imageService.createImage(user, imageFile);
            return ResponseEntity.ok(savedImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateImage(
            @RequestParam("userId") Long userId,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        User imgUser = userService.read(userId);

        if(imgUser!=null) {
            if(imgUser.getImage()==null){
                Image pic = imageService.createImage(imgUser,imageFile);
                ImageDTO dto = new ImageDTO(pic.getUser().getUserId(), pic.getImageId(), pic.getImageUrl());
                return ResponseEntity.ok(dto);
            }
        }
        Image updatedImage = imageService.updateImage(userId, imageFile);
        ImageDTO dto = new ImageDTO(updatedImage.getUser().getUserId(), updatedImage.getImageId(), updatedImage.getImageUrl());

        if (updatedImage == null) {
            return ResponseEntity.badRequest().body("Failed to update image: user or image not found, or invalid file.");
        }

        return ResponseEntity.ok(dto);
    }


    @PatchMapping("/update")
    @Transactional
    public ResponseEntity<ImageDTO> patchImage(@RequestBody Map<String, Object> payload) {
        // Extract userId and new image info from request body
        Long userId = Long.valueOf(payload.get("userId").toString());
        String newImageUrl = payload.containsKey("imageUrl") ? (String) payload.get("imageUrl") : null;

        // Find existing image for the user
        List<Image> existingImages = imageService.findByUserId(userId);
        if (existingImages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Image existingImage = existingImages.get(0); // assume 1 image per user

        // Update only the fields provided
        if (newImageUrl != null && !newImageUrl.isBlank()) {
            existingImage.setImageUrl(newImageUrl.getBytes()); // keep it as String
        }

        Image updatedImage = imageService.update(existingImage);

        // Make sure DTO uses consistent order
        ImageDTO dto = new ImageDTO(
                updatedImage.getImageId(),
                updatedImage.getUser().getUserId(),
                updatedImage.getImageUrl()
        );

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

    @GetMapping("/byid/{userId}")
    public ResponseEntity<ImageDTO> getImageByUserId(@PathVariable long userId) {
        Image image = imageService.findOneImageByUserId(userId);
        if (image != null) {
            ImageDTO imageDTO = new ImageDTO(image.getImageId(), image.getUser().getUserId(), image.getImageUrl());
            return ResponseEntity.ok(imageDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
