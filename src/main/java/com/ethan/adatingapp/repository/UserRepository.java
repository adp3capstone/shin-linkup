package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // For bcrypt login
    User findByUsername(String username);

    // Filtering
    List<User> findAllByCourse(Course course);
    List<User> findAllByInstitution(Institution institution);
    List<User> findAllByAgeBetween(int minAge, int maxAge);
    List<User> findAllByGender(Gender gender);
    List<User> findAllByInterestsIn(Collection<Interest> interests);

    // For scheduled deletion
    List<User> findAllByDeletionDueDateBefore(LocalDateTime time);
}
