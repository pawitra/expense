import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefAccount {
    Account account;

    @Given("^a customer with initial balance (\\d+) exists$")
    public void a_customer_with_initial_balance_exists(double money) throws Throwable {
        account = new Account(money);
    }

    @When("^I deposit (\\d+) to my account$")
    public void i_deposit_to_my_account(double money) throws Throwable{
        account.deposit(money);
    }

    @Then("^my account balance is (\\d+)$")
    public void my_account_balance_is(double balance) throws Throwable {
        assertEquals(balance,account.getBalance());
    }
    @And("^my account income is (\\d+)$")
    public void my_account_income_is(double balance) throws Throwable {
        assertEquals(balance,account.getIncome());
    }

    @When("^I withdraw (\\d+) from my account$")
    public void I_withdraw_from_my_account(double money)throws Throwable{
        account.withdraw(money);
    }
    @Then("^my account balance  is (\\d+)$")
    public void my_account_balance_after_withdraw_is(double money)throws Throwable {
        assertEquals(money,account.getBalance());
    }
    @And("^my account expense is (\\d+)$")
    public void my_account_expense_is(double money) throws Throwable{
        assertEquals(money,account.getExpenses());
    }

}
