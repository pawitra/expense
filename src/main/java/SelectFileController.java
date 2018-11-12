import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectFileController {
    @FXML
    protected Button textButton;
    @FXML
    protected Button databaseButton;

    @FXML
    void selectText(ActionEvent event) {
        Main.filename = "historyOfExpense.txt";
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

    @FXML
    void selectDatabase(ActionEvent event) {
        Main.filename = "historyOfExpenseDB.db";
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
}
