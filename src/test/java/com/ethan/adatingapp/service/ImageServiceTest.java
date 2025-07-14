package com.ethan.adatingapp.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.factory.ImageFactory;

import static com.ethan.adatingapp.util.Base64ImageUtil.convertBase64ToBytes;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImageServiceTest {
    private final ImageService imageService;

    String dataUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAhCAYAAABX5MJvAAAAAXNSR0IArs4c6QAAAj9JREFUWEftVjFvEzEY/T5bSEUsjVSMsqV3HRgD16EbysYGa7eywZZOlH8QpnaDrYxswD+ACSRq6MaSS7MRrkh0qcpQn+FLMbiXc+zjKhSk3HQ6+973/L7nZyPMwIMzwAHmJEwXgpQ4aLcWr3D+rWrrjpVqLO8Pj3z//Z8kGOJd38pyrV/SnFpKZElM0i+WFRMy9aqXJbF2EB0JmTaLYxOAWRIfAEDLtdqaJAh2KGS6bOOXkfi1Ct0Flb+hyTmDBYb8Lb1XIaEQO/z09MyYnN0CwJ0yDCcJ6v3SXv8V/WTvjiokbE98XV25Y7xSxHCSQMSdq3v9zYsicbi6sq217oYqQbKvAcB3IdPLF0XCMutrIdPOVE98brdanHMyJwDgIyH7vbrtGN2MugxxewzJVFO8H46mkqDBLIk+AeD1s4l6/1jlHZOYVTwBJ6oJC3wXEG4bLCEHN7xb1ExwbdVKJM5XK82Isd6uPKDvX5LoPgI+sedcUqrR8JwHxbDSoB9ck4Onrlre9LOUMSk6ETY2uKXgkZBpY9oizVgwifOGhWdCpveKBbIk3v2ZiBsuA9ZWggAOk/iFBrAOMOwxhHe5ztcAcOt3EQ3PxYd0PUQFryfKQMp8Ys/z9b8MM7gdk9JHDwGwZ74z0FtLcvA4dPXenAgFypLoIwC2KUvK9n8ozl8rQQXmJP5kxz9uB90Hij3OtaZLCt3ChgxxfEzbj7mP+LwR7Ikp90ZnjZBzplJOzAQJn6R1xoPbUaeI7985CaPQD273HzEp88iMAAAAAElFTkSuQmCC";
    byte[] imageBytes = convertBase64ToBytes(dataUrl);

    public Image image = ImageFactory
            .createImage(124235345,123456789, imageBytes);

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
                .setImageUrl(imageBytes)
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