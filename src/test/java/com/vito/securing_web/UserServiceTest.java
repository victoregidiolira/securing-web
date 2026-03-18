package com.vito.securing_web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @Test
    void register_shouldThrowException_whenUsernameAlreadyExists() {
        UserEntity existingUser = new UserEntity();
        existingUser.setUsername("user");
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(existingUser);

        UserEntity newUser = new UserEntity();
        newUser.setUsername("user");
        newUser.setPassword("1234");

        assertThrows(RuntimeException.class, () -> userService.register(newUser));
    }

    @Test
    void register_shouldThrowException_whenPasswordIsTooShort(){
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("123");

        assertThrows(RuntimeException.class, () -> userService.register(user));

    }

    @Test
    void register_shouldSaveUser_whenDataIsValid(){
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("1234");

        userService.register(user);

        Mockito.verify(userRepository).save(Mockito.any());
    }
}