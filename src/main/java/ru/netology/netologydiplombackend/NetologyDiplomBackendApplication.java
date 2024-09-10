package ru.netology.netologydiplombackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.netology.netologydiplombackend.entity.UserEntity;
import ru.netology.netologydiplombackend.repository.UserRepository;


@SpringBootApplication
public class NetologyDiplomBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetologyDiplomBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineIvan(UserRepository users, PasswordEncoder encoder) {
        return args -> users.save(new UserEntity("ivan", encoder.encode("qwerty"), "ROLE_USER"));
    }
    @Bean
    CommandLineRunner commandLinePetr(UserRepository users, PasswordEncoder encoder) {
        return args -> users.save(new UserEntity("petr", encoder.encode("12345"), "ROLE_USER"));
    }
}
