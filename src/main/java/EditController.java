import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class EditController {

    Transaction selectedTransaction;
    Account account;

    @FXML
    Label nameLabel;
    @FXML
    TextField dateField;
    @FXML
    TextField amountField;
    @FXML
    TextField detailField;
    @FXML
    Button cancelButton;
    @FXML
    Button saveButton;

    @FXML
    void initialize() {
        selectedTransaction = FirstPageController.getSelectedTransaction();
        account = FirstPageController.getAccount();
        dateField.setText(String.valueOf(selectedTransaction.getDate()));
        amountField.setText(String.valueOf(selectedTransaction.getAmount()));
        detailField.setText(String.valueOf(selectedTransaction.getDetail()));
    }

    @FXML
    void cancel(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        try {
            stage.setScene(new Scene(loader.load(),800,600));
            stage.setResizable(false);
            stage.show();
        } catch(IOException e1){
            e1.printStackTrace();
        }
    }

    @FXML
    void save(ActionEvent event) {
        String date = dateField.getText();
        String[] arrDate = date.split("-");
        int[] arrDateInt = new int[3];
        for (int i = 0; i < 3; i++) {
            arrDateInt[i] = Integer.parseInt(arrDate[i]);
        }
        selectedTransaction.setDate(LocalDate.of(arrDateInt[0],arrDateInt[1],arrDateInt[2]));
        selectedTransaction.setAmount(Double.parseDouble(amountField.getText()));
        selectedTransaction.setDetail(detailField.getText());

        System.out.println(account.formatContent());
        if (Main.filename.contains(".txt"))
            writeFile(account.formatContent(), Main.filename);
        if (Main.filename.contains(".db"))
            writeDatabase(selectedTransaction);

        Button b=(Button)event.getSource();
        Stage stage=(Stage)b.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("firstPage.fxml"));
        try{
            stage.setScene(new Scene(loader.load(),800,600));
            stage.setResizable(false);
            stage.show();
        }catch(IOException e1){
            e1.printStackTrace();
        }
    }

    private void writeFile(String content, String filename) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filename))) {
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

                String query = "update transactions set date = \"" + String.valueOf(transaction.getDate())
                        + "\", amount = " + transaction.getAmount() + ", type = \"" + transaction.getType() + "\", note = \""
                        + transaction.getDetail() + "\" where id = " + transaction.getId();
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
