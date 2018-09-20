import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Account {

//    private double balance;
//    private double income;
//    private double expenses;

    private static SimpleDateFormat formatDate = new SimpleDateFormat("DD/MM/YYYY-hh:mm");
    private ArrayList<Transaction> transactions;
    private Transaction t1;
    private double amount = 0;

    public Account(double amount, String date,String detail)throws ParseException {
        transactions = new ArrayList<Transaction>();
        t1=new Transaction(amount, formatDate.parse(date),detail);
        transactions.add(t1);
//        balance = amount;
//        income = amount;
//        expenses = 0;
    }


    public void deposit(double income, String date, String detail) throws ParseException {
        t1 = new Transaction(income, formatDate.parse(date), detail);
        transactions.add(t1);
    }

    public void withdraw(double expense, String date, String detail) throws ParseException {
        if (expense <= getBalance()) {
            t1=new Transaction(-expense, formatDate.parse(date), detail);
            transactions.add(t1);
        }
    }


    public double getBalance() {
        amount = 0;
        for (Transaction ts : transactions)
            amount += ts.getAmount();
        return amount;
    }

    public double getIncome() {
        amount = 0;
        for (Transaction transaction : transactions)
            if (transaction.getAmount() > 0)
                amount -= transaction.getAmount();
        return amount;
    }

    public double getExpenses() {
        amount = 0;
        for (Transaction transaction : transactions)
            if (transaction.getAmount() < 0)
                amount -= transaction.getAmount();
        return amount;
    }


}
