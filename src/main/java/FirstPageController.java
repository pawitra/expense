import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class FirstPageController {


    private static Account account = new Account(Main.nameAccount, Main.filename);
    private static Transaction selectedTransaction;

    @FXML
    protected Label nameLabel;
    @FXML
    protected Button addButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button backButton;
    @FXML
    protected TextField dateField;
    @FXML
    protected TextField amountField;
    @FXML
    protected TextField detailField;
    @FXML
    protected TableView<Transaction> tableView;
    @FXML
    protected TableColumn dateCol;
    @FXML
    protected TableColumn amountCol;
    @FXML
    protected TableColumn typeCol;
    @FXML
    protected TableColumn detailCol;

    @FXML
    void initialize(){
        nameLabel.setText(account.getName());
        dateField.setText(String.valueOf(LocalDate.now()));
        displayTable();
    }

    @FXML
    void add(ActionEvent event){
        String dateNow = dateField.getText();
        String amountNow = amountField.getText();
        String detailNow = detailField.getText();
        double amount = Double.parseDouble(amountNow);
        Transaction currentTransaction = new Transaction(convertToDate(dateNow), amount, detailNow);
        account.add(currentTransaction);
        displayTable();
        amountField.setText("");
        detailField.setText("");
        String content = currentTransaction.formatContent();
        if (Main.filename.contains(".txt"))
            writeFile(content, Main.filename);
        if (Main.filename.contains(".db"))
            writeDatabase(currentTransaction);

    }

    @FXML
    void edit(ActionEvent event){
        selectedTransaction = tableView.getSelectionModel().getSelectedItem();
        Button b=(Button)event.getSource();
        Stage stage=(Stage)b.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("edit.fxml"));
        try{
            stage.setScene(new Scene(loader.load(),800,600));
            stage.setResizable(false);
            stage.show();
        }catch(IOException e1){
            e1.printStackTrace();
        }

    }

    @FXML
    void back(ActionEvent event){
        Button b=(Button)event.getSource();
        Stage stage=(Stage)b.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("selectFile.fxml"));
        try{
            stage.setScene(new Scene(loader.load(),300,300));
            stage.setResizable(false);
            stage.show();
        }catch(IOException e1){
            e1.printStackTrace();
        }

    }

    public static Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public static Account getAccount() {
        return account;
    }

    private LocalDate convertToDate(String currentDate) {
        String[] date = currentDate.split("-");
        int[] intDate = new int[3];
        for (int i = 0; i < 3; i++) {
            intDate[i]=Integer.parseInt(date[i]);
        }
        return LocalDate.of(intDate[0], intDate[1], intDate[2]);
    }

    private void displayTable() {
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("amount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
        detailCol.setCellValueFactory(new PropertyValueFactory<Transaction,String>("detail"));
        tableView.setItems(account.getList());
        tableView.getColumns().setAll(dateCol, amountCol, typeCol, detailCol);
    }

    private void writeFile(String content, String filename) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename, true))) {
            buffer.write(content);
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDatabase(Transaction transaction){
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

                String query = "insert into transactions values ("
                        + transaction.getId() + ", \"" + String.valueOf(transaction.getDate()) + "\", " + transaction.getAmount()
                        + ", \"" + transaction.getType() + "\", \"" + transaction.getDetail() + "\")";
                Statement statement = conn.createStatement();
                statement.executeQuery(query);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
