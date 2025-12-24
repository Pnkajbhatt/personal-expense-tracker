package com.geekbits.personalexpensetracker.controller;

import com.geekbits.personalexpensetracker.dto.AuthResponse;
import com.geekbits.personalexpensetracker.dto.LoginRequest;
import com.geekbits.personalexpensetracker.dto.RegisterRequest;
import com.geekbits.personalexpensetracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AuthController {
    @RestController
    @RequestMapping("/api/auth")
    @RequiredArgsConstructor
    public class authController {
        private final AuthService authService;

        @PostMapping("/signup")
        public ResponseEntity<AuthResponse> signup(@RequestBody RegisterRequest request) {
            return ResponseEntity.ok(authService.signup(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(authService.login(request));
        }
    }
}
