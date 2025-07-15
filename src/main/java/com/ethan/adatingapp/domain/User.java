package com.ethan.adatingapp.domain;

import com.ethan.adatingapp.domain.enums.Gender;
import com.ethan.adatingapp.domain.enums.Institution;
import com.ethan.adatingapp.domain.enums.Interest;
import com.ethan.adatingapp.domain.enums.RelationshipType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private int age;

    private String bio;

    private Institution institution;
    private Gender gender;
    private List<Interest> interests;
    private RelationshipType relationshipType;

    private User(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.bio = builder.bio;

        this.institution = builder.institution;
        this.gender = builder.gender;
        this.interests = builder.interests;
        this.relationshipType = builder.relationshipType;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }


    public Institution getInstitution() {
        return institution;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", institution=" + institution +
                ", gender=" + gender +
                ", interests=" + interests +
                ", relationshipType=" + relationshipType +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private int age;
        private String bio;

        private Institution institution;
        private Gender gender;
        private List<Interest> interests;
        private RelationshipType relationshipType;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder setInstitution(Institution institution) {
            this.institution = institution;
            return this;
        }

        public Builder  setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder  setInterests(List<Interest> interests) {
            this.interests = interests;
            return this;
        }

        public Builder  setRelationshipType(RelationshipType relationshipType) {
            this.relationshipType = relationshipType;
            return this;
        }

        public Builder copy(User user) {
            this.userId = user.userId;
            this.username = user.username;
            this.password = user.password;
            this.email = user.email;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.age = user.age;
            this.bio = user.bio;

            this.institution = user.institution;
            this.interests = user.interests;
            this.gender = user.gender;
            this.relationshipType = user.relationshipType;

            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
