package com.ethan.adatingapp.util;

import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.enums.Course;
import com.ethan.adatingapp.domain.enums.Interest;

import java.util.List;

public class PreferenceDTO {
    private Long preferenceId;
    private Long userId;

    private List<Interest> preferredInterests;
    private String relationshipType;
    private int minAge;
    private int maxAge;
    private String preferredGender;
    private List<Course> preferredCourses;
    private int maxDistance;
    private boolean smokingPreference;
    private boolean drinkingPreference;

    public PreferenceDTO() {}

    public PreferenceDTO(Long preferenceId, Long userId, List<Interest> preferredInterests, String relationshipType,
                         int minAge, int maxAge, String preferredGender, List<Course> preferredCourses,
                         int maxDistance, boolean smokingPreference, boolean drinkingPreference) {
        this.preferenceId = preferenceId;
        this.userId = userId;
        this.preferredInterests = preferredInterests;
        this.relationshipType = relationshipType;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.preferredGender = preferredGender;
        this.preferredCourses = preferredCourses;
        this.maxDistance = maxDistance;
        this.smokingPreference = smokingPreference;
        this.drinkingPreference = drinkingPreference;
    }

    // Constructor from Preference entity
    public PreferenceDTO(Preference preference) {
        this.preferenceId = preference.getPreferenceId();
        this.userId = preference.getUser() != null ? preference.getUser().getUserId() : null;
        this.preferredInterests = preference.getPreferredInterests();
        this.relationshipType = preference.getRelationshipType() != null ? preference.getRelationshipType().name() : null;
        this.minAge = preference.getMinAge();
        this.maxAge = preference.getMaxAge();
        this.preferredGender = preference.getPreferredGender() != null ? preference.getPreferredGender().name() : null;
        this.preferredCourses = preference.getPreferredCourses();
        this.maxDistance = preference.getMaxDistance();
        this.smokingPreference = preference.isSmokingPreference();
        this.drinkingPreference = preference.isDrinkingPreference();
    }

    // Getters and setters
    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Interest> getPreferredInterests() {
        return preferredInterests;
    }

    public void setPreferredInterests(List<Interest> preferredInterests) {
        this.preferredInterests = preferredInterests;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        this.preferredGender = preferredGender;
    }

    public List<Course> getPreferredCourses() {
        return preferredCourses;
    }

    public void setPreferredCourses(List<Course> preferredCourses) {
        this.preferredCourses = preferredCourses;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean isSmokingPreference() {
        return smokingPreference;
    }

    public void setSmokingPreference(boolean smokingPreference) {
        this.smokingPreference = smokingPreference;
    }

    public boolean isDrinkingPreference() {
        return drinkingPreference;
    }

    public void setDrinkingPreference(boolean drinkingPreference) {
        this.drinkingPreference = drinkingPreference;
    }
}
