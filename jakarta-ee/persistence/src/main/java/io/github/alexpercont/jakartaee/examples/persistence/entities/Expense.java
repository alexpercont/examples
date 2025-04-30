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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
