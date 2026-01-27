package com.finance.system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime; // Import for created_at

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "or_number", unique = true)
    private String orNumber; // Official Receipt Number

    private String description;

    private BigDecimal amount;

    @Column(name = "transaction_date")
    private LocalDate date;

    // "INCOME" or "EXPENSE"
    private String type; 

    private String category;

    private String status; // PENDING, APPROVED

    @Column(name = "created_at", updatable = false) // updatable = false for auto-generated timestamp
    private LocalDateTime createdAt;
}
