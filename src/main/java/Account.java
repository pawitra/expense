public class Account {

    private double balance;
    private double income;
    private double expenses;

    public Account(double init) {
            balance = init;
            income = init;
            expenses = 0;
    }

    public void deposit(double amount) {
        balance += amount;
        income += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance){
            balance -= amount;
            expenses += amount;
        }

    }

    public double getBalance() {
        return balance;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

}
