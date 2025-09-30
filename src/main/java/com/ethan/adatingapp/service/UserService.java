package com.ethan.adatingapp.service;

import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.domain.enums.Course;
import com.ethan.adatingapp.domain.enums.Gender;
import com.ethan.adatingapp.domain.enums.Institution;
import com.ethan.adatingapp.domain.enums.Interest;
import com.ethan.adatingapp.repository.UserRepository;
import com.ethan.adatingapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
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

    //Forgot password

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);// Make sure UserRepository has a findByEmail method
    }

    public String generatePasswordResetToken(String email) {
        return jwtUtil.generateToken(email);
    }

    public String createPasswordResetLink(String email) {
        String token = generatePasswordResetToken(email);
        return "http://localhost:8081/reset-password?token=" + token;
    }



}
