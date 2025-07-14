package za.ac.cput.linkup.service;

/* Image Service.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import org.springframework.stereotype.Service;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.repository.ImageRepository;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image create(Image image) {
        return imageRepository.save(image);
    }

    public Image read(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public Image update(Image image) {
        return imageRepository.save(image);
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

}
