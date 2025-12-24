package com.geekbits.personalexpensetracker.dto;

public record RegisterRequest(
        String username,
        String email,
        String password) {
}
