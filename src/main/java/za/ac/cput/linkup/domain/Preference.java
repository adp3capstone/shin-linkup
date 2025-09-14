package za.ac.cput.linkup.domain;

import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.domain.enums.RelationshipType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="preference")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long preferenceId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true, nullable = false)
    User user;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_interests",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "interest")
    private List<Interest> preferredInterests;

    private RelationshipType relationshipType;

    private int minAge;
    private int maxAge;
    private Gender preferredGender;

    @ElementCollection(targetClass = Course.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "course",length = 50)
    private List<Course> preferredCourses;

    private int maxDistance;
    private boolean smokingPreference;
    private boolean drinkingPreference;

    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Interest> getPreferredInterests() {
        return preferredInterests;
    }

    public void setPreferredInterests(List<Interest> interests) {
        this.preferredInterests = interests;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
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

    public Gender getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(Gender preferredGender) {
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

    @Override
    public String toString() {
        return "Preference{" +
                "preferenceId=" + preferenceId +
                ", user=" + user +
                ", preferredInterests=" + preferredInterests +
                ", relationshipType=" + relationshipType +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", preferredGender=" + preferredGender +
                ", preferredCourses=" + preferredCourses +
                ", maxDistance=" + maxDistance +
                ", smokingPreference=" + smokingPreference +
                ", drinkingPreference=" + drinkingPreference +
                '}';
    }
}
