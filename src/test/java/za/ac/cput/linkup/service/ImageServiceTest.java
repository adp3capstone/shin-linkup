package za.ac.cput.linkup.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.factory.ImageFactory;
import za.ac.cput.linkup.service.ImageService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImageServiceTest {
    private final ImageService imageService;

    public static Image image = ImageFactory
            .createImage(124235345,123456789, "https://example.com/image.jpg");

    public ImageServiceTest(ImageService imageService) {
        this.imageService = imageService;
    }

    @Test
    @Order(1)
    void create() {
        Image savedImage = imageService.create(image);
        assertNotNull(savedImage);
        System.out.println("Created: " + savedImage);
    }

    @Test
    @Order(2)
    void read() {
        Image readImage = imageService.read(image.getImageId());
        assertNotNull(readImage);
        System.out.println("Read: " + readImage);
    }

    @Test
    @Order(3)
    void update() {
        Image updatedImage = new Image.Builder()
                .copy(image)
                .setUserId(123456789)
                .setImageUrl("https://example.com/updated_image.jpg")
                .build();

        Image savedImage = imageService.update(updatedImage);
        assertNotNull(savedImage);
        System.out.println("Updated: " + savedImage);
    }

    @Test
    @Order(4)
    void delete() {
        imageService.delete(image.getImageId());
        Image deletedImage = imageService.read(image.getImageId());
        assertNull(deletedImage);
        System.out.println("Deleted: " + deletedImage);
    }
}