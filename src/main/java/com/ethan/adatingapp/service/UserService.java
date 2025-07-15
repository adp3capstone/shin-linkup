package com.ethan.adatingapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ethan.adatingapp.domain.User;
import com.ethan.adatingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
