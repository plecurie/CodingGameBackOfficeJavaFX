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
            main.getChildren().add(displayController.displayLevels(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSecond(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onThird(ActionEvent actionEvent){

        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displayLevels(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(DisplayerController displayerController, AnchorPane main) {
        this.displayController = displayerController;
        this.main = main;
    }
}
