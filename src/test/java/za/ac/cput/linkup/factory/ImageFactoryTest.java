package za.ac.cput.linkup.factory;

/* Image Factory Test.java
Author: Ethan Le Roux (222622172)
Date:18 May 2025
*/

import org.junit.jupiter.api.Test;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.factory.ImageFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageFactoryTest {
    public static Image dummyImage = ImageFactory.createImage(
            199999,
            1098690,
            "https://example.com/dummy.jpg"
    );

    @Test
    public void createImage() {
        assertNotNull(dummyImage);
        System.out.println(dummyImage.toString());
    }
}