package za.ac.cput.linkup.util;

import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.domain.enums.Orientation;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDTO {
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
    private List<Interest> interests;
    private String imageBase64;

    // Preferences
    private Long preferenceId;
    private List<Interest> preferredInterests;
    private String relationshipType;
    private int minAge;
    private int maxAge;
    private String preferredGender;
    private List<Course> preferredCourses;
    private int maxDistance;
    private boolean smokingPreference;
    private boolean drinkingPreference;

    // Likes
    private List<Long> likedUserIds;
    private List<Long> likedByUserIds;

    // Matches
    private List<Long> matchedUserIds;

    // Additional fields
    private boolean isSmoker;
    private boolean isDrinker;
    private double height;
    private Orientation orientation;

    public UserDTO() {}

    public UserDTO(User user) {
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
        this.interests = user.getInterests();

        // Image
        if (user.getImage() != null && user.getImage().getImageUrl() != null) {
            this.imageBase64 = Base64.getEncoder().encodeToString(user.getImage().getImageUrl());
        }

        // Preferences
        Preference pref = user.getPreferences();
        if (pref != null) {
            this.preferenceId = pref.getPreferenceId();
            this.preferredInterests = pref.getPreferredInterests();
            this.relationshipType = pref.getRelationshipType() != null ? pref.getRelationshipType().name() : null;
            this.minAge = pref.getMinAge();
            this.maxAge = pref.getMaxAge();
            this.preferredGender = pref.getPreferredGender() != null ? pref.getPreferredGender().name() : null;
            this.preferredCourses = pref.getPreferredCourses();
            this.maxDistance = pref.getMaxDistance();
            this.smokingPreference = pref.isSmokingPreference();
            this.drinkingPreference = pref.isDrinkingPreference();
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

// create a mutable copy
        this.matchedUserIds = new ArrayList<>(matchesFromUser1);

// safely add all from the other list
        this.matchedUserIds.addAll(matchesFromUser2);
        //this.matchedUserIds.addAll(matchesFromUser2);

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
    public List<Interest> getInterests() { return interests; }
    public void setInterests(List<Interest> interests) { this.interests = interests; }
    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

    // Preferences
    public Long getPreferenceId() { return preferenceId; }
    public void setPreferenceId(Long preferenceId) { this.preferenceId = preferenceId; }
    public List<Interest> getPreferredInterests() { return preferredInterests; }
    public void setPreferredInterests(List<Interest> preferredInterests) { this.preferredInterests = preferredInterests; }
    public String getRelationshipType() { return relationshipType; }
    public void setRelationshipType(String relationshipType) { this.relationshipType = relationshipType; }
    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }
    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }
    public String getPreferredGender() { return preferredGender; }
    public void setPreferredGender(String preferredGender) { this.preferredGender = preferredGender; }
    public List<Course> getPreferredCourses() { return preferredCourses; }
    public void setPreferredCourses(List<Course> preferredCourses) { this.preferredCourses = preferredCourses; }
    public int getMaxDistance() { return maxDistance; }
    public void setMaxDistance(int maxDistance) { this.maxDistance = maxDistance; }
    public boolean isSmokingPreference() { return smokingPreference; }
    public void setSmokingPreference(boolean smokingPreference) { this.smokingPreference = smokingPreference; }
    public boolean isDrinkingPreference() { return drinkingPreference; }
    public void setDrinkingPreference(boolean drinkingPreference) { this.drinkingPreference = drinkingPreference; }

    // Likes
    public List<Long> getLikedUserIds() { return likedUserIds; }
    public void setLikedUserIds(List<Long> likedUserIds) { this.likedUserIds = likedUserIds; }
    public List<Long> getLikedByUserIds() { return likedByUserIds; }
    public void setLikedByUserIds(List<Long> likedByUserIds) { this.likedByUserIds = likedByUserIds; }

    // Matches
    public List<Long> getMatchedUserIds() { return matchedUserIds; }
    public void setMatchedUserIds(List<Long> matchedUserIds) { this.matchedUserIds = matchedUserIds; }

    // Additional fields
    public boolean isSmoker() { return isSmoker; }
    public void setSmoker(boolean smoker) { isSmoker = smoker; }
    public boolean isDrinker() { return isDrinker; }
    public void setDrinker(boolean drinker) { isDrinker = drinker; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
