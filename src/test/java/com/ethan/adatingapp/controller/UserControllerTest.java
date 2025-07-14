package com.ethan.adatingapp.controller;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.factory.UserFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private final UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerTest(UserController userController) {
        this.userController = userController;
    }

    @Test
    void testCreateUserIfSingleFieldIsEmpty() throws Exception {
        User user = UserFactory.createUser(
                98765432L,
                "",
                "123456",
                "john@john.com",
                "ethan",
                "maxcm",
                21,
                "awe"
        );
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/user/signup")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @Disabled
//    void getUserById() {
//        User user = UserFactory.createUser(
//                123456789L,
//                "Jane",
//                "Doe",
//                "",
//                "",
//                "",
//                22,
//                ""
//        );
//        userController.createUser(user);
//        User foundUser = userController.getUserById(user.getUserId()).getBody();
//        assertNotNull(foundUser);
//        assertEquals(user.getUserId(), foundUser.getUserId());
//        System.out.println("Found User: " + foundUser);
//    }
}