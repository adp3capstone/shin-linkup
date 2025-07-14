package za.ac.cput.linkup.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.factory.ImageFactory;
import za.ac.cput.linkup.repository.ImageRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageRepositoryTest {

    ImageRepository imageRepository;

    @Autowired
    public ImageRepositoryTest(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Test
    void createImage() {
        Image image = ImageFactory.createImage(5L, 123456789L, "https://example.com/image.jpg");
        Image savedImage = imageRepository.save(image);
        assertNotNull(savedImage);
        assertEquals(image.getImageId(), savedImage.getImageId());
    }

    @Test
    void readImage() {
        Image image = ImageFactory.createImage(5L, 123456789L, "https://example.com/image.jpg");
        imageRepository.save(image);
        Image foundImage = imageRepository.findById(image.getImageId()).orElse(null);
        assertNotNull(foundImage);
        assertEquals(image.getImageUrl(), foundImage.getImageUrl());
    }
}