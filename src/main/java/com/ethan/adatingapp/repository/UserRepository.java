package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.Course;
import com.ethan.adatingapp.domain.enums.Gender;
import com.ethan.adatingapp.domain.enums.Institution;
import com.ethan.adatingapp.domain.enums.Interest;
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

    User findByEmail(String email);

}
