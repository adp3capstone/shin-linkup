package za.ac.cput.linkup.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MatchServiceTest {
    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setFirstName("Anita");
        user1.setLastName("Lottering");
        user1.setUsername("anny");
        user1 = userService.create(user1);

        user2 = new User();
        user2.setFirstName("Brezano");
        user2.setLastName("Lienberg");
        user2.setUsername("Brezzy");
        user2 = userService.create(user2);
    }

    @Test
    void createMatchIfLikedBack() {
        likeService.addLike(user2, user1);

        Match result = matchService.createMatchIfLikedBack(user1, user2);

        assertNotNull(result);
        assertEquals(user1.getUserId(), result.getUser1().getUserId());
        assertEquals(user2.getUserId(), result.getUser2().getUserId());
        assertNotNull(result.getMatchedAt());
        System.out.println(result);
    }

    @Test
    void findAllMatchesForUser() {
        likeService.addLike(user2, user1);

        // user1 likes user2, match should be created
        Match result = matchService.createMatchIfLikedBack(user1, user2);

        assertNotNull(result);
        assertEquals(user1.getUserId(), result.getUser1().getUserId());
        assertEquals(user2.getUserId(), result.getUser2().getUserId());
        System.out.println(result);
    }

    @Test
    void createMatchIfLikedBack_whenNotLikedBack_shouldReturnNull() {
        // user1 likes user2 but user2 hasn't liked back
        Match result = matchService.createMatchIfLikedBack(user1, user2);

        assertNull(result, "No match should be created if not liked back");
        System.out.println(result);
    }
}