package controllers.games;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GamesController {

    private DisplayerController displayController;

    @FXML private void onFirst(ActionEvent actionEvent){
        try {
            displayController.displayToolbox(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSecond(ActionEvent actionEvent){
        try {
            displayController.displayToolbox(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onThird(ActionEvent actionEvent){
        try {
            displayController.displayLevels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){
        try {
            displayController.displayToolbox(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
    }
}
