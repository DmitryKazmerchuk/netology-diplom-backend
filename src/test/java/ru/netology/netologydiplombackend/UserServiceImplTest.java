package ru.netology.netologydiplombackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import ru.netology.netologydiplombackend.entity.UserEntity;
import ru.netology.netologydiplombackend.model.SecurityUser;
import ru.netology.netologydiplombackend.repository.UserRepository;
import ru.netology.netologydiplombackend.service.UserServiceImpl;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private final UserEntity user = new UserEntity(USERNAME, PASSWORD, null);
    private final SecurityUser securityUser = new SecurityUser(user);

    @Test
    void loadUserByUsernameTest() {
        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername(USERNAME);
        assertEquals(securityUser.getPassword(), userDetails.getPassword());
    }
}