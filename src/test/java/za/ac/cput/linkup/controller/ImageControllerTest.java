package za.ac.cput.linkup.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.linkup.controller.ImageController;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.factory.ImageFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImageControllerTest {

    private final ImageController imageController;

    public static Image image = ImageFactory
            .createImage(99876543,8765432, "https://awebsite.com/an-image.jpg");

    @Autowired
    ImageControllerTest(ImageController imageController) {
        this.imageController = imageController;
    }


    @Test
    @Order(2)
    void getImageById() {
        Image img = imageController.getImageById(image.getImageId()).getBody();
        assertNotNull(img);
        System.out.println("Image found: " + img);
    }

    @Test
    @Order(1)
    void createImage() {
        Image createdImage = imageController.createImage(image).getBody();
        assertNotNull(createdImage);
        System.out.println("Created: " + createdImage);
    }

    @Test
    void updateImage() {
        Image updatedImage = new Image.Builder()
                .copy(image)
                .setUserId(8765432)
                .setImageUrl("https://awebsite.com/updated_image.jpg")
                .build();

        Image img = imageController.updateImage(updatedImage).getBody();
        assertNotNull(img);
        System.out.println("Updated: " + img);
    }

    @Test
    void deleteImage() {
        imageController.deleteImage(image.getImageId());
        assertNull(imageController.getImageById(image.getImageId()).getBody());
        System.out.println("Deleted: " + image);
    }
}