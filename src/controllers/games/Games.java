package controllers.games;

import controllers.games.levels.Levels;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Games {

    public Games() {
        /* todo default constructor */
    }

    @FXML private void onFirst(ActionEvent actionEvent){
        //dashboard.onSelectedGame(1);
        try {
            //displayer.displayUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSecond(ActionEvent actionEvent){
        Levels levels = new Levels();
        try {
            levels.displayLevels(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onThird(ActionEvent actionEvent){
        Levels levels = new Levels();
        try {
            levels.displayLevels(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){
        Levels levels = new Levels();
        try {
            levels.displayLevels(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
