package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Home {
    public Home() {
        /* todo default constructor */
    }

    private AnchorPane main ;
    private Displayer displayController;

    @FXML
    private void onGames(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayGames());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onUsers(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Displayer displayController, AnchorPane main) {
        this.displayController = displayController;
        this.main = main;
    }
}
