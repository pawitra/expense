import java.util.Date;

public class Transaction {
    private Date date;
    private double amount;
    private String detail;

    Transaction(double amount, Date date, String detail) {
        this.amount = amount;
        this.date = date;
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

}