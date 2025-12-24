package com.geekbits.personalexpensetracker.service;

import com.geekbits.personalexpensetracker.dto.AuthResponse;
import com.geekbits.personalexpensetracker.dto.LoginRequest;
import com.geekbits.personalexpensetracker.dto.RegisterRequest;
import com.geekbits.personalexpensetracker.entity.User;
import com.geekbits.personalexpensetracker.repository.UserRepository;
import com.geekbits.personalexpensetracker.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse signup(RegisterRequest request){
        if(userRepository.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("Email already registered");
        }
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password())).build();

        userRepository.save(user);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtService.generateToken(userDetails);
        
        return new AuthResponse(jwt);
    }

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtService.generateToken(userDetails);
        return new AuthResponse(jwt);
    }
}
