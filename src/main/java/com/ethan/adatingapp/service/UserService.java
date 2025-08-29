package com.ethan.adatingapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import com.ethan.adatingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        //Encrypt password before saving
        String encryptedPassword = BCrypt.withDefaults()
                .hashToString(12, user.getPassword().toCharArray());

        User encryptedUser = new User.Builder()
                .copy(user)
                .setPassword(encryptedPassword)
                .build();

        return userRepository.save(encryptedUser);
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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) return null;

        // Verify password against BCrypt hash
        if (BCrypt.verifyer().verify(rawPassword.toCharArray(), user.getPassword()).verified) {
            return user;
        }
        return null;


    }
}
