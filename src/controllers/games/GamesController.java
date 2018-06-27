package controllers.games;

import controllers.DisplayerController;
import dao.DAOGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GamesController implements Initializable {

    private DisplayerController displayController = new DisplayerController();
    private DAOGame daoGame = new DAOGame();
    private List<Game> list_games = daoGame.getGames();
    private Game selected_game;

    @FXML private Button button_1;
    @FXML private Button button_2;
    @FXML private Button button_3;
    @FXML private Button button_4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Button> buttons = new ArrayList<Button>();
        buttons.add(button_1);
        buttons.add(button_2);
        buttons.add(button_3);
        buttons.add(button_4);

        for (int i = 0; i < list_games.size(); i++) {
            buttons.get(i).setText(list_games.get(i).getName());
        }
    }

    @FXML private void onFirst(ActionEvent actionEvent){
        selected_game = list_games.get(0);
        try {
            displayController.displayToolbox(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSecond(ActionEvent actionEvent){
        selected_game = list_games.get(1);
        try {
            displayController.displayToolbox(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onThird(ActionEvent actionEvent){
        selected_game = list_games.get(2);
        try {
            displayController.displaySombreroLevels(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onFourth(ActionEvent actionEvent){
        selected_game = list_games.get(3);
        try {
            displayController.displayToolbox(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void linkDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
    }

}
