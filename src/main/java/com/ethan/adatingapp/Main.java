package com.ethan.adatingapp;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.Gender;
import com.ethan.adatingapp.domain.enums.Institution;
import com.ethan.adatingapp.domain.enums.Interest;
import com.ethan.adatingapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}