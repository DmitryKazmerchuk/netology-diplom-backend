package ru.netology.netologydiplombackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.netologydiplombackend.dto.TokenDto;
import ru.netology.netologydiplombackend.dto.UserDto;
import ru.netology.netologydiplombackend.service.AuthenticationService;


@RestController
@RequestMapping("/")
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto authRequest) {
        String token = authService.loginUser(authRequest);
        return token != null ? new ResponseEntity<>(new TokenDto(token), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {
        authService.logoutUser(token);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}