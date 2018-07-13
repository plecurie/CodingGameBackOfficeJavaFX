package controllers.games.toolbox.sombrero;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Game;
import services.JsonToString;
import settings.Colors;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static settings.Colors.convertColor;

public class SombreroFactory {

    public static Sombrero sombrero_to_break;

    private static int difficulty;
    private static int cell;
    private static String name;
    private static int F1;
    private static int F2;
    private static int F3;
    private static int F4;
    private static ImageView arrow;

    public static GridPane buildSombrero() {

        JsonObject grid_params = breakSombreroToJson();
        GridPane board = new GridPane();

        JsonToString jsonToString = new JsonToString();
        List grid_list = jsonToString.parseJSON(String .valueOf(grid_params));

        for (Object aList : grid_list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
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

                    String[][] color_cell = new String[cell][cell];
                    String[][] item_cell = new String[cell][cell];
                    int cell_i = 0, cell_y = 0;

                    String[] obj = aList.toString().split("[^\\w]", 2);
                    value = obj[1];

                    List cell_list = jsonToString.parseJSON(String .valueOf(value));

                    for (Object bList : cell_list) {

                        String[] o = bList.toString().split(":");
                        String k = o[0];
                        String v = o[1];

                        if (cell_y == cell) {
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

                    for (int i = 0; i < cell; i++) {
                        for (int j = 0; j < cell; j++) {

                            Pane pane = new Pane();
                            pane.setStyle(color_cell[i][j]);

                            if (item_cell[i][j].equals("null")){
                                item_cell[i][j] = "";
                            }

                            if (cell == 10){
                                pane.setMinSize(60.0,60.0);
                            } else if (cell == 15){
                                pane.setMinSize(40.0,40.0);
                            }

                            switch (item_cell[i][j]) {
                                case "up" : {
                                    arrow = setArrowOrientation(270);
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "down" : {
                                    arrow = setArrowOrientation(90);
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "left" : {
                                    arrow = setArrowOrientation(180);
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "right" : {
                                    arrow = setArrowOrientation(0);
                                    pane.getChildren().add(arrow);
                                    break;
                                }
                                case "item" : {
                                    ImageView imageView = new ImageView("file:src/contents/images/stargold.png");
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

    private static ImageView setArrowOrientation(int rotate) {
        ImageView imageView = new ImageView("file:src/contents/images/arrow.png");
        imageView.setRotate(rotate);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);
        return imageView;
    }

    private static JsonObject breakSombreroToJson() {

        GridPane gridPane = sombrero_to_break.getGridpane();
        String level_name = sombrero_to_break.getName();
        int cell = sombrero_to_break.getCellCount();
        int level_difficulty = sombrero_to_break.getDifficulty();
        int f1 = sombrero_to_break.getF1();
        int f2 = sombrero_to_break.getF2();
        int f3 = sombrero_to_break.getF3();
        int f4 = sombrero_to_break.getF4();

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

    static String getName() {
        return name;
    }
    static int getF1() {
        return F1;
    }
    static int getF2() {
        return F2;
    }
    static int getF3() {
        return F3;
    }
    static int getF4() {
        return F4;
    }
    static ImageView getArrow() {
        return arrow;
    }

}
