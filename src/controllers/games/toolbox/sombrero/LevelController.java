package controllers.games.toolbox.sombrero;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    @FXML private GridPane gridPane;
    private DisplayerController displayController;

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

    @FXML private void onFifteenToFifteen() {
        boolean empty = ensureNotFilled();
        if (empty) {
            changeGrid(15);
        } else {
            boolean confirmation = requestConfirmation();
            if(confirmation) {
                changeGrid(15);
            }
        }
    }

    @FXML private void onTenToTen() {
        boolean empty = ensureNotFilled();
        if (empty) {
            changeGrid(10);
        } else {
            boolean confirmation = requestConfirmation();
            if(confirmation) {
                changeGrid(10);
            }
        }
    }

    private boolean ensureNotFilled() {
        for (Node node : gridPane.getChildren()) {
            if (!node.getStyle().equals(BLACK)) {
                return false;
            }
        }
        return true;
    }

    private boolean requestConfirmation() {
        String confirm_message = "Would you like to delete modifications ?";
        boolean confirmed = displayController.displayConfirmation(confirm_message);

        if (confirmed) return true;
        else return false;
    }

    private void changeGrid(int gridCell) {
        setCellCount(gridCell);
        try {
            displayController.displayToolbox(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCellCount() { return cellCount; }

    public static void setCellCount(int cellCount) { LevelController.cellCount = cellCount; }

    private static void setColor(String color) {
        LevelController.color = color;
    }

    public void setDisplayer(DisplayerController displayerController) { this.displayController = displayerController; }

}
