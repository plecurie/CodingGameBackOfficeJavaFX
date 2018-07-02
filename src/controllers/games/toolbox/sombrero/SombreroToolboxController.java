package controllers.games.toolbox.sombrero;

import controllers.DisplayerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Game;
import settings.Colors;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static settings.Colors.*;

public class SombreroToolboxController implements Initializable {

    @FXML private GridPane gridPane;
    @FXML private ChoiceBox<String> choiceBox;
    private DisplayerController displayController;
    private Game selected_game;

    private static String color;
    private static int cellCount;
    private static String action = "color";
    private final Label arrow = new Label("PEPITO");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onBlueClick(new ActionEvent());

        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
            node.setOnMouseClicked((MouseEvent t) -> {
                //Pane selectedPane = (Pane) t.getSource();
                Pane selectedPane = (Pane) node.lookup("Pane");
                switch (action) {
                    case "color" : {
                        node.setStyle(color);
                        break;
                    }
                    case "arrow" : {
                        if (selectedPane.getChildren().isEmpty())
                            selectedPane.getChildren().add(arrow);
                        break;
                    }
                    case "biscuit" : {
                        if (selectedPane.getChildren().isEmpty())
                            selectedPane.getChildren().add(new Label("MINI-ROLL"));
                        break;
                    }
                    default:
                        break;
                }
            });
        }

        List<String> list_functions = new ArrayList<String>() ;
        for (int i = 0; i < 7; i++) {
            String function = "F" + (i+1);
            list_functions.add(function);
        }
        choiceBox.setItems(FXCollections.observableArrayList(list_functions));
        choiceBox.setValue(list_functions.get(0));
    }

    public void linkDisplayerGame(DisplayerController displayerController, Game game) {
        this.displayController = displayerController;
        this.selected_game = game;
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

    @FXML private void onVoidClick(ActionEvent event) {
        action = "color";
        setColor(BLACK);
    }
    @FXML private void onRedClick(ActionEvent event) {
        action = "color";
        setColor(RED);
    }
    @FXML private void onGreenClick(ActionEvent event) {
        action = "color";
        setColor(GREEN);
    }
    @FXML private void onBlueClick(ActionEvent event) {
        action = "color";
        setColor(BLUE);
    }
    @FXML private void onArrowLeftClick(ActionEvent event) {
        action = "arrow";
    }
    @FXML private void onArrowRightClick(ActionEvent event) {
        action = "arrow";
    }
    @FXML private void onArrowUpClick(ActionEvent event) {
        action = "arrow";
    }
    @FXML private void onArrowDownClick(ActionEvent event) {
        action = "arrow";
    }
    @FXML private void onBiscuitClick() {
        action = "biscuit";
    }

    @FXML private void onClearClick(ActionEvent event) {
        displayController.displayConfirmation("Are you sure you want to delete all ? \nYou will not be able to return modifications...");
        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
            Pane pane = (Pane) node.lookup("Pane");
            try {
                pane.getChildren().clear();
            } catch (Exception ignored) {}
        }
    }

    @FXML private void onResolveClick() {

        try {
            displayController.displaySombreroTest(gridPane, choiceBox.getValue(), cellCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML private void onQuit() {
        if (displayController.displayConfirmation("Do you really want to quit ?")) {
            quit();
        }
    }

    private void quit() {
        try {
            displayController.displaySombreroLevels(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
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
        return displayController.displayConfirmation(confirm_message);
    }

    private void changeGrid(int gridCell) {
        setCellCount(gridCell);
        try {
            displayController.displayToolbox(selected_game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCellCount() { return cellCount; }
    public static void setCellCount(int cellCount) { SombreroToolboxController.cellCount = cellCount; }
    private static void setColor(String color) { SombreroToolboxController.color = color; }

}
