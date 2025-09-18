package za.ac.cput.linkup.factory;

import za.ac.cput.linkup.domain.EmergencyContact;
import za.ac.cput.linkup.domain.Image;
import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.*;
import za.ac.cput.linkup.util.Helper;

import java.util.List;

public class UserFactory {
    public static User createUserForSignup(
            String firstName,
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
            boolean isSmoker,
            boolean isDrinker,
            double height,
            Orientation orientation
    ){
        if(
            Helper.isStringNullOrEmpty(firstName)
            || Helper.isStringNullOrEmpty(lastName)
            || Helper.isStringNullOrEmpty(email)
            || Helper.isStringNullOrEmpty(username)
            || Helper.isStringNullOrEmpty(password)
            || Helper.isIntNegative(age) || Helper.isIntZero(age)
            || Helper.isStringNullOrEmpty(bio)
            || Helper.isObjectNull(institution)
            || Helper.isObjectNull(gender)
            || Helper.isObjectNull(course)
            || !Helper.isValidInterests(interests)
            || Helper.isObjectNull(orientation)
            || !Helper.isValidPositiveDouble(height)
        ){
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
                .isSmoker(isSmoker)
                .isDrinker(isDrinker)
                .height(height)
                .orientation(orientation)
                .build();
    }
}
