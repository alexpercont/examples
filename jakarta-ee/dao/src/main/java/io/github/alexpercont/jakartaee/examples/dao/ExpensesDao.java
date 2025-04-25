package io.github.alexpercont.jakartaee.examples.dao;

import io.github.alexpercont.jakartaee.examples.persistence.entities.Expense;
import jakarta.persistence.EntityManager;

public class ExpensesDao extends JpaDataAccessObject<Expense, Long> {

    public ExpensesDao(EntityManager entityManager) {
        super(entityManager, Expense.class);
    }
}
