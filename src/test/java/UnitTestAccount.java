import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTestAccount {
    Account account;

    @BeforeEach
    void init() {
        account = new Account(100);
    }

//    @Test
//    void testDeposit() {
//        account.deposit(500);
//        assertEquals(600,account.getBalance());
//    }
//
//    @Test
//    void testWithdrawNotOverBalance() {
//        account.withdraw(50);
//        assertEquals(50,account.getBalance());
//    }
//
//    @Test
//    void testWithdrawOverBalance() {
//        account.withdraw(200);
//        assertEquals(100,account.getBalance());
//    }
//
//    @Test
//    void testShowIncome() {
//        account.deposit(500);
//        assertEquals(600,account.getIncome());
//    }
//
//    @Test
//    void testShowExpenses() {
//        account.withdraw(50);
//        assertEquals(50,account.getExpenses());
//    }


}
