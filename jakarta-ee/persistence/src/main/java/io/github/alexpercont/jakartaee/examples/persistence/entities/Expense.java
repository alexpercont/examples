package io.github.alexpercont.jakartaee.examples.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigInteger;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private BigInteger amount;


}
