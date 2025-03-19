package io.github.alexpercont.jakartaee.examples.persistence.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "expenses")
public class Expense implements EntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private BigInteger amount;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private ExpenseCategory category;

}
