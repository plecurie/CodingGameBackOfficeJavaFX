package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Displayer implements Initializable {

    @FXML private AnchorPane main ;
    private static Stage stage ;

    public Displayer() {
        /* todo default constructor */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main.setStyle("-fx-background-color: white");
        try {
            onHome(new ActionEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStage(Stage stage) {
        Displayer.stage = stage;
    }

    @FXML private void onHome(ActionEvent actionEvent){
        Home home = new Home();
        try {
            main.getChildren().add(home.displayHome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayDashboard(){

        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("../contents/dashboard.fxml"));
            sceneRoot.setCenter(anchorPane);
            Scene scene = new Scene(sceneRoot);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/images/favicon.png"));
            stage.setScene(scene);
            setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
