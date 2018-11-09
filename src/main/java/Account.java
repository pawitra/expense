import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<Transaction> transactions;

    public Account(String name, String filename) {
        this.name = name;
        transactions = new ArrayList<>();
        if (filename.length() > 0) readFile(filename);
    }

    public Account(String name) {
        this(name, "");
    }

    public void add(Transaction transaction){
        transactions.add(transaction);
    }


    public boolean remove(Transaction transaction){
        return transactions.remove(transaction);
    }

    public Transaction getTransaction(int i){
        return transactions.get(i);
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        double result = 0;
        for (Transaction t :
                transactions) {
            result += t.getAmount();
        }
        return result;
    }

    public ObservableList getList(){
        return FXCollections.observableArrayList(transactions);
    }

    public String formatContent() {
        String content = "";
        for (Transaction t :
                transactions) {
            content += t.formatContent();
        }
        return content;
    }

    private LocalDate convertToDate(String currentDate) {
        String[] date = currentDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < 3; i++) {
            intDate[i]=Integer.parseInt(date[i]);
        }
        return LocalDate.of(intDate[0], intDate[1], intDate[2]);
    }

    private void readFile(String filename) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                // "\\s" == any spaces
                String[] splitLine = line.split("\\s+");
                add(new Transaction(
                        convertToDate(splitLine[0]), Double.parseDouble(splitLine[1]), splitLine[2]
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
