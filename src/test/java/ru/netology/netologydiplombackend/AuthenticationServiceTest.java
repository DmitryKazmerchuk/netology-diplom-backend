package ru.netology.netologydiplombackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ru.netology.netologydiplombackend.dto.UserDto;
import ru.netology.netologydiplombackend.security.JwtToken;
import ru.netology.netologydiplombackend.service.AuthenticationService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtToken jwtTokenUtils;
    private final String USER = "user";
    private final String PASSWORD = "password";
    private final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(USER, PASSWORD);
    private final String token = UUID.randomUUID().toString();
    private final UserDto authRequest = new UserDto(USER, PASSWORD);

    @Test
    void loginUserTest() {
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        given(jwtTokenUtils.generateToken(authentication)).willReturn(token);
        assertEquals(token, authenticationService.loginUser(authRequest));
    }
}