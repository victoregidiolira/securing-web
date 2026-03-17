    package com.vito.securing_web;

    import org.springframework.boot.ApplicationArguments;
    import org.springframework.boot.ApplicationRunner;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Component;

    @Component
    public class DataInitializer implements ApplicationRunner {

        private final UserRepository userRepository;
        private PasswordEncoder passwordEncoder;

        public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(ApplicationArguments args){
            if (userRepository.findByUsername("user") == null){
                UserEntity user = new UserEntity();

                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("1234"));

                userRepository.save(user);
            }
        }

    }
