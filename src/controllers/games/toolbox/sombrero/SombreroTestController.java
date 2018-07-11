package controllers.games.toolbox.sombrero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.Game;
import services.JsonToString;
import settings.Colors;

import javax.json.Json;
import javax.json.JsonObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SombreroTestController implements Initializable {

    public static GridPane sombrero_test;
    public static int cell_max;
    public static String level_name;
    public static int f1;
    public static int f2;
    public static int f3;
    public static int f4;
    public static int level_difficulty;

    private static int ID_GAME;
    private static int CELL;
    private static String NAME;
    private static int F1;
    private static int F2;
    private static int F3;
    private static int F4;
    private static int DIFFICULTY;

    @FXML private Label level_label;
    @FXML private GridPane gridPane_f1;
    @FXML private GridPane gridPane_f2;
    @FXML private GridPane gridPane_f3;
    @FXML private GridPane gridPane_f4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level_label.setText(NAME);

        gridPane_f1.getChildren().clear();
        for (int i = 0; i < F1; i++) {
            Pane pane = buildCommandPane();
            gridPane_f1.addColumn(i, pane);
        }
        System.out.println(gridPane_f1.isGridLinesVisible());

        gridPane_f2.getChildren().clear();
        for (int i = 0; i < F2; i++) {
            Pane pane = buildCommandPane();
            gridPane_f2.addColumn(i, pane);
        }

        gridPane_f3.getChildren().clear();
        for (int i = 0; i < F3; i++) {
            Pane pane = buildCommandPane();
            gridPane_f3.addColumn(i, pane);
        }

        gridPane_f4.getChildren().clear();
        for (int i = 0; i < F4; i++) {
            Pane pane = buildCommandPane();
            gridPane_f4.addColumn(i, pane);
        }
    }

    private Pane buildCommandPane() {
        Pane pane = new Pane();
        pane.setPrefSize(48.0,48.0);
        pane.setStyle(Colors.LIGHTGREY);
        pane.setBorder((new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        return pane;
    }

    public static GridPane buildGrid() {

        JsonObject grid_params = readGrid();
        GridPane board = new GridPane();

        JsonToString jsonToString = new JsonToString();
        List grid_list = jsonToString.parseJSON(String .valueOf(grid_params));

        for (Object aList : grid_list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "id_game" : {
                    ID_GAME = Integer.valueOf(value);
                    break;
                }
                case "name" : {
                    NAME = value;
                    break;
                }
                case "cell" : {
                    CELL = Integer.valueOf(value);
                    break;
                }
                case "difficulty" : {
                    DIFFICULTY = Integer.valueOf(value);
                    break;
                }
                case "f1" : {
                    F1 = Integer.valueOf(value);
                    break;
                }
                case "f2" : {
                    F2 = Integer.valueOf(value);
                    break;
                }
                case "f3" : {
                    F3 = Integer.valueOf(value);
                    break;
                }
                case "f4" : {
                    F4 = Integer.valueOf(value);
                    break;
                }
                case "grid_list" : {

                    String[][] color_cell = new String[CELL][CELL];
                    String[][] item_cell = new String[CELL][CELL];
                    int cell_i = 0, cell_y = 0;

                    String[] obj = aList.toString().split("[^\\w]", 2);
                    value = obj[1];

                    List cell_list = jsonToString.parseJSON(String .valueOf(value));

                    for (Object bList : cell_list) {

                        String[] o = bList.toString().split(":");
                        String k = o[0];
                        String v = o[1];

                        if (cell_y == CELL) {
                            cell_i++;
                            cell_y = 0;
                        }

                        switch (k) {
                            case "color" : {
                                color_cell[cell_i][cell_y] = convertColor(v);
                                break;
                            }
                            case "description" : {
                                item_cell[cell_i][cell_y] = v;
                                cell_y++;
                                break;
                            }
                            default: break;
                        }
                    }

                    for (int i = 0; i < CELL; i++) {
                        for (int j = 0; j < CELL; j++) {

                            Pane pane = new Pane();
                            pane.setStyle(color_cell[i][j]);

                            if (item_cell[i][j].equals("null")){
                                item_cell[i][j] = "";
                            }
                            pane.getChildren().add(new Label(item_cell[i][j]));

                            if (CELL == 10){
                                pane.setMinSize(60.0,60.0);
                            } else if (CELL == 15){
                                pane.setMinSize(40.0,40.0);
                            }
                            board.add(pane, j, i);
                        }
                    }break;
                }
                default: break;
            }
        }
        board.setGridLinesVisible(true);
        board.setVisible(true);

        return board;
    }

    @FXML private void onPlay() {}

    @FXML private void onNext() {}

    @FXML private void onStop() {}

    private static String convertColor(String cell_color){

        String str_color = "";

        if (cell_color.equals("BLACK")) str_color = Colors.BLACK;
        if (cell_color.equals("BLUE")) str_color = Colors.BLUE;
        if (cell_color.equals("GREEN")) str_color = Colors.GREEN;
        if (cell_color.equals("RED")) str_color = Colors.RED;

        return str_color;
    }

    private static JsonObject readGrid() {

        int column = 0;
        List<Object> grid_list = new ArrayList<>();
        String cell_color= "";

        for (int i = 0; i < sombrero_test.getChildren().size()-1 ; i++) {
            if (column == cell_max) column = 0;
            column++;

            Node node = sombrero_test.getChildren().get(i);
            Pane pane = (Pane) node.lookup("Pane");
            Label description = null;

                switch (pane.getStyle()) {
                    case "-fx-background-color:BLACK;" : {
                        cell_color = "BLACK";
                        if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");

                        break;
                    }
                    case "-fx-background-color:BLUE;" : {
                        cell_color = "BLUE";
                        if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                        break;
                    }
                    case "-fx-background-color:RED;" : {
                        cell_color = "RED";
                        if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                        break;
                    }
                    case "-fx-background-color:LIMEGREEN;" : {
                        cell_color = "GREEN";
                        if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                        break;
                    }
                    default:
                        break;
                }
                if (description == null) description = new Label("null");
                JsonObject cell_params = Json.createObjectBuilder().add("color", cell_color).add("description", description.getText()).build();
                grid_list.add(cell_params);
        }

        return Json.createObjectBuilder().add("id_game", Game.GAME_ID)
                .add("name", level_name).add("cell", cell_max).add("difficulty", level_difficulty)
                .add("f1", f1).add("f2", f2).add("f3", f3).add("f4", f4).add("grid_list", String.valueOf(grid_list)).build();
    }


}
