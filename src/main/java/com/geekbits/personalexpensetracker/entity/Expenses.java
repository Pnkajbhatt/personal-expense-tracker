package com.geekbits.personalexpensetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "amount is required")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;


    @NotNull(message = "category is required")
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank(message = "description is required")
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @NotNull(message = "date is required")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public enum Category {
        food,
        transport,
        entertainment,
        bills,
        other
    }

}
