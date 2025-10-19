package za.ac.cput.linkup.repository;

/**
 * UserRepository.java
 * Author: Ethan Le Roux (222622172)
 */

import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Institution;
import za.ac.cput.linkup.domain.enums.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllByCourse(Course course);
    List<User> findAllByInstitution(Institution institution);
    List<User> findAllByAgeBetween(int minAge, int maxAge);
    List<User> findAllByGender(Gender gender);
    List<User> findAllByInterestsIn(Collection<Interest> interests);

    // Query users that match a set of preference fields. Lists may be empty to skip those filters.
    @org.springframework.data.jpa.repository.Query(
            "select distinct u from User u left join u.interests intr " +
            "where u.age between :minAge and :maxAge " +
            "and (:gender is null or u.gender = :gender) " +
            "and (:coursesEmpty = true or u.course in :courses) " +
            "and (:interestsEmpty = true or intr in :interests) " +
            "and (:smokingPref is null or u.isSmoker = :smokingPref) " +
            "and (:drinkingPref is null or u.isDrinker = :drinkingPref)")
    java.util.List<User> findByPreference(
            @org.springframework.data.repository.query.Param("minAge") int minAge,
            @org.springframework.data.repository.query.Param("maxAge") int maxAge,
            @org.springframework.data.repository.query.Param("gender") Gender gender,
            @org.springframework.data.repository.query.Param("courses") java.util.List<Course> courses,
            @org.springframework.data.repository.query.Param("interests") java.util.List<Interest> interests,
            @org.springframework.data.repository.query.Param("smokingPref") java.lang.Boolean smokingPref,
            @org.springframework.data.repository.query.Param("drinkingPref") java.lang.Boolean drinkingPref,
            @org.springframework.data.repository.query.Param("coursesEmpty") boolean coursesEmpty,
            @org.springframework.data.repository.query.Param("interestsEmpty") boolean interestsEmpty
    );
}
