package za.ac.cput.linkup.controller;

/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.service.ImageService;

import java.net.URI;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable long imageId) {
        Image image = imageService.read(imageId);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        Image createdImage = imageService.create(image);
        return ResponseEntity
                .created(URI.create("/image/"+ createdImage.getImageId()))
                .body(createdImage);
    }

    @PutMapping
    public ResponseEntity<Image> updateImage(@RequestBody Image image) {
        Image updatedImage = imageService.update(image);
        if (updatedImage != null) {
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
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
