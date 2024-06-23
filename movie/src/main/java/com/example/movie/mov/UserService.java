package com.example.movie.mov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfUserExists(String username, String password) {
        boolean userExists = userRepository.existsByUsernameAndPassword(username, password);
        if (userExists) {
            return true;
        }
        return false;

    }

    public String registerUser(User user) {
        Optional<User> registerUserExists=userRepository.existsByUsername(user.getUsername());
        if (registerUserExists.isPresent()) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }
}