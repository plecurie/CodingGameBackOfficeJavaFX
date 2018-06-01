package controllers.games.levels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectedLevelController implements Initializable {

    @FXML private GridPane gridPane;

    private static String color;
    private static int cellCount;
    private static final String BLACK = "-fx-background-color:BLACK;";
    private static final String RED = "-fx-background-color:RED;";
    private static final String GREEN = "-fx-background-color:GREEN;";
    private static final String BLUE = "-fx-background-color:BLUE;";



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onBlueClick(new ActionEvent());

        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
            node.setOnMouseClicked((MouseEvent t) -> {
                node.setStyle(color);
            });
        }
    }

    private static void setColor(String color) {
        SelectedLevelController.color = color;
    }

    private static void setCellCount(int grid) {
        SelectedLevelController.cellCount = grid;
    }

    @FXML private void onTenToTen() {
        //Avertir avant de changer si toutes les cases ne sont pas noires
        setCellCount(10);
        System.out.println(cellCount);
    }
    @FXML private void onFifteenToFifteen() {
        //Avertir avant de changer si toutes les cases ne sont pas noires
        setCellCount(15);
        System.out.println(cellCount);
    }

    @FXML private void onVoidClick(ActionEvent event) {
        setColor(BLACK);
    }
    @FXML private void onRedClick(ActionEvent event) {
        setColor(RED);
    }
    @FXML private void onGreenClick(ActionEvent event) {
        setColor(GREEN);
    }
    @FXML private void onBlueClick(ActionEvent event) {
        setColor(BLUE);
    }
    @FXML private void onClearClick(ActionEvent event) {
        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
        }
    }
}
