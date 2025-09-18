package za.ac.cput.linkup.factory;

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

        if (
                !Helper.isValidInterests(preferredInterests)
                        || Helper.isObjectNull(relationshipType)
                        || Helper.isValidMinAge(minAge)
                        || Helper.isValidMaxAge(maxAge, minAge)
                        || Helper.isObjectNull(preferredGender)
                        || Helper.isValidCourses(preferredCourses)
                        || Helper.isIntZero(maxDistance) || Helper.isIntNegative(maxDistance)
        ) {
            return null;
        }
        return Preference.builder()
                .user(Helper.validateUser(user))
                .preferredInterests(preferredInterests)
                .relationshipType(relationshipType)
                .minAge(minAge)
                .maxAge(maxAge)
                .preferredGender(preferredGender)
                .preferredCourses(preferredCourses)
                .maxDistance(maxDistance)
                .smokingPreference(smokingPreference)
                .drinkingPreference(drinkingPreference)
                .build();
    }
}
