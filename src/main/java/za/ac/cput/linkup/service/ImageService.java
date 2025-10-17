package za.ac.cput.linkup.service;

/* Image Service.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.repository.ImageRepository;
import za.ac.cput.linkup.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public ImageService(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public Image create(Image image) {
        return imageRepository.save(image);
    }

    public Image createImage(User user, MultipartFile file) throws IOException {
        Image image = new Image.Builder()
                 .setUser(user)
                .setImageUrl(file.getBytes()) // store raw bytes in DB
                .build();

        return imageRepository.save(image);
    }

    public Image updateImage(Long userId, MultipartFile file) {
        // Try to find the user
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Try to find the existing image
        Image existingImage = imageRepository.findByUser(user);
        if (existingImage == null) {
            return null;
        }

        try {
            // Replace binary data
            existingImage.setImageUrl(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return imageRepository.save(existingImage);
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

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public  List<Image> findByUserId(Long userId) {
        return imageRepository.findAllByUser_UserId(userId);
    }

    public Image findOneImageByUserId(Long userId){
        return imageRepository.findByUser_UserId(userId);
    }
}
