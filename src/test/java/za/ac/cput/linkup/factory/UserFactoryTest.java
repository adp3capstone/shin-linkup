package za.ac.cput.linkup.factory;

import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import org.junit.jupiter.api.Test;
import za.ac.cput.linkup.domain.enums.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void testCreateBasicUser_valid() {
        List<Interest> userInts = List.of(Interest.ART);
        Image img = new Image.Builder().setImageId(1L).setImageUrl("/9j8998977".getBytes()).build();
        User user = UserFactory.createBasicUser(
                "Ethan",
                "Smith",
                "ethan@example.com",
                "ethan123",
                "password123",
                21,
                "This is my bio",
                Institution.CAPE_PENINSULA_UNIVERSITY_OF_TECHNOLOGY,
                Gender.MALE,
                Course.BCOM_MKT,
                userInts,
                Orientation.STRAIGHT,
                false,
                true,
                175.5,
                img
        );

        assertNotNull(user);
        assertEquals("Ethan", user.getFirstName());
        assertEquals("ethan@example.com", user.getEmail());
        assertEquals(175.5, user.getHeight());
    }

    @Test
    void testCreateFullUser_valid() {
        List<Interest> userInts = List.of(Interest.ART);
        Image img = new Image.Builder().setImageId(1L).setImageUrl("/9j8998977".getBytes()).build();
        Preference preference = new Preference();

        User user = UserFactory.createFullUser(
                "Ruby",
                "Jones",
                "ruby@example.com",
                "ruby123",
                "password456",
                23,
                "", // bio allowed empty
                Institution.CAPE_PENINSULA_UNIVERSITY_OF_TECHNOLOGY,
                Gender.FEMALE,
                Course.BCOM_MKT,
                userInts,
                Orientation.BISEXUAL,
                true,
                false,
                162,
                preference,
                Collections.emptyList(),
                img
        );

        assertNotNull(user);
        assertEquals("Ruby", user.getFirstName());
        assertEquals("", user.getBio());
        assertTrue(user.isSmoker());
    }

    // ---------------------------
    // FAIL CASES (should return null)
    // ---------------------------

    @Test
    void testCreateBasicUser_invalidEmail() {
        User user = UserFactory.createBasicUser(
                "John",
                "Doe",
                "invalid-email",
                "johnny",
                "secret",
                20,
                "bio",
                null,
                Gender.MALE,
                null,
                null,
                Orientation.STRAIGHT,
                false,
                false,
                180,
                null
        );
        assertNull(user);
    }

    @Test
    void testCreateBasicUser_invalidHeightZero() {
        User user = UserFactory.createBasicUser(
                "Anna",
                "Brown",
                "anna@example.com",
                "anna123",
                "secret",
                19,
                "bio",
                null,
                Gender.FEMALE,
                null,
                null,
                Orientation.GAY,
                false,
                false,
                0,
                null
        );
        assertNull(user);
    }

    @Test
    void testCreateBasicUser_invalidNegativeAge() {
        User user = UserFactory.createBasicUser(
                "Liam",
                "Gray",
                "liam@example.com",
                "liam123",
                "secret",
                -5, // invalid age
                "bio",
                null,
                Gender.MALE,
                null,
                null,
                Orientation.STRAIGHT,
                false,
                false,
                175,
                null
        );
        assertNull(user);
    }

    @Test
    void testCreateFullUser_nullGender() {
        User user = UserFactory.createFullUser(
                "Sophie",
                "White",
                "sophie@example.com",
                "sophie123",
                "secret",
                22,
                "bio",
                null,
                null,
                null,
                null,
                Orientation.GAY,
                false,
                false,
                170,
                null,
                null,
                null
        );
        assertNull(user);
    }
}