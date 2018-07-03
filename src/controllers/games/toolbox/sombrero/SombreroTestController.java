package controllers.games.toolbox.sombrero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Game;
import services.dao.DAOLevel;

import javax.json.Json;
import javax.json.JsonObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SombreroTestController implements Initializable {
    public static GridPane grid_test;
    public static int cell_max;
    public static String functions;
    private static String level_name;
    public static int level_difficulty;

    private DAOLevel daoLevel = new DAOLevel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readGrid();
    }

    @FXML private void onPlay() {

    }

    @FXML private void onNext() {

    }

    @FXML private void onStop() {

    }

    private void readGrid() {

        int column = 0, line = 0;
        List<Object> grid_list = new ArrayList<>();
        level_name = "TEST";


        for (int i = 0; i < grid_test.getChildren().size() ; i++) {
            if (column == cell_max) {
                column = 0;
                line++;
            }
            if (column == 0 && line < cell_max) grid_list.add("line:" + line);
            column++;

            Node node = grid_test.getChildren().get(i);
            Pane pane = (Pane) node.lookup("Pane");
            try {
                switch (pane.getStyle()) {
                    case "-fx-background-color:BLACK;" : {
                        grid_list.add("color:BLACK");
                        break;
                    }
                    case "-fx-background-color:BLUE;" : {
                        grid_list.add("color:BLUE");
                        break;
                    }
                    case "-fx-background-color:RED;" : {
                        grid_list.add("color:RED");
                        break;
                    }
                    case "-fx-background-color:GREEN;" : {
                        grid_list.add("color:GREEN");
                        break;
                    }
                }
                Label item = (Label) pane.getChildren().get(0).lookup("Label");
                grid_list.add("item:"+item.getText());
            } catch (Exception ignored) {}
        }

        JsonObject parameters = Json.createObjectBuilder().add("id_game", Game.GAME_ID)
                .add("level_name", level_name).add("level_difficulty", level_difficulty)
                .add("functions", functions).add("grid_list", String.valueOf(grid_list)).build();

        System.out.println(parameters);
        //daoLevel.createLevel(Game.GAME_ID, level_name, level_difficulty, functions, grid_list);
    }
}
