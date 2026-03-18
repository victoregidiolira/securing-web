package com.vito.securing_web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserEntity userEntity){
        if (userRepository.findByUsername(userEntity.getUsername()) != null){
            throw new RuntimeException("Username already exists.");
        } if (userEntity.getPassword().length() < 4) {
            throw new RuntimeException("Password must be at least 4 characters long");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

}
