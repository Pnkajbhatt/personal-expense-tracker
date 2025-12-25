package com.geekbits.personalexpensetracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
                @NotBlank(message = "username is required") String username,
                @NotBlank(message = "email is required") @Email(message = "email should be valid") String email,
                @NotBlank(message = "password is required") String password) {
}
