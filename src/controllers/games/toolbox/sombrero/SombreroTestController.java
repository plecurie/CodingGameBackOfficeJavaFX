package controllers.games.toolbox.sombrero;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import models.Game;
import settings.Colors;

import java.net.URL;
import java.util.ResourceBundle;

public class SombreroTestController implements Initializable {

    @FXML private Label level_label;
    @FXML private Label f1_label;
    @FXML private Label f2_label;
    @FXML private Label f3_label;
    @FXML private Label f4_label;
    @FXML private GridPane gridPane_f1;
    @FXML private GridPane gridPane_f2;
    @FXML private GridPane gridPane_f3;
    @FXML private GridPane gridPane_f4;

    private static String ACTION;
    private static String COLOR;
    private static ImageView PLAYER;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level_label.setText(Game.GAME_NAME + ": " + SombreroFactory.getName());
        COLOR = Colors.LIGHTGREY;
        ACTION = "";
        fillCommandGrid();

        PLAYER = SombreroFactory.getArrow();
    }

    @FXML private void onPlay(MouseEvent event) {
        final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), PLAYER);

        moveForward(translateTransition);

    }

    @FXML private void onNext() { System.out.println("next"); }
    @FXML private void onStop() { System.out.println("stop"); }
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

    private void moveForward(TranslateTransition translateTransition) {
        translateTransition.setToY(PLAYER.getY()-30);
        translateTransition.play();

        PLAYER.setY(PLAYER.getY() -30);
    }

    private void moveTurnLeft() {

    }

    private void moveTurnRight() {

    }

    private void fillCommandGrid() {

        gridPane_f1.getChildren().clear();

        for (int i = 0; i < SombreroFactory.getF1(); i++) {
            Pane pane = createCommandPane(i);
            gridPane_f1.addColumn(i, pane);
            f1_label.setText("F1");
        }

        gridPane_f2.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF2(); i++) {
            Pane pane = createCommandPane(i);
            gridPane_f2.addColumn(i, pane);
            f2_label.setText("F2");
        }

        gridPane_f3.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF3(); i++) {
            Pane pane = createCommandPane(i);
            gridPane_f3.addColumn(i, pane);
            f3_label.setText("F3");
        }

        gridPane_f4.getChildren().clear();
        for (int i = 0; i < SombreroFactory.getF4(); i++) {
            Pane pane = createCommandPane(i);
            gridPane_f4.addColumn(i, pane);
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
}
