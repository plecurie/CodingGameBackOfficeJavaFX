package controllers.games.levels;

import controllers.Displayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Levels {
    public Levels() {
        /* todo default constructor */
    }

    private AnchorPane main ;
    private Displayer displayController;

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displaySelectedLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Displayer displayerController, AnchorPane main) {
        this.displayController = displayerController;
        this.main = main;
    }
}
