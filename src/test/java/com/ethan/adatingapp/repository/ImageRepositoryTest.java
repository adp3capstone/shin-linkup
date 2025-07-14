package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.factory.ImageFactory;

import static com.ethan.adatingapp.util.Base64ImageUtil.convertBase64ToBytes;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageRepositoryTest {

    ImageRepository imageRepository;

    String dataUrl = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAhCAYAAABX5MJvAAAAAXNSR0IArs4c6QAAAj9JREFUWEftVjFvEzEY/T5bSEUsjVSMsqV3HRgD16EbysYGa7eywZZOlH8QpnaDrYxswD+ACSRq6MaSS7MRrkh0qcpQn+FLMbiXc+zjKhSk3HQ6+973/L7nZyPMwIMzwAHmJEwXgpQ4aLcWr3D+rWrrjpVqLO8Pj3z//Z8kGOJd38pyrV/SnFpKZElM0i+WFRMy9aqXJbF2EB0JmTaLYxOAWRIfAEDLtdqaJAh2KGS6bOOXkfi1Ct0Flb+hyTmDBYb8Lb1XIaEQO/z09MyYnN0CwJ0yDCcJ6v3SXv8V/WTvjiokbE98XV25Y7xSxHCSQMSdq3v9zYsicbi6sq217oYqQbKvAcB3IdPLF0XCMutrIdPOVE98brdanHMyJwDgIyH7vbrtGN2MugxxewzJVFO8H46mkqDBLIk+AeD1s4l6/1jlHZOYVTwBJ6oJC3wXEG4bLCEHN7xb1ExwbdVKJM5XK82Isd6uPKDvX5LoPgI+sedcUqrR8JwHxbDSoB9ck4Onrlre9LOUMSk6ETY2uKXgkZBpY9oizVgwifOGhWdCpveKBbIk3v2ZiBsuA9ZWggAOk/iFBrAOMOwxhHe5ztcAcOt3EQ3PxYd0PUQFryfKQMp8Ys/z9b8MM7gdk9JHDwGwZ74z0FtLcvA4dPXenAgFypLoIwC2KUvK9n8ozl8rQQXmJP5kxz9uB90Hij3OtaZLCt3ChgxxfEzbj7mP+LwR7Ikp90ZnjZBzplJOzAQJn6R1xoPbUaeI7985CaPQD273HzEp88iMAAAAAElFTkSuQmCC";
    byte[] imageBytes = convertBase64ToBytes(dataUrl);

    @Autowired
    public ImageRepositoryTest(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Test
    void createImage() {
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
        Image image = ImageFactory.createImage(user,  imageBytes);
        assertNull(image.getImageId());

        Image savedImage = imageRepository.save(image);
        assertNotNull(savedImage.getImageId());
        System.out.println("Generated ID: " + savedImage.getImageId());
    }

    @Test
    void readImage() {
        Image foundImage = imageRepository.findById(1L).orElse(null);
        assertNotNull(foundImage);
    }
}