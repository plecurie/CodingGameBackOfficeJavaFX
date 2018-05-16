package controllers.games;

import controllers.Displayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Games {

    private Displayer displayer = Displayer.getCurrentDisplayer();

    public Games() {
        /* todo default constructor */
    }

    @FXML private void onFirst(ActionEvent actionEvent){

    }

    @FXML private void onSecond(ActionEvent actionEvent){

    }

    @FXML private void onThird(ActionEvent actionEvent){

        try {
            Displayer.currentDisplayer.displaySelectedLevel();
            displayer.displaySelectedLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){

    }
}
