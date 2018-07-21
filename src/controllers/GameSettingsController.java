package controllers;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Game;
import models.Level;
import services.dao.DAOGame;
import services.dao.DAOLevel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable {

    @FXML private ChoiceBox<String> profile_cb;
    @FXML private ChoiceBox<String> game_cb;
    @FXML private TableView<Level> tab_level;
    @FXML private TableColumn<Level, String> column_name;
    @FXML private TableColumn<Level, String> column_difficulty;

    private List<Game> games;
    private DAOGame daoGame = new DAOGame();
    private DAOLevel daoLevel = new DAOLevel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> list_profile = new ArrayList<>();
        list_profile.add("Ninja");
        list_profile.add("Prof");
        list_profile.add("Explorer");
        profile_cb.setItems(FXCollections.observableArrayList(list_profile));
        profile_cb.setValue(list_profile.get(0));

        games = daoGame.getGames();

        List<String> list_game = new ArrayList<>();
        for (Game game : games) {
            list_game.add(game.getName());
        }
        game_cb.setItems(FXCollections.observableArrayList(list_game));
        game_cb.setValue(list_game.get(0));

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        tab_level.getColumns().setAll(column_name, column_difficulty);

    }

    @FXML void onGameChange(Event event) {
        List<Level> list_level;
        for (Game game : games){
            if (game.getName().equals(game_cb.getValue())){
                list_level = daoLevel.getLevels(game.getId());
                tab_level.setItems(FXCollections.observableArrayList(list_level));
            }
        }
    }
}
