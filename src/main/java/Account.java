import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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
        if (filename.contains(".txt")){
            if (filename.length() > 0) readFile(filename);
        }else if (filename.contains(".db")){
            try {
                Class.forName("org.sqlite.JDBC");
                String dbURL = "jdbc:sqlite:historyOfExpenseDB.db";
                Connection conn = DriverManager.getConnection(dbURL);

                if (conn != null) {
                    System.out.println("Connected to the database.");
                    // display database information
                    DatabaseMetaData dm = conn.getMetaData();
                    System.out.println("Driver name: " + dm.getDriverName());
                    System.out.println("Product name: " + dm.getDatabaseProductName());

                    System.out.println("----- Data in Transactions table -----");

                    String query = "select * from transactions";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String date = resultSet.getString(2);
                        double amount = resultSet.getDouble(3);
                        String type = resultSet.getString(4);
                        String detail = resultSet.getString(5);
                        add(new Transaction(convertToDate(date),amount,detail));
                        System.out.println("id:"+id+" date:"+date+" amount:"+amount+" type: "+type+" note: "+detail);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Account(String name) {
        this(name, "");
    }

    public void add(Transaction transaction){
        transactions.add(transaction);
        transaction.setId(transactions.indexOf(transaction)+1);
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
