package com.ethan.adatingapp.service;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.*;
import com.ethan.adatingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        String encryptedPassword = BCrypt.withDefaults()
                .hashToString(12, user.getPassword().toCharArray());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (result.verified) {
                return user;
            }
        }
        return null;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean delete(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
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

    public List<User> findUsersForDeletion(LocalDateTime now) {
        return userRepository.findAllByDeletionDueDateBefore(now);
    }
}
