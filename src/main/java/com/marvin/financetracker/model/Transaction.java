package com.marvin.financetracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key
    private Category category;

}
