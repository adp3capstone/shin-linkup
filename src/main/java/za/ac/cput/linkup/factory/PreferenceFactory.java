package za.ac.cput.linkup.factory;

/**
 * PreferenceFactory.java
 * Author: Ethan Le Roux (222622172)
 */

import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.domain.enums.RelationshipType;
import za.ac.cput.linkup.util.Helper;

import java.util.List;

public class PreferenceFactory {
    public static Preference createPreference(User user,
                                              List<Interest> preferredInterests,
                                              RelationshipType relationshipType,
                                              int minAge,
                                              int maxAge,
                                              Gender preferredGender,
                                              List<Course> preferredCourses,
                                              int maxDistance,
                                              boolean smokingPreference,
                                              boolean drinkingPreference) {

        return Preference.builder()
                .user(Helper.validateUser(user))
                .preferredInterests(preferredInterests)
                .relationshipType(Helper.validateRelationshipType(relationshipType))
                .minAge(Helper.validateMinAge(minAge))
                .maxAge(Helper.validateMaxAge(maxAge, minAge))
                .preferredGender(Helper.validatePreferredGender(preferredGender))
                .preferredCourses(Helper.validateCourses(preferredCourses))
                .maxDistance(Helper.validateMaxDistance(maxDistance))
                .smokingPreference(smokingPreference)
                .drinkingPreference(drinkingPreference)
                .build();
    }
}
