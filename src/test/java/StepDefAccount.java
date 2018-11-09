import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefAccount {
    Account account;

    @Given("^a customer with initial balance (\\d+) exists at (\\d+)/(\\d+)/(\\d+)-(\\d+):(\\d+) from (\\d+)$")
    public void a_customer_with_initial_balance_exists(double money,String date,String detail) throws Throwable {
        account = new Account(money,date,detail);
    }

    @When("^I deposit (\\d+) to my account at (\\d+)/(\\d+)/(\\d+)-(\\d+):(\\d+)$")
    public void i_deposit_to_my_account(double money,String date,String detail) throws Throwable{
        account.deposit(money,date,detail);
    }

    @Then("^my account balance is (\\d+)$")
    public void my_account_balance_is(double balance) throws Throwable {
        assertEquals(balance,account.getBalance());
    }
    @And("^my account income is (\\d+)$")
    public void my_account_income_is(double balance) throws Throwable {
        assertEquals(balance,account.getIncome());
    }

    @When("^I withdraw (\\d+) from my account at (\\d+)/(\\d+)/(\\d+)-(\\d+):(\\d+)$")
    public void I_withdraw_from_my_account(double money,String date,String detail)throws Throwable{
        account.withdraw(money,date,detail);
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
