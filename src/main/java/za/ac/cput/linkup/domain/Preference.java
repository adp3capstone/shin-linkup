package za.ac.cput.linkup.domain;

import lombok.*;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.domain.enums.RelationshipType;
import jakarta.persistence.*;

import java.util.List;

@ToString
@Setter
@Getter
@Builder
@Entity
@Table(name="preference")
@NoArgsConstructor
@AllArgsConstructor
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
}
