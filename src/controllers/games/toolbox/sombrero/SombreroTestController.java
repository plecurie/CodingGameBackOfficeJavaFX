package controllers.games.toolbox.sombrero;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.Game;
import services.JsonToString;
import settings.Colors;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class SombreroTestController {

    public static GridPane sombrero_test;
    public static int cell_max;
    public static String functions;
    public static int level_difficulty;

    public static GridPane buildGrid() {

        JsonObject grid_params = readGrid();
        GridPane board = new GridPane();

        JsonToString jsonToString = new JsonToString();
        List grid_list = jsonToString.parseJSON(String .valueOf(grid_params));

        int id_game;
        String name;
        int cell = 0;
        int difficulty;
        String functions;

        for (Object aList : grid_list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "id_game" : {
                    id_game = Integer.valueOf(value);
                    break;
                }
                case "name" : {
                    name = value;
                    break;
                }
                case "cell" : {
                    cell = Integer.valueOf(value);
                    break;
                }
                case "difficulty" : {
                    difficulty = Integer.valueOf(value);
                    break;
                }
                case "functions" : {
                    functions = value;
                    break;
                }
                case "grid_list" : {

                    String[] obj = aList.toString().split("[^\\w]", 2);
                    value = obj[1];
                    List cell_list = jsonToString.parseJSON(String .valueOf(value));

                    String[][] cell_color = new String[cell][cell];
                    String[][] item = new String[cell][cell];
                    int cell_i = 0, cell_y = 0;

                    for (Object bList : cell_list) {
                        String[] o = bList.toString().split(":");
                        String k = o[0];
                        String v = o[1];

                        if (cell_y == cell) {
                            cell_i++;
                            cell_y = 0;
                        }

                        switch (k) {
                            case "color" : {
                                cell_color[cell_i][cell_y] = convertColor(v);
                                break;
                            }
                            case "item" : {
                                item[cell_i][cell_y] = v;
                                cell_y++;
                                break;
                            }
                            default:
                                break;
                        }
                    }

                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    columnConstraints.setMinWidth(10.0);
                    columnConstraints.setPrefWidth(50.0);
                    columnConstraints.setHgrow(Priority.SOMETIMES);

                    RowConstraints rowConstraints = new RowConstraints();
                    rowConstraints.setMinHeight(10.0);
                    rowConstraints.setPrefHeight(30.0);
                    rowConstraints.setVgrow(Priority.SOMETIMES);

                    board.getColumnConstraints().add(columnConstraints);
                    board.getRowConstraints().add(rowConstraints);

                    Pane[][] panes = new Pane[cell][cell];
                    for (int i = 0; i < cell; i++) {
                        for (int j = 0; j < cell; j++) {
                            Pane pane = new Pane();
                            pane.setStyle(cell_color[i][j]);
                            if (item[i][j].equals("null")) item[i][j] = "";
                            pane.getChildren().add(new Label(item[i][j]));
                            pane.setMinSize(50.0,50.0);
                            panes[i][j] = pane;
                            board.add(panes[i][j], j, i);
                        }
                    }
                    break;
                }
                default:
                    break;
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

        int column = 0, line = 0;
        List<Object> grid_list = new ArrayList<>();
        String level_name = "TEST";
        String cell_color= "";

        for (int i = 0; i < sombrero_test.getChildren().size()-1 ; i++) {
            if (column == cell_max) {
                column = 0;
            }
            column++;

            Node node = sombrero_test.getChildren().get(i);
            Pane pane = (Pane) node.lookup("Pane");

            Label item = null;

                switch (pane.getStyle()) {
                    case "-fx-background-color:BLACK;" : {
                        cell_color = "BLACK";
                        if (pane.getChildren().size() > 0)
                            item = (Label) pane.getChildren().get(0).lookup("Label");
                        break;
                    }
                    case "-fx-background-color:BLUE;" : {
                        cell_color = "BLUE";
                        if (pane.getChildren().size() > 0)
                            item = (Label) pane.getChildren().get(0).lookup("Label");
                        break;
                    }
                    case "-fx-background-color:RED;" : {
                        cell_color = "RED";
                        if (pane.getChildren().size() > 0)
                            item = (Label) pane.getChildren().get(0).lookup("Label");
                        break;
                    }
                    case "-fx-background-color:GREEN;" : {
                        cell_color = "GREEN";
                        if (pane.getChildren().size() > 0)
                            item = (Label) pane.getChildren().get(0).lookup("Label");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                if (item == null){ item = new Label("null"); }
                JsonObject cell_params = Json.createObjectBuilder().add("color", cell_color).add("item", item.getText()).build();
                grid_list.add(cell_params);
        }

        return Json.createObjectBuilder().add("id_game", Game.GAME_ID)
                .add("name", level_name).add("cell", cell_max).add("difficulty", level_difficulty)
                .add("functions", functions).add("grid_list", String.valueOf(grid_list)).build();
    }
}
