package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void findByUsernameAndPassword() {
        String username = "testuser";
        String password = "testpassword";
        User user = userService.findByUsernameAndPassword(username, password);
        assertNotNull(user, "User should not be null");
    }
}