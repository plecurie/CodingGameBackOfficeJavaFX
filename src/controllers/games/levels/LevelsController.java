package controllers.games.levels;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LevelsController {
    public LevelsController() {
        /* todo default constructor */
    }

    private AnchorPane main ;
    private DisplayerController displayController;

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displaySelectedLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(DisplayerController displayerControllerController, AnchorPane main) {
        this.displayController = displayerControllerController;
        this.main = main;
    }
}
