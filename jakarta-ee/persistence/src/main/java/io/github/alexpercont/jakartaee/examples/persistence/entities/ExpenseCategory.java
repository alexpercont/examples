package io.github.alexpercont.jakartaee.examples.persistence.entities;

import jakarta.persistence.*;

@Entity(name = "Category")
@Table(name = "expense_categories", catalog = "expenses", schema = "expenses")
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

}
