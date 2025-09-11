package com.ethan.adatingapp.domain;

import com.ethan.adatingapp.domain.enums.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "deletion_due_date")
    private LocalDateTime deletionDueDate;

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
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private EmergencyContact emergencyContact;

    // Default constructor
    public User() {}

    // Builder constructor
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
        this.course = builder.course;
        this.interests = builder.interests;
        this.image = builder.image;
        this.deletionDueDate = builder.deletionDueDate;
        this.emergencyContact = builder.emergencyContact;

        // Maintain bidirectional relationship
        if (this.emergencyContact != null) {
            this.emergencyContact.setUser(this);
        }
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public LocalDateTime getDeletionDueDate() { return deletionDueDate; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public String getBio() { return bio; }
    public Institution getInstitution() { return institution; }
    public Gender getGender() { return gender; }
    public Course getCourse() { return course; }
    public List<Interest> getInterests() { return interests; }
    public Image getImage() { return image; }
    public Preference getPreferences() { return preferences; }
    public Set<Like> getLikesGiven() { return likesGiven; }
    public Set<Like> getLikesReceived() { return likesReceived; }
    public EmergencyContact getEmergencyContact() { return emergencyContact; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setDeletionDueDate(LocalDateTime deletionDueDate) { this.deletionDueDate = deletionDueDate; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setAge(int age) { this.age = age; }
    public void setBio(String bio) { this.bio = bio; }
    public void setInstitution(Institution institution) { this.institution = institution; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setCourse(Course course) { this.course = course; }
    public void setInterests(List<Interest> interests) { this.interests = interests; }
    public void setImage(Image image) { this.image = image; }
    public void setPreferences(Preference preferences) { this.preferences = preferences; }
    public void setLikesGiven(Set<Like> likesGiven) { this.likesGiven = likesGiven; }
    public void setLikesReceived(Set<Like> likesReceived) { this.likesReceived = likesReceived; }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
        if (emergencyContact != null) {
            emergencyContact.setUser(this); // maintain bidirectional consistency
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", deletionDueDate=" + deletionDueDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", institution=" + institution +
                ", gender=" + gender +
                ", course=" + course +
                ", interests=" + interests +
                ", emergencyContact=" + (emergencyContact != null ? emergencyContact.getFirstName() : null) +
                '}';
    }

    // Builder Pattern
    public static class Builder {
        private Long userId;
        private LocalDateTime deletionDueDate;
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
        private List<Interest> interests;
        private Image image;
        private EmergencyContact emergencyContact;

        public Builder setUserId(Long userId) { this.userId = userId; return this; }
        public Builder setDeletionDueDate(LocalDateTime deletionDueDate) { this.deletionDueDate = deletionDueDate; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setUsername(String username) { this.username = username; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setAge(int age) { this.age = age; return this; }
        public Builder setBio(String bio) { this.bio = bio; return this; }
        public Builder setInstitution(Institution institution) { this.institution = institution; return this; }
        public Builder setGender(Gender gender) { this.gender = gender; return this; }
        public Builder setCourse(Course course) { this.course = course; return this; }
        public Builder setInterests(List<Interest> interests) { this.interests = interests; return this; }
        public Builder setImage(Image image) { this.image = image; return this; }
        public Builder setEmergencyContact(EmergencyContact emergencyContact) {
            this.emergencyContact = emergencyContact;
            if (emergencyContact != null) {
                emergencyContact.setUser(null); // will be set in User constructor
            }
            return this;
        }

        public Builder copy(User user) {
            this.userId = user.userId;
            this.deletionDueDate = user.deletionDueDate;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.username = user.username;
            this.password = user.password;
            this.age = user.age;
            this.bio = user.bio;
            this.institution = user.institution;
            this.gender = user.gender;
            this.course = user.course;
            this.interests = user.interests;
            this.image = user.image;
            this.emergencyContact = user.emergencyContact;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
