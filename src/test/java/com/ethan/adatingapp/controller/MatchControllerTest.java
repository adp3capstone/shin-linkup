package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.service.LikeService;
import com.ethan.adatingapp.service.MatchService;
import com.ethan.adatingapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MatchControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private MatchService matchService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setFirstName("Ethan");
        user1.setLastName("Lottering");
        user1.setUsername("nehemiah");
        user1 = userService.create(user1);

        user2 = new User();
        user2.setFirstName("Hope");
        user2.setLastName("Puse");
        user2.setUsername("Hopey");
        user2 = userService.create(user2);
    }

    @Test
    void createMatch() {
// user1 likes user2
        restTemplate.postForEntity(
                String.format("/like/add?likerId=%d&likedId=%d", user1.getUserId(), user2.getUserId()),
                null,
                String.class
        );

        // user2 likes user1
        restTemplate.postForEntity(
                String.format("/like/add?likerId=%d&likedId=%d", user2.getUserId(), user1.getUserId()),
                null,
                String.class
        );

        //create the match
        String url = String.format("/match/create?user1Id=%d&user2Id=%d", user1.getUserId(), user2.getUserId());
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(200, response.getStatusCodeValue());
        System.out.println(response.getBody());
    }

    @Test
    void getAllUserMatches() {
        restTemplate.postForEntity(
                String.format("/like/add?likerId=%d&likedId=%d", user1.getUserId(), user2.getUserId()),
                null,
                String.class
        );

        restTemplate.postForEntity(
                String.format("/like/add?likerId=%d&likedId=%d", user2.getUserId(), user1.getUserId()),
                null,
                String.class
        );

        //Create the match
        restTemplate.postForEntity(
                String.format("/match/create?user1Id=%d&user2Id=%d", user1.getUserId(), user2.getUserId()),
                null,
                String.class
        );

        //Fetch all matches for user1
        String url = String.format("/match/allbyid/%d", user1.getUserId());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        //Assertions
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("matchId"));
        assertTrue(response.getBody().contains(user2.getUsername())); // Check that the match includes user2
        System.out.println(response.getBody());
    }

    @Test
    void getMatchById() {
    }
}