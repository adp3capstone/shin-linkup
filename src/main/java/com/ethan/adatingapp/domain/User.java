package com.ethan.adatingapp.domain;

import com.ethan.adatingapp.domain.enums.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Course course;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_interests",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "interest")
    private List<Interest> interests;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Image image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Preference preferences;

    @OneToMany(mappedBy = "liker", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likesGiven;

    @OneToMany(mappedBy = "liked", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likesReceived;

    public Set<Like> getLikesGiven() {
        return likesGiven;
    }

    public void setLikesGiven(Set<Like> likesGiven) {
        this.likesGiven = likesGiven;
    }

    public Set<Like> getLikesReceived() {
        return likesReceived;
    }

    public void setLikesReceived(Set<Like> likesReceived) {
        this.likesReceived = likesReceived;
    }

    @OneToMany(mappedBy = "user1")
    private List<Match> matchesAsUser1;

    @OneToMany(mappedBy = "user2")
    private List<Match> matchesAsUser2;

    private boolean isSmoker;

    private boolean isDrinker;

    private double height;

    private Orientation orientation;

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Match> getMatchesAsUser1() {
        return matchesAsUser1;
    }

    public void setMatchesAsUser1(List<Match> matchesAsUser1) {
        this.matchesAsUser1 = matchesAsUser1;
    }

    public List<Match> getMatchesAsUser2() {
        return matchesAsUser2;
    }

    public void setMatchesAsUser2(List<Match> matchesAsUser2) {
        this.matchesAsUser2 = matchesAsUser2;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    public boolean isDrinker() {
        return isDrinker;
    }

    public void setDrinker(boolean drinker) {
        isDrinker = drinker;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
//    private User(Builder builder) {
//        this.userId = builder.userId;
//        this.username = builder.username;
//        this.password = builder.password;
//        this.email = builder.email;
//        this.firstName = builder.firstName;
//        this.lastName = builder.lastName;
//        this.age = builder.age;
//        this.bio = builder.bio;
//
//        this.institution = builder.institution;
//        this.gender = builder.gender;
//        this.interests = builder.interests;
//
//        this.image = builder.image;
//    }

    public User() {

    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Image getImage() {
        return image;
    }

    public Preference getPreferences() {
        return preferences;
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
                ", image=" + image +
                ",course=" + course +
                '}';
    }

    //Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPreferences(Preference preferences) {
        this.preferences = preferences;
    }

    //    public static class Builder {
//        private Long userId;
//        private String username;
//        private String password;
//        private String email;
//        private String firstName;
//        private String lastName;
//        private int age;
//        private String bio;
//
//        private Institution institution;
//        private Gender gender;
//        private List<Interest> interests;
//        private Image image;
//
//        public Builder setImage(Image image) {
//            this.image = image;
//            return this;
//        }
//
//        public Builder setUserId(Long userId) {
//            this.userId = userId;
//            return this;
//        }
//
//        public Builder setUsername(String username) {
//            this.username = username;
//            return this;
//        }
//
//        public Builder setPassword(String password) {
//            this.password = password;
//            return this;
//        }
//
//        public Builder setEmail(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder setFirstName(String firstName) {
//            this.firstName = firstName;
//            return this;
//        }
//
//        public Builder setLastName(String lastName) {
//            this.lastName = lastName;
//            return this;
//        }
//
//        public Builder setAge(int age) {
//            this.age = age;
//            return this;
//        }
//
//        public Builder setBio(String bio) {
//            this.bio = bio;
//            return this;
//        }
//
//        public Builder setInstitution(Institution institution) {
//            this.institution = institution;
//            return this;
//        }
//
//        public Builder  setGender(Gender gender) {
//            this.gender = gender;
//            return this;
//        }
//
//        public Builder  setPreferredInterests(List<Interest> interests) {
//            this.interests = interests;
//            return this;
//        }
//
//        public Builder copy(User user) {
//            this.userId = user.userId;
//            this.username = user.username;
//            this.password = user.password;
//            this.email = user.email;
//            this.firstName = user.firstName;
//            this.lastName = user.lastName;
//            this.age = user.age;
//            this.bio = user.bio;
//
//            this.institution = user.institution;
//            this.interests = user.interests;
//            this.gender = user.gender;
//            return this;
//        }
//
//        public User build() {
//            return new User(this);
//        }
//    }
}
