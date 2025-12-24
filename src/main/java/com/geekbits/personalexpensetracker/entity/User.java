package com.geekbits.personalexpensetracker.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "users")
@Data 
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username is required")
    @Size(min = 1, max = 100, message = "Field must be between 1 and 100 characters")
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NotBlank(message = "email is required")
    @Column(name = "email", nullable = false, length = 30,unique = true)
    private String email;

    @NonNull
    @Size(min = 8, max = 30, message = "password must be between 8 and 30 characters")
    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "created_date")
    private java.time.LocalDate created_date;
    
}
