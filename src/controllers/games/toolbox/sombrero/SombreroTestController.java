package controllers.games.toolbox.sombrero;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

    private PathTransition animation = new PathTransition();

    private static String ACTION;
    private static String COLOR;
    private ImageView PLAYER;
    private double start_x;
    private double start_y;
    private double start_rotate;

    @Override public void initialize(URL location, ResourceBundle resources) {
        level_label.setText(Game.GAME_NAME + ": " + SombreroFactory.getName());
        COLOR = Colors.LIGHTGREY;
        ACTION = "";
        fillCommandGrid();

        PLAYER = SombreroFactory.getArrow();
        start_x = PLAYER.getLayoutX();
        start_y = PLAYER.getLayoutY();
        start_rotate = PLAYER.getRotate();
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

    @FXML private void onPlay(MouseEvent event) {
        animation = playFunction(f1_gridpane);
        animation.play();
    }

    @FXML private void onNext() {
        animation.pause();
    }

    @FXML private void onStop() {
        animation.stop();
        animation.jumpTo(Duration.ZERO);
        PLAYER.setRotate(start_rotate);
        PLAYER.setX(start_x - Sombrero.getINNER_ITEM_DEFAULT_WIDTH()/2);
        PLAYER.setY(start_y - Sombrero.getINNER_ITEM_DEFAULT_HEIGHT()/2);
    }

    private PathTransition playFunction(GridPane gridPane) {

        List<String> commands = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            Pane pane = (Pane) node.lookup("Pane");
            Label label = (Label) pane.lookup("Label");
            String command = label.getText();

            commands.add(command);
        }

        Path road = createPath(commands);

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
            switch (command) {
                case "move": {
                    path = move(path);
                    break;
                }
                case "left": {
                    turnLeft();
                    break;
                }
                case "right": {
                    turnRight();
                    break;
                }
                case "F1": {
                    playFunction(f1_gridpane);
                    break;
                }
                case "F2": {
                    playFunction(f2_gridpane);
                    break;
                }
                case "F3": {
                    playFunction(f3_gridpane);
                    break;
                }
                case "F4": {
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

        switch (orientation) {
            case 270 :{
                path.getElements().add(new MoveTo(PLAYER.getX(), PLAYER.getY()));
                path.getElements().add(new LineTo(PLAYER.getX(), PLAYER.getY() - 2*Sombrero.getINNER_ITEM_DEFAULT_HEIGHT()));
                PLAYER.setY(PLAYER.getY() - 2*Sombrero.getINNER_ITEM_DEFAULT_HEIGHT());
                break;
            }
            case 90 :{
                path.getElements().add(new MoveTo(PLAYER.getX(), PLAYER.getY()));
                path.getElements().add(new LineTo(PLAYER.getX(), PLAYER.getY() + 2*Sombrero.getINNER_ITEM_DEFAULT_HEIGHT()));
                PLAYER.setY(PLAYER.getY() + 2*Sombrero.getINNER_ITEM_DEFAULT_HEIGHT());
                break;
            }
            case 180 :{
                path.getElements().add(new MoveTo(PLAYER.getX(), PLAYER.getY()));
                path.getElements().add(new LineTo(PLAYER.getX() - 2*Sombrero.getINNER_ITEM_DEFAULT_WIDTH(), PLAYER.getY()));
                PLAYER.setX(PLAYER.getX() - 2*Sombrero.getINNER_ITEM_DEFAULT_WIDTH());
                break;
            }
            case 0 :{
                path.getElements().add(new MoveTo(PLAYER.getX(), PLAYER.getY()));
                path.getElements().add(new LineTo(PLAYER.getX() + 2*Sombrero.getINNER_ITEM_DEFAULT_WIDTH(), PLAYER.getY()));
                PLAYER.setX(PLAYER.getX() + 2*Sombrero.getINNER_ITEM_DEFAULT_WIDTH());
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

    private void fillCommandGrid() {

        f1_gridpane.getChildren().clear();

        for (int i = 0; i < SombreroFactory.getF1(); i++) {
            Pane pane = createCommandPane(i);
            f1_gridpane.addColumn(i, pane);
            f1_label.setText("F1");
        }

        f2_gridpane.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF2(); i++) {
            Pane pane = createCommandPane(i);
            f2_gridpane.addColumn(i, pane);
            f2_label.setText("F2");
        }

        f3_gridpane.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF3(); i++) {
            Pane pane = createCommandPane(i);
            f3_gridpane.addColumn(i, pane);
            f3_label.setText("F3");
        }

        f4_gridpane.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF4(); i++) {
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
}
