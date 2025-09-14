package com.ethan.adatingapp.factory;

import com.ethan.adatingapp.domain.EmergencyContact;
import com.ethan.adatingapp.domain.Image;
import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import com.ethan.adatingapp.util.Helper;

import java.util.List;

public class UserFactory {
    private static boolean validateBasicFields(String firstName,
                                               String lastName,
                                               String email,
                                               String username,
                                               String password,
                                               int age,
                                               Gender gender,
                                               Orientation orientation,
                                               double height) {
        if (Helper.isStringNullOrEmpty(firstName)) return false;
        if (Helper.isStringNullOrEmpty(lastName)) return false;
        if (!Helper.isValidEmail(email)) return false;
        if (Helper.isStringNullOrEmpty(username)) return false;
        if (Helper.isStringNullOrEmpty(password)) return false;
        if (Helper.isIntNegative(age) || Helper.isIntZero(age)) return false;
        if (Helper.isObjectNull(gender)) return false;
        if (Helper.isObjectNull(orientation)) return false;
        return !(height <= 0);
    }

    public static User createBasicUser(String firstName,
                                       String lastName,
                                       String email,
                                       String username,
                                       String password,
                                       int age,
                                       String bio,
                                       Institution institution,
                                       Gender gender,
                                       Course course,
                                       List<Interest> interests,
                                       Orientation orientation,
                                       boolean isSmoker,
                                       boolean isDrinker,
                                       double height,
                                       Image image) {

        if (!validateBasicFields(firstName, lastName, email, username, password, age, gender, orientation, height)) {
            return null;
        }

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .age(age)
                .bio(bio)
                .institution(institution)
                .gender(gender)
                .course(course)
                .interests(interests)
                .orientation(orientation)
                .isSmoker(isSmoker)
                .isDrinker(isDrinker)
                .height(height)
                .image(image)
                .build();
    }

    public static User createFullUser(String firstName,
                                      String lastName,
                                      String email,
                                      String username,
                                      String password,
                                      int age,
                                      String bio,
                                      Institution institution,
                                      Gender gender,
                                      Course course,
                                      List<Interest> interests,
                                      Orientation orientation,
                                      boolean isSmoker,
                                      boolean isDrinker,
                                      double height,
                                      Preference preferences,
                                      List<EmergencyContact> contacts,
                                      Image image
    ) {
        if (!validateBasicFields(firstName, lastName, email, username, password, age, gender, orientation, height)) {
            return null;
        }

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .age(age)
                .bio(bio)
                .institution(institution)
                .gender(gender)
                .course(course)
                .interests(interests)
                .orientation(orientation)
                .isSmoker(isSmoker)
                .isDrinker(isDrinker)
                .height(height)
                .preferences(preferences)
                .emergencyContacts(contacts)
                .image(image)
                .build();
    }
}
