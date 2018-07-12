package controllers.games.toolbox.sombrero;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private static int DIFFICULTY;
    private static int CELL;
    private static String NAME;
    private static int F1;
    private static int F2;
    private static int F3;
    private static int F4;
    private static String ACTION;
    private static String COLOR;

    @FXML private Label level_label;
    @FXML private Label f1_label;
    @FXML private Label f2_label;
    @FXML private Label f3_label;
    @FXML private Label f4_label;
    @FXML private GridPane gridPane_f1;
    @FXML private GridPane gridPane_f2;
    @FXML private GridPane gridPane_f3;
    @FXML private GridPane gridPane_f4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level_label.setText("TESTING: " + NAME);
        COLOR = Colors.LIGHTGREY;
        ACTION = "";

        fillCommandGrid();
    }

    private void fillCommandGrid() {
        gridPane_f1.getChildren().clear();
        for (int i = 0; i < F1; i++) {
            Pane pane = createCommandPane(i);
            gridPane_f1.addColumn(i, pane);
            f1_label.setText("F1");
        }

        gridPane_f2.getChildren().clear();
        for (int i = 0; i < F2; i++) {
            Pane pane = createCommandPane(i);
            gridPane_f2.addColumn(i, pane);
            f2_label.setText("F2");
        }

        gridPane_f3.getChildren().clear();
        for (int i = 0; i < F3; i++) {
            Pane pane = createCommandPane(i);
            gridPane_f3.addColumn(i, pane);
            f3_label.setText("F3");
        }

        gridPane_f4.getChildren().clear();
        for (int i = 0; i < F4; i++) {
            Pane pane = createCommandPane(i);
            gridPane_f4.addColumn(i, pane);
            f4_label.setText("F4");
        }
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

                    for (int i = 0; i < CELL; i++) {
                        for (int j = 0; j < CELL; j++) {

                            Pane pane = new Pane();
                            pane.setStyle(color_cell[i][j]);

                            if (item_cell[i][j].equals("null")){
                                item_cell[i][j] = "";
                            }

                            if (CELL == 10){
                                pane.setMinSize(60.0,60.0);
                            } else if (CELL == 15){
                                pane.setMinSize(40.0,40.0);
                            }

                            switch (item_cell[i][j]) {
                                case "up" : {
                                    pane.getChildren().add(setArrowPosition(270));
                                    break;
                                }
                                case "down" : {
                                    pane.getChildren().add(setArrowPosition(90));
                                    break;
                                }
                                case "left" : {
                                    pane.getChildren().add(setArrowPosition(180));
                                    break;
                                }
                                case "right" : {
                                    pane.getChildren().add(setArrowPosition(0));
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

    private static ImageView setArrowPosition(int rotate) {
        ImageView imageView = new ImageView("file:src/contents/images/arrow.png");
        imageView.setRotate(rotate);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);
        return imageView;
    }

    private Pane createCommandPane(int number) {

        Label label = new Label(String.valueOf(number));
        label.setTranslateX(20);
        label.setTranslateY(10);

        Pane pane = new Pane();
        pane.setPrefSize(48.0,48.0);
        pane.setStyle(Colors.WHITESMOKE );
        pane.getChildren().add(label);
        pane.setBorder((new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));

        pane.setOnMouseClicked(this::putCommand);

        return pane;
    }

    @FXML private void onPlay() {
        System.out.println("play");
    }

    @FXML private void onNext() {
        System.out.println("next");
    }

    @FXML private void onStop() {
        System.out.println("stop");
    }

    @FXML private void onChooseUp(MouseEvent event){
        ImageView iv = (ImageView) event.getSource();
        COLOR = iv.getParent().getStyle();
        ACTION = "up";
    }

    @FXML private void onChooseLeft(MouseEvent event){
        ImageView iv = (ImageView) event.getSource();
        COLOR = iv.getParent().getStyle();
        ACTION = "left";
    }

    @FXML private void onChooseRight(MouseEvent event){
        ImageView iv = (ImageView) event.getSource();
        COLOR = iv.getParent().getStyle();
        ACTION = "right";
    }

    @FXML private void onFChoose(MouseEvent event) {
        Label lb = (Label) event.getSource();
        COLOR = lb.getParent().getStyle();
        ACTION = lb.getText();
    }

    @FXML private void onVoidChoose(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        COLOR = pane.getStyle();
        ACTION = "";
    }

    private void putCommand(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.getChildren().clear();
        pane.setStyle(COLOR);

        Double pane_width = pane.getWidth();
        Double pane_height = pane.getHeight();

        switch (ACTION) {
            case "up" : {
                pane.getChildren().add(0, buildCommandImageView("file:src/contents/images/arrow-up.png", pane_width, pane_height));
                break;
            }
            case "left" : {
                pane.getChildren().add(0, buildCommandImageView("file:src/contents/images/arrow-back.png", pane_width, pane_height));
                break;
            }
            case "right" : {
                pane.getChildren().add(0, buildCommandImageView("file:src/contents/images/arrow-next.png", pane_width, pane_height));
                break;
            }
            case "F1" : {
                pane.getChildren().add(buildCommandLabel());
                break;
            }
            case "F2" : {
                pane.getChildren().add(buildCommandLabel());
                break;
            }
            case "F3" : {
                pane.getChildren().add(buildCommandLabel());
                break;
            }
            case "F4" : {
                pane.getChildren().add(buildCommandLabel());
                break;
            }
            default:
                break;
        }
    }

    private Label buildCommandLabel() {
        Label label = new Label(ACTION);
        label.setTranslateX(18);
        label.setTranslateY(10);
        label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
        return label;
    }

    private ImageView buildCommandImageView(String url, Double parent_width, Double parent_height) {
        ImageView imageView = new ImageView(url);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.relocate(parent_width/2-15,parent_height/2-15);
        return imageView;
    }

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
                .add("name", level_name).add("cell", cell_max).add("difficulty", level_difficulty)
                .add("f1", f1).add("f2", f2).add("f3", f3).add("f4", f4).add("grid_list", String.valueOf(grid_list)).build();
    }


}
