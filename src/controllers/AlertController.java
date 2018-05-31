package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable {

    @FXML private Label error_message;
    private static String message;
    private static final Stage stage = new Stage();

    public AlertController() {/*todo default constructor*/}
    public static void showAlert(String message){
        AlertController.message = message;
        DisplayerController displayerController = new DisplayerController();
        displayerController.setStage(stage);
        displayerController.displayAlert();
    }

    @Override public void initialize(URL location, ResourceBundle resources){error_message.setText(AlertController.message);}
    @FXML protected void onClick() { stage.close(); }

}
