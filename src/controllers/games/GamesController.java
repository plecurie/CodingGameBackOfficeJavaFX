package controllers.games;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GamesController {

    private AnchorPane main ;
    private DisplayerController displayController;

    public GamesController() {
        /* todo default constructor */
    }

    @FXML private void onFirst(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSecond(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onThird(ActionEvent actionEvent){

        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(DisplayerController displayerControllerController, AnchorPane main) {
        this.displayController = displayerControllerController;
        this.main = main;
    }
}
