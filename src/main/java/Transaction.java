import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String type;
    private double amount;
    private String detail;
    private int id;


    public Transaction(LocalDate date, double amount, String detail) {
        this.date = date;
        if (amount<0) type = "expense";
        else type = "deposit";
        this.amount = amount;
        this.detail = detail;
    }

    public Transaction(LocalDate date, double amount) {
        this.date = date;
        if (amount<0) type = "expense";
        else type = "deposit";
        this.amount = amount;
        this.detail = "";
    }

    public String formatContent() {
        String content = "";
        content += date;
        content += "      ";
        content += amount;
        content += "      ";
        content += detail;
        content += "\n";
        return content;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
        if (amount<0) type = "expense";
        else type = "deposit";
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}