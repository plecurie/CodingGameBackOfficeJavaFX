package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.dao.DAOAuth;

public class StarterController {

    private static Stage stage;

    @FXML private TextField textField;
    @FXML private PasswordField passwordField;

    private DAOAuth daoAuth = new DAOAuth();

    public StarterController() {
        /* todo default constructor */
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void displayStarter() {
        BorderPane sceneRoot = new BorderPane();
        try {
            final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/authentification.fxml"));
            sceneRoot.setCenter(anchorPane);
            Scene scene = new Scene(sceneRoot);
            stage.getIcons().add(new Image("/contents/images/favicon.png"));
            stage.setTitle("Pepit'CodingGame - Authentification");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onValidButton(ActionEvent actionEvent) {

        String username = textField.getText();
        String password = passwordField.getText();

        try{
                if (daoAuth.signIn(username,password)) {
                    stage.close();
                    DisplayerController displayerController = new DisplayerController();
                    displayerController.displayDashboard();
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
