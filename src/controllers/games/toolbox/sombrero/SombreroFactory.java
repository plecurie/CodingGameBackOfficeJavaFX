package controllers.games.toolbox.sombrero;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Game;
import services.DataFactory;
import settings.Colors;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static settings.Colors.convertColor;

public class SombreroFactory {

    public JsonObject convertSombreroToJsonObject(Sombrero sombrero) {

        GridPane gridPane = sombrero.getGridpane();
        String level_name = sombrero.getName();
        int cell = sombrero.getCellCount();
        int level_difficulty = sombrero.getDifficulty();
        int f1 = sombrero.getF1();
        int f2 = sombrero.getF2();
        int f3 = sombrero.getF3();
        int f4 = sombrero.getF4();

        int column = 0;
        List<Object> grid_list = new ArrayList<>();
        String cell_color= "";

        for (int i = 0; i < gridPane.getChildren().size()-1 ; i++) {
            if (column == cell) column = 0;
            column++;

            Node node = gridPane.getChildren().get(i);
            Pane pane = (Pane) node.lookup("Pane");
            Label description = null;

            switch (pane.getStyle()) {
                case Colors.BLACK : {
                    cell_color = "BLACK";
                    if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                    break;
                }
                case Colors.BLUE : {
                    cell_color = "BLUE";
                    if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                    break;
                }
                case Colors.RED : {
                    cell_color = "RED";
                    if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                    break;
                }
                case Colors.GREEN : {
                    cell_color = "GREEN";
                    if (pane.getChildren().size() > 0) description = (Label) pane.getChildren().get(1).lookup("Label");
                    break;
                }
                default:
                    break;
            }
            if (description == null) description = new Label("null");
            JsonObject cell_params = Json.createObjectBuilder().add("COLOR", cell_color).add("description", description.getText()).build();
            grid_list.add(cell_params);
        }
        return Json.createObjectBuilder().add("id_game", Game.GAME_ID)
                .add("name", level_name).add("cell", cell).add("difficulty", level_difficulty)
                .add("f1", f1).add("f2", f2).add("f3", f3).add("f4", f4).add("grid_list", String.valueOf(grid_list)).build();
    }


    public void browseAndBuildSombrero(List sombrero_list) {

        int difficulty = 0;
        int cell_count = 0;
        String name = "";
        int f1 = 0;
        int f2 = 0;
        int f3 = 0;
        int f4 = 0;
        GridPane board = new GridPane();

        for (Object object : sombrero_list) {
            String[] list_objet = object.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "name" : {
                    name = value;
                    break;
                }
                case "cell" : {
                    cell_count = Integer.valueOf(value);
                    break;
                }
                case "difficulty" : {
                    difficulty = Integer.valueOf(value);
                    break;
                }
                case "f1" : {
                    f1 = Integer.valueOf(value);
                    break;
                }
                case "f2" : {
                    f2 = Integer.valueOf(value);
                    break;
                }
                case "f3" : {
                    f3 = Integer.valueOf(value);
                    break;
                }
                case "f4" : {
                    f4 = Integer.valueOf(value);
                    break;
                }
                case "grid_list" : {

                    String[] obj = object.toString().split("[^\\w]", 2);
                    value = obj[1];

                    String[][] color_cell = new String[cell_count][cell_count];
                    String[][] item_cell = new String[cell_count][cell_count];
                    int cell_i = 0, cell_y = 0;

                    List cell_list = new DataFactory().parseJSON(String.valueOf(value));

                    for (Object aList : cell_list) {
                        String[] o = aList.toString().split(":");
                        String k = o[0];
                        String v = o[1];

                        if (cell_y == cell_count) {
                            cell_i++;
                            cell_y = 0;
                        }

                        switch (k) {
                            case "COLOR" : {
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

                    for (int i = 0; i < cell_count; i++) {
                        for (int j = 0; j < cell_count; j++) {

                            Pane pane = new Pane();
                            pane.setStyle(color_cell[i][j]);
                            SombreroItem arrow;

                            if (item_cell[i][j].equals("null")){
                                item_cell[i][j] = "";
                            }

                            if (cell_count == 10){
                                pane.setMinSize(60.0,60.0);
                            } else if (cell_count == 15){
                                pane.setMinSize(40.0,40.0);
                            }

                            switch (item_cell[i][j]) {
                                case "up" : {
                                    arrow = setArrowOrientation(270,i,j);
                                    SombreroItem.setArrow(arrow, arrow.getX(), arrow.getY());
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "down" : {
                                    arrow = setArrowOrientation(90,i,j);
                                    SombreroItem.setArrow(arrow, arrow.getX(), arrow.getY());
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "left" : {
                                    arrow = setArrowOrientation(180,i,j);
                                    SombreroItem.setArrow(arrow, arrow.getX(), arrow.getY());
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "right" : {
                                    arrow = setArrowOrientation(0,i,j);
                                    SombreroItem.setArrow(arrow, arrow.getX(), arrow.getY());
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "item" : {
                                    SombreroItem imageView = new SombreroItem("file:src/contents/images/stargold.png", i, j);
                                    imageView.setFitHeight(30);
                                    imageView.setFitWidth(30);
                                    imageView.setLayoutX(15);
                                    imageView.setLayoutY(15);
                                    pane.getChildren().add(imageView);
                                    break;
                                }
                                default:
                                    break;
                            }
                            GridPane.setRowIndex(pane, i);
                            GridPane.setColumnIndex(pane, j);
                            board.getChildren().addAll(pane);
                        }
                    }
                    board.setGridLinesVisible(true);
                    board.setVisible(true);
                    Sombrero.setSelectedSombrero(new Sombrero(board, name, f1, f2, f3, f4, difficulty,cell_count));
                    break;
                }
                default:
                    break;
            }
        }
    }

    GridPane buildEmptySombrero(int cell_count) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < cell_count; i++) {
            for (int j = 0; j < cell_count; j++) {
                Pane pane = new Pane();
                pane.setStyle(Colors.BLACK);
                GridPane.setRowIndex(pane, i);
                GridPane.setColumnIndex(pane, j);
                gridPane.getChildren().addAll(pane);
            }
        }
        gridPane.setGridLinesVisible(true);
        gridPane.setVisible(true);
        return gridPane;
    }


    private static SombreroItem setArrowOrientation(int rotate, int rowId, int columnId) {
        SombreroItem imageView = new SombreroItem("file:src/contents/images/arrow.png",rowId,columnId);
        imageView.setRotate(rotate);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);
        return imageView;
    }

}
