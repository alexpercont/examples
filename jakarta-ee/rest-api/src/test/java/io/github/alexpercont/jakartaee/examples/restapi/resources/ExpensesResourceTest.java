package io.github.alexpercont.jakartaee.examples.restapi.resources;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class ExpensesResourceTest {

    @Test
    void getExpensesReturnsListOfExpenses() {
        ExpensesResource resource = new ExpensesResource();
        List<String> expenses = resource.getExpenses();
        assertEquals(List.of("Rent", "Food", "Transportation"), expenses);
    }

    @Test
    void getExpensesReturnsNonEmptyList() {
        ExpensesResource resource = new ExpensesResource();
        List<String> expenses = resource.getExpenses();
        assertEquals(3, expenses.size());
    }
}