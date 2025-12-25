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
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "email is required")
   
    private String email;

    @NonNull
    
    private String password;

    @Column(name = "created_date")
    private java.time.LocalDate created_date;

}
