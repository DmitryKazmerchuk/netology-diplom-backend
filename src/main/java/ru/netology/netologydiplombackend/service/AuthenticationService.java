package ru.netology.netologydiplombackend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.netology.netologydiplombackend.dto.UserDto;
import ru.netology.netologydiplombackend.security.JwtToken;


import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtTokenUtils;
    private final Map<String, String> tokenStore = new HashMap<>();

    public AuthenticationService(AuthenticationManager authenticationManager, JwtToken jwtTokenUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public String loginUser(UserDto authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtils.generateToken(authentication);
            tokenStore.put(token, authRequest.getLogin());
            return token;
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("BadCredentialsException");
        }
    }

    public void logoutUser(String authToken) {
        tokenStore.remove(authToken);
    }
}