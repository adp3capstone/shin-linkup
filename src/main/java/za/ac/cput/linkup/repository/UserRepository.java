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
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByUsernameAndPassword(String username, String password);
    List<User> findAllByCourse(Course course);
    List<User> findAllByInstitution(Institution institution);
    List<User> findAllByAgeBetween(int minAge, int maxAge);
    List<User> findAllByGender(Gender gender);
    List<User> findAllByInterestsIn(Collection<Interest> interests);

    Optional<User> findByUsername(String username);
}
