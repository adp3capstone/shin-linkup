package za.ac.cput.linkup.service;

/**
 * UserService.java
 * Author: Ethan Le Roux (222622172)
 */

import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.domain.Preference;
import za.ac.cput.linkup.domain.enums.Course;
import za.ac.cput.linkup.domain.enums.Gender;
import za.ac.cput.linkup.domain.enums.Institution;
import za.ac.cput.linkup.domain.enums.Interest;
import za.ac.cput.linkup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        //Encrypt password before saving
//        String encryptedPassword = BCrypt.withDefaults()
//                .hashToString(12, user.getPassword().toCharArray());
//
//        User encryptedUser = new User.Builder()
//                .copy(user)
//                .setPassword(encryptedPassword)
//                .build();

        return userRepository.save(user);
    }

    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllByCourse(Course course) {
        return userRepository.findAllByCourse(course);
    }

    public List<User> findAllByInstitution(Institution institution) {
        return userRepository.findAllByInstitution(institution);
    }

    public List<User> findAllByAgeBetween(int minAge, int maxAge) {
        return userRepository.findAllByAgeBetween(minAge, maxAge);
    }

    public List<User> findAllByGender(Gender gender) {
        return userRepository.findAllByGender(gender);
    }

    public List<User> findAllByInterestsIn(Collection<Interest> interests) {
        return userRepository.findAllByInterestsIn(interests);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByPreference(Preference preference) {
        int minAge = preference.getMinAge();
        int maxAge = preference.getMaxAge();
        Gender gender = preference.getPreferredGender();
        List<Course> courses = preference.getPreferredCourses();
        List<Interest> interests = preference.getPreferredInterests();
        boolean coursesEmpty = (courses == null || courses.isEmpty());
        boolean interestsEmpty = (interests == null || interests.isEmpty());
        if (coursesEmpty) {
            courses = Collections.emptyList();
        }
        if (interestsEmpty) {
            interests = Collections.emptyList();
        }
        Boolean smokingPref = preference.isSmokingPreference();
        Boolean drinkingPref = preference.isDrinkingPreference();
        return userRepository.findByPreference(
                minAge,
                maxAge,
                gender,
                courses,
                interests,
                smokingPref,
                drinkingPref,
                coursesEmpty,
                interestsEmpty
        );
    }
}
