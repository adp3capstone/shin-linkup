package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.factory.ImageFactory;

import static com.ethan.adatingapp.util.Base64ImageUtil.convertBase64ToBytes;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImageControllerTest {

    private final ImageController imageController;

    String dataUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAhCAYAAABX5MJvAAAAAXNSR0IArs4c6QAAAj9JREFUWEftVjFvEzEY/T5bSEUsjVSMsqV3HRgD16EbysYGa7eywZZOlH8QpnaDrYxswD+ACSRq6MaSS7MRrkh0qcpQn+FLMbiXc+zjKhSk3HQ6+973/L7nZyPMwIMzwAHmJEwXgpQ4aLcWr3D+rWrrjpVqLO8Pj3z//Z8kGOJd38pyrV/SnFpKZElM0i+WFRMy9aqXJbF2EB0JmTaLYxOAWRIfAEDLtdqaJAh2KGS6bOOXkfi1Ct0Flb+hyTmDBYb8Lb1XIaEQO/z09MyYnN0CwJ0yDCcJ6v3SXv8V/WTvjiokbE98XV25Y7xSxHCSQMSdq3v9zYsicbi6sq217oYqQbKvAcB3IdPLF0XCMutrIdPOVE98brdanHMyJwDgIyH7vbrtGN2MugxxewzJVFO8H46mkqDBLIk+AeD1s4l6/1jlHZOYVTwBJ6oJC3wXEG4bLCEHN7xb1ExwbdVKJM5XK82Isd6uPKDvX5LoPgI+sedcUqrR8JwHxbDSoB9ck4Onrlre9LOUMSk6ETY2uKXgkZBpY9oizVgwifOGhWdCpveKBbIk3v2ZiBsuA9ZWggAOk/iFBrAOMOwxhHe5ztcAcOt3EQ3PxYd0PUQFryfKQMp8Ys/z9b8MM7gdk9JHDwGwZ74z0FtLcvA4dPXenAgFypLoIwC2KUvK9n8ozl8rQQXmJP5kxz9uB90Hij3OtaZLCt3ChgxxfEzbj7mP+LwR7Ikp90ZnjZBzplJOzAQJn6R1xoPbUaeI7985CaPQD273HzEp88iMAAAAAElFTkSuQmCC";
    byte[] imageBytes = convertBase64ToBytes(dataUrl);

    User user = new User.Builder()
            .setUserId(98765432L) // assuming userId is manually set here for the test
            .setUsername("testUser")
            .setPassword("password")
            .setEmail("test@example.com")
            .setFirstName("Test")
            .setLastName("User")
            .setAge(25)
            .setBio("This is a test user.")
            .build();

    public Image image = ImageFactory
            .createImage(user, imageBytes);

    @Autowired
    ImageControllerTest(ImageController imageController) {
        this.imageController = imageController;
    }


    @Test
    @Order(2)
    void getImageById() {
        Image img = imageController.getImageById(1L).getBody();
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
                .setImageUrl(imageBytes)
                .build();

        Image img = imageController.updateImage(updatedImage).getBody();
        assertNotNull(img);
        System.out.println("Updated: " + img);
    }

    @Test
    void deleteImage() {
        imageController.deleteImage(4);
        assertNull(imageController.getImageById(4).getBody());
        System.out.println("Deleted: " + image);
    }
}