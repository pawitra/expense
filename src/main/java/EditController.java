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
        writeFile(account.formatContent(), Main.filename);
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



}
