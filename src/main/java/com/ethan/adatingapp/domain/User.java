package com.ethan.adatingapp.domain;

import com.ethan.adatingapp.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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

        this.image = builder.image;
    }

    public User() {

    }

    public LocalDateTime getDeletionDueDate() {
        return deletionDueDate;
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
                ", deletionDueDate=" + deletionDueDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", institution=" + institution +
                ", gender=" + gender +
                ", course=" + course +
                ", interests=" + interests +
                ", image=" + image +
                ", preferences=" + preferences +
                ", likesGiven=" + likesGiven +
                ", likesReceived=" + likesReceived +
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
  
    public void setDeletionDueDate(LocalDateTime deletionDueDate) {
        this.deletionDueDate = deletionDueDate;
    }
}
