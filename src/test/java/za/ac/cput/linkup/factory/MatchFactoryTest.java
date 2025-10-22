package za.ac.cput.linkup.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class MatchFactoryTest {

    @Test
    void build_valid_Match() {
        User user1 = new User();
        user1.setUserId(1L);

        User user2 = new User();
        user2.setUserId(2L);

        Match match = MatchFactory.buildMatch(user1, user2, null);

        assertNotNull(match);
        assertEquals(1L, match.getUser1().getUserId());
        assertEquals(2L, match.getUser2().getUserId());
        assertNotNull(match.getMatchedAt());
    }

    @Test
    void createMatchWithNullUser() {
        User user1 = new User();
        user1.setUserId(6L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MatchFactory.buildMatch(user1, null, null);
        });

        assertEquals("Users cannot be null", exception.getMessage());
    }
}