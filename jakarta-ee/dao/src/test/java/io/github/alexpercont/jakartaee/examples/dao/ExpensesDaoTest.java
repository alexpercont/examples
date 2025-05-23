package io.github.alexpercont.jakartaee.examples.dao;

import io.github.alexpercont.jakartaee.examples.persistence.entities.Expense;
import io.github.alexpercont.jakartaee.examples.persistence.entities.ExpenseCategory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

@ExtendWith(MockitoExtension.class)
public class ExpensesDaoTest {

    @Mock
    private EntityManager mockEm;

    private ExpensesDao getTestDao() {
        return new ExpensesDao(this.mockEm);
    }

    @Test
    public void createExpense() {
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setAmount(BigInteger.valueOf(4L));
        expense.setCategory(new ExpenseCategory());
        getTestDao().create(expense);
    }

    @Test
    public void readExpenseById() {
        Expense mockExpense = new Expense();
        Long id = 1L;
        mockExpense.setId(id);
        mockExpense.setAmount(BigInteger.valueOf(100L));
        mockExpense.setCategory(null);
        Mockito.when(this.mockEm.find(Expense.class, id)).thenReturn(mockExpense);

        Expense expense = getTestDao().read(1L);
        Assertions.assertEquals(mockExpense, expense);
    }

    @Test
    public void readExpenseInexistentId() {
        Long id = 2L;
        Mockito.when(this.mockEm.find(Expense.class, id)).thenReturn(null);
        Expense expense = getTestDao().read(id);
        Assertions.assertNull(expense);
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(this.mockEm);
    }
}
