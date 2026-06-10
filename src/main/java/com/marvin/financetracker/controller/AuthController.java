package com.marvin.financetracker.controller;

import com.marvin.financetracker.model.User;
import com.marvin.financetracker.security.JwtUtils;
import com.marvin.financetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    AuthController(UserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){
       validateRequired(request.username(), "username");
       validateRequired(request.password(), "password");

       User user = new User();
       user.setUsername(request.username());
       user.setPassword(passwordEncoder.encode(request.password()));
       user.setEmail(request.email());
       user.setFirstName(request.firstName());
       user.setLastName(request.lastName());

       User savedUser = userService.createUser(user);
       return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        validateRequired(request.username(), "username");
        validateRequired(request.password(), "password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        String token = jwtUtils.generateToken(request.username());
        return ResponseEntity.ok(token);
    }

    private void validateRequired(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fieldName + " is required");
        }
    }

    private record RegisterRequest(String username, String password, String email, String firstName, String lastName) {
    }

    private record LoginRequest(String username, String password) {
    }
}
