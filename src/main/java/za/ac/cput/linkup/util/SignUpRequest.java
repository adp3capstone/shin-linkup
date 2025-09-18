package za.ac.cput.linkup.util;

import lombok.Getter;
import lombok.ToString;
import za.ac.cput.linkup.domain.enums.*;
import za.ac.cput.linkup.domain.*;

import java.util.List;
@ToString
@Getter
public class SignUpRequest {
            String firstName;
            String lastName;
            String email;
            String username;
            String password;
            int age;
            String bio;
            Institution institution;
            Gender gender;
            Course course;
            List<Interest> interests;
            boolean isSmoker;
            boolean isDrinker;
            double height;
            Orientation orientation;

            public SignUpRequest(String firstName,
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
                                 Orientation orientation) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.username = username;
                this.password = password;
                this.age = age;
                this.bio = bio;
                this.institution = institution;
                this.gender = gender;
                this.course = course;
                this.interests = interests;
                this.isSmoker = isSmoker;
                this.isDrinker = isDrinker;
                this.height = height;
                this.orientation = orientation;
            }
}
