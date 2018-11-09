import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UnitTestAccount {
    private Account account;

    @BeforeEach
    void init() {
        account = new Account("Pawitra");
    }

    @Test
    void getCurrencyTest() {
        assertEquals(0, account.getTotal());
    }

    @Test
    void addIncomeTest() {
        account.add(new Transaction(LocalDate.now(), 500, ""));
        assertEquals(500, account.getTotal());
    }

    @Test
    void addExpenseTest() {
        account.add(new Transaction(LocalDate.now(), -50, ""));
        assertEquals(-50, account.getTotal());
    }

    @Test
    void expenseMoreThanIncomeTest() {
        account.add(new Transaction(LocalDate.now(), 50, ""));
        account.add(new Transaction(LocalDate.now(), -500, ""));
        assertEquals(-450, account.getTotal());
    }

    @Test
    void incomeMoreThanExpenseTest() {
        account.add(new Transaction(LocalDate.now(), 500, ""));
        account.add(new Transaction(LocalDate.now(), -50, ""));
        assertEquals(450, account.getTotal());
    }

}
