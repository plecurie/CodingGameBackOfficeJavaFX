package controllers.games.toolbox.sombrero;

import controllers.DisplayerController;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import models.Game;
import services.dao.DAOLevel;
import settings.Colors;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SombreroTestController implements Initializable {

    @FXML private Label level_label;
    @FXML private Label f1_label;
    @FXML private Label f2_label;
    @FXML private Label f3_label;
    @FXML private Label f4_label;
    @FXML private GridPane f1_gridpane;
    @FXML private GridPane f2_gridpane;
    @FXML private GridPane f3_gridpane;
    @FXML private GridPane f4_gridpane;

    private Tooltip tooltip = new Tooltip("Recursion soon available.");
    private PathTransition animation = new PathTransition();

    private String ACTION;
    private String COLOR;
    private SombreroItem PLAYER;
    private GridPane GRID;
    private Sombrero sombrero;
    private int column_start, row_start;
    private double start_x, start_y, start_rotate;
    private DisplayerController displayController;
    private Path road;

    @Override public void initialize(URL location, ResourceBundle resources) {

        sombrero = Sombrero.getSelectedSombrero();

        level_label.setText(Game.GAME_NAME + ": " + sombrero.getName());
        COLOR = Colors.LIGHTGREY;
        ACTION = "move";

        fillCommandGrid();

        PLAYER = SombreroItem.getArrow();
        GRID = sombrero.getGridpane();

        start_x = PLAYER.getX();
        start_y = PLAYER.getY();
        start_rotate = PLAYER.getRotate();

        column_start = PLAYER.getColumnId();
        row_start = PLAYER.getRowId();

    }

    private void initPlayer() {
        PLAYER.setRotate(start_rotate);

        PLAYER.setX(start_x);
        PLAYER.setY(start_y);

        PLAYER.setRowId(row_start);
        PLAYER.setColumnId(column_start);
    }

    @FXML private void onChooseUp(MouseEvent event){
        ImageView iv = (ImageView) event.getSource();
        COLOR = iv.getParent().getStyle();
        ACTION = "move";
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
        ACTION = "void";
    }

    @FXML private void onPlay() {
        if (animation.getStatus().toString().equals("PAUSED")){
            animation.play();
        } else  {
            initPlayer();
            animation = playFunction(f1_gridpane);
            animation.play();
        }
    }

    @FXML private void onNext() {
        if (!animation.getStatus().toString().equals("PAUSED"))
            animation.pause();
        else
            animation.play();
    }

    @FXML private void onStop() {
        animation.stop();
        animation.jumpTo(Duration.ZERO);

        initPlayer();
    }

    @FXML private void onCancel() throws Exception {
        displayController.displayToolbox();
    }

    @FXML private void onSubmit() {
        fireGameWon();
    }

    private PathTransition playFunction(GridPane gridPane) {

        List<String> commands = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            Label label = (Label) pane.lookup("Label");
            String movement = label.getText();
            String condition = pane.getStyle();

            String command = movement + ":" + condition;

            commands.add(command);
        }

        road = createPath(commands);
        PathTransition animation = new PathTransition();
        animation.setNode(PLAYER);
        animation.setPath(road);
        animation.setDuration(new Duration(200*commands.size()));
        animation.setInterpolator(Interpolator.LINEAR);

        return animation;
    }

    private Path createPath(List<String> commands) {
        Path path = new Path();

        for (String command : commands) {
            String[] object = command.split(":");
            String movement = object[0];
            String style = object[1];
            String color = object[2];

            String cell_color = style + ":" + color;

            switch (movement) {
                case "move": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER)) {
                        path = move(path);
                        if (isBlack())
                            fireGameOver();

                        if (!isLastSombreroItem())
                            removeIfIsItem();
                        else if (isLastSombreroItem())
                            fireGameWon();
                    }
                    break;
                }
                case "left": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        turnLeft();
                    break;
                }
                case "right": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        turnRight();
                    break;
                }
                case "F1": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        playFunction(f1_gridpane);
                    break;
                }
                case "F2": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        playFunction(f2_gridpane);
                    break;
                }
                case "F3": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        playFunction(f3_gridpane);
                    break;
                }
                case "F4": {
                    if(isColorCondition(cell_color) || cell_color.equals(Colors.SILVER))
                        playFunction(f4_gridpane);
                    break;
                }
                default:
                    break;
            }
        }
        return path;
    }

    private Path move(Path path) {

        Double rotate = PLAYER.getRotate();
        int orientation = rotate.intValue();

        double pos_x = PLAYER.getX() + Sombrero.getInnerItemDefaultWidth()/2;
        double pos_y = PLAYER.getY() + Sombrero.getInnerItemDefaultHeight()/2;

        switch (orientation) {
            //haut
            case 270 :{
                path.getElements().add(new MoveTo(pos_x, pos_y));
                path.getElements().add(new LineTo(pos_x, pos_y - 2*Sombrero.getInnerItemDefaultWidth()));
                PLAYER.setY(PLAYER.getY() - 2*Sombrero.getInnerItemDefaultHeight());
                PLAYER.setRowId(PLAYER.getRowId() - 1);

                break;
            }
            //bas
            case 90 :{
                path.getElements().add(new MoveTo(pos_x, pos_y));
                path.getElements().add(new LineTo(pos_x, pos_y + 2*Sombrero.getInnerItemDefaultWidth()));
                PLAYER.setY(PLAYER.getY() + 2*Sombrero.getInnerItemDefaultHeight());
                PLAYER.setRowId(PLAYER.getRowId() + 1);

                break;
            }
            //gauche
            case 180 :{
                path.getElements().add(new MoveTo(pos_x, pos_y));
                path.getElements().add(new LineTo(pos_x - 2*Sombrero.getInnerItemDefaultWidth(), pos_y));
                PLAYER.setX(PLAYER.getX() - 2*Sombrero.getInnerItemDefaultHeight());
                PLAYER.setColumnId(PLAYER.getColumnId() - 1);

                break;
            }
            //droite
            case 0 :{
                path.getElements().add(new MoveTo(pos_x, pos_y));
                path.getElements().add(new LineTo(pos_x + 2*Sombrero.getInnerItemDefaultHeight(), pos_y));
                PLAYER.setX(PLAYER.getX() + 2*Sombrero.getInnerItemDefaultWidth());
                PLAYER.setColumnId(PLAYER.getColumnId() + 1);

                break;
            }
            default:
                break;
        }
        return path;
    }

    private void turnLeft() {
        if(PLAYER.getRotate() > 0)
            PLAYER.setRotate(PLAYER.getRotate() - 90);
        else
            PLAYER.setRotate(270);
    }

    private void turnRight() {
        if (PLAYER.getRotate() < 270)
            PLAYER.setRotate(PLAYER.getRotate() + 90);
        else
            PLAYER.setRotate(0);
    }

    private boolean isColorCondition(String color) {
        for (Node node : GRID.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            if (pane != null) {
                if (GridPane.getColumnIndex(pane) == PLAYER.getColumnId()
                        && GridPane.getRowIndex(pane) == PLAYER.getRowId()
                        && pane.getStyle().equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBlack() {
        for (Node node : GRID.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            if (pane != null) {
                if (GridPane.getColumnIndex(pane) == PLAYER.getColumnId()
                        && GridPane.getRowIndex(pane) == PLAYER.getRowId()){
                        if(pane.getStyle().equals(Colors.BLACK))
                            return true;
                }
            }
        }
        return false;
    }

    private void removeIfIsItem() {
        for (Node node : GRID.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            if (pane != null) {
                if (GridPane.getColumnIndex(pane) == PLAYER.getColumnId()
                        && GridPane.getRowIndex(pane) == PLAYER.getRowId()){
                    if (pane.getChildren().size() != 0){
                        SombreroItem item = (SombreroItem) pane.getChildren().get(0).lookup("SombreroItem");
                        if(item != null){
                            item.setVisible(false);
                        }
                    }
                }
            }
        }
    }

    private boolean isLastSombreroItem() {
        for (Node node : GRID.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            if (pane != null) {
                if (pane.getChildren().size() != 0){
                    return false;
                }
            }
        }
        return true;
    }

    private void fireGameOver() {
        animation.stop();
        displayController.displayInformation("You lose...");
    }

    private void fireGameWon() {
        if (displayController.displayConfirmation("Would you like to submit this level ?")) {
            DAOLevel daoLevel = new DAOLevel();
            if (daoLevel.createLevel(Game.GAME_ID, Sombrero.name, Sombrero.difficulty)){
                displayController.displayInformation("Level created !");
            }
        }
    }

    private void fillCommandGrid() {

        f1_gridpane.getChildren().clear();

        for (int i = 0; i < sombrero.getF1(); i++) {
            Pane pane = createCommandPane(i);
            f1_gridpane.addColumn(i, pane);
            f1_label.setText("F1");
        }

        f2_gridpane.getChildren().clear();
        for (int i = 0; i < sombrero.getF2(); i++) {
            Pane pane = createCommandPane(i);
            f2_gridpane.addColumn(i, pane);
            f2_label.setText("F2");
        }

        f3_gridpane.getChildren().clear();
        for (int i = 0; i < sombrero.getF3(); i++) {
            Pane pane = createCommandPane(i);
            f3_gridpane.addColumn(i, pane);
            f3_label.setText("F3");
        }

        f4_gridpane.getChildren().clear();
        for (int i = 0; i < sombrero.getF4(); i++) {
            Pane pane = createCommandPane(i);
            f4_gridpane.addColumn(i, pane);
            f4_label.setText("F4");
        }
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

        pane.setOnMouseClicked(this::dropCommand);

        return pane;
    }

    private void dropCommand(MouseEvent event) {
        Pane pane = (Pane) event.getSource();
        pane.getChildren().clear();
        pane.setStyle(COLOR);

        Double pane_w = pane.getWidth();
        Double pane_h = pane.getHeight();

        Label label = new Label();
        label.setVisible(false);

        switch (ACTION) {
            case "move" : {
                label.setText(ACTION);
                pane.getChildren().addAll(buildCommandImageView("file:src/contents/images/arrow-up.png", pane_w, pane_h), label);
                break;
            }
            case "left" : {
                label.setText(ACTION);
                pane.getChildren().addAll(buildCommandImageView("file:src/contents/images/arrow-back.png", pane_w, pane_h), label);
                break;
            }
            case "right" : {
                label.setText(ACTION);
                pane.getChildren().addAll(buildCommandImageView("file:src/contents/images/arrow-next.png", pane_w, pane_h), label);
                break;
            }
            case "void" : {
                label.setText(ACTION);
                pane.getChildren().add(label);
            }
            default: {
                pane.getChildren().add(buildCommandLabel());
                break;
            }
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

    @FXML void displayTooltip(MouseEvent event){

        Pane pane = (Pane) event.getSource();
        Tooltip.install(pane, tooltip);

    }

    public void linkDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
    }
}
