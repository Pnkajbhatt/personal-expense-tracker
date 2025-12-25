package com.geekbits.personalexpensetracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geekbits.personalexpensetracker.dto.AuthResponse;
import com.geekbits.personalexpensetracker.dto.LoginRequest;
import com.geekbits.personalexpensetracker.dto.RegisterRequest;
import com.geekbits.personalexpensetracker.entity.User;
import com.geekbits.personalexpensetracker.exception.ResourceNotFoundException;
import com.geekbits.personalexpensetracker.repository.UserRepository;
import com.geekbits.personalexpensetracker.security.JwtService;

import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse signup(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .created_date(LocalDate.now()).build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getPassword());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.email()));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
