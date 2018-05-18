package controllers.games;

import controllers.Displayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Games {

    private AnchorPane main ;
    private Displayer displayController;

    public Games() {
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

    public void setMain(Displayer displayerController, AnchorPane main) {
        this.displayController = displayerController;
        this.main = main;
    }
}
