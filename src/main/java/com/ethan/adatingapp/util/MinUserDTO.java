package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.Orientation;

import java.util.ArrayList;
import java.util.List;

public class MinUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private int age;
    private String bio;
    private String institution;
    private String gender;
    private String course;
    private List<String> interests;

    // Likes and Matches as IDs only
    private List<Long> likedUserIds;
    private List<Long> likedByUserIds;
    private List<Long> matchedUserIds;

    // Additional fields
    private boolean isSmoker;
    private boolean isDrinker;
    private double height;
    private Orientation orientation;

    public MinUserDTO() {}

    public MinUserDTO(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.bio = user.getBio();
        this.institution = user.getInstitution() != null ? user.getInstitution().name() : null;
        this.gender = user.getGender() != null ? user.getGender().name() : null;
        this.course = user.getCourse() != null ? user.getCourse().name() : null;

        if (user.getInterests() != null) {
            this.interests = user.getInterests().stream().map(Enum::name).toList();
        }

        // Likes
        if (user.getLikesGiven() != null) {
            this.likedUserIds = user.getLikesGiven().stream()
                    .map(like -> like.getLiked().getUserId())
                    .toList();
        }

        if (user.getLikesReceived() != null) {
            this.likedByUserIds = user.getLikesReceived().stream()
                    .map(like -> like.getLiker().getUserId())
                    .toList();
        }

        // Matches
        List<Long> matchesFromUser1 = user.getMatchesAsUser1() != null
                ? user.getMatchesAsUser1().stream().map(m -> m.getUser2().getUserId()).toList()
                : List.of();

        List<Long> matchesFromUser2 = user.getMatchesAsUser2() != null
                ? user.getMatchesAsUser2().stream().map(m -> m.getUser1().getUserId()).toList()
                : List.of();

        this.matchedUserIds = new ArrayList<>(matchesFromUser1);
        this.matchedUserIds.addAll(matchesFromUser2);

        // Additional fields
        this.isSmoker = user.isSmoker();
        this.isDrinker = user.isDrinker();
        this.height = user.getHeight();
        this.orientation = user.getOrientation();
    }

    // --- Getters and Setters ---
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public List<Long> getLikedUserIds() { return likedUserIds; }
    public void setLikedUserIds(List<Long> likedUserIds) { this.likedUserIds = likedUserIds; }
    public List<Long> getLikedByUserIds() { return likedByUserIds; }
    public void setLikedByUserIds(List<Long> likedByUserIds) { this.likedByUserIds = likedByUserIds; }
    public List<Long> getMatchedUserIds() { return matchedUserIds; }
    public void setMatchedUserIds(List<Long> matchedUserIds) { this.matchedUserIds = matchedUserIds; }
    public boolean isSmoker() { return isSmoker; }
    public void setSmoker(boolean smoker) { isSmoker = smoker; }
    public boolean isDrinker() { return isDrinker; }
    public void setDrinker(boolean drinker) { isDrinker = drinker; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public Orientation getOrientation() { return orientation; }
    public void setOrientation(Orientation orientation) { this.orientation = orientation; }
}
