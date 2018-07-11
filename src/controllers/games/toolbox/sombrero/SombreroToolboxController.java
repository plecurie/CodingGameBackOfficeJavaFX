package controllers.games.toolbox.sombrero;

import controllers.DisplayerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static settings.Colors.*;

public class SombreroToolboxController implements Initializable {

    @FXML private GridPane gridPane;
    @FXML private ChoiceBox<Integer> functions_cb1;
    @FXML private ChoiceBox<Integer> functions_cb2;
    @FXML private ChoiceBox<Integer> functions_cb3;
    @FXML private ChoiceBox<Integer> functions_cb4;
    @FXML private ChoiceBox<Integer> difficuly_cb;
    private DisplayerController displayController;

    private static String color;
    private static int cellCount;
    private static String action = "color";
    //private final Label position = new Label();
    private final ImageView position = new ImageView();

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
                    case "up" : {
                        if (selectedPane.getChildren().isEmpty()) {
                            //position.setText("UP");
                            position.setImage(new Image("file:src/contents/images/arrow.png"));
                            position.setRotate(270);
                            position.setFitHeight(60);
                            position.setFitWidth(60);
                            selectedPane.getChildren().add(position);
                        }
                        break;
                    }
                    case "down" : {
                        if (selectedPane.getChildren().isEmpty()) {
                            //position.setText("DOWN");
                            position.setImage(new Image("file:src/contents/images/arrow.png"));
                            position.setRotate(90);
                            position.setFitHeight(60);
                            position.setFitWidth(60);
                            selectedPane.getChildren().add(position);
                        }
                        break;
                    }
                    case "left" : {
                        if (selectedPane.getChildren().isEmpty()){
                            //position.setText("LEFT");
                            position.setImage(new Image("file:src/contents/images/arrow.png"));
                            position.setRotate(180);
                            position.setFitHeight(60);
                            position.setFitWidth(60);
                            selectedPane.getChildren().add(position);
                        }
                        break;
                    }
                    case "right" : {
                        if (selectedPane.getChildren().isEmpty()){
                            //position.setText("RIGHT");
                            position.setImage(new Image("file:src/contents/images/arrow.png"));
                            position.setRotate(0);
                            position.setFitHeight(60);
                            position.setFitWidth(60);
                            selectedPane.getChildren().add(position);
                        }
                        break;
                    }
                    case "item" : {
                        if (selectedPane.getChildren().isEmpty()){
                            ImageView item_iv = new ImageView("file:src/contents/images/stargold.png");
                            item_iv.setFitHeight(60);
                            item_iv.setFitWidth(60);
                            selectedPane.getChildren().add(item_iv);
                        }

                        break;
                    }
                    default:
                        break;
                }
            });
        }

        List<Integer> list_functions = new ArrayList<Integer>() ;
        for (int i = 0; i < 10; i++) {
            list_functions.add(i+1);
        }
        functions_cb1.setItems(FXCollections.observableArrayList(list_functions));
        functions_cb2.setItems(FXCollections.observableArrayList(list_functions));
        functions_cb3.setItems(FXCollections.observableArrayList(list_functions));
        functions_cb4.setItems(FXCollections.observableArrayList(list_functions));
        functions_cb1.setValue(list_functions.get(0));
        functions_cb2.setValue(list_functions.get(0));
        functions_cb3.setValue(list_functions.get(0));
        functions_cb4.setValue(list_functions.get(0));


        List<Integer> list_difficulty = new ArrayList<Integer>() ;
        for (int i = 0; i < 10; i++) {
            list_difficulty.add(i+1);
        }
        difficuly_cb.setItems(FXCollections.observableArrayList(list_difficulty));
        difficuly_cb.setValue(list_difficulty.get(0));

    }

    public void linkDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
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
    @FXML private void onArrowLeftClick(MouseEvent event) {
        action = "left";
    }
    @FXML private void onArrowRightClick(MouseEvent event) {
        action = "right";
    }
    @FXML private void onArrowUpClick(MouseEvent event) {
        action = "up";
    }
    @FXML private void onArrowDownClick(MouseEvent event) {
        action = "down";
    }
    @FXML private void onItemClick() { action = "item"; }

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
/*
        try {
            displayController.displaySombreroTest(gridPane, functions_cb.getValue(), difficuly_cb.getValue(), cellCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    @FXML private void onQuit() {
        if (displayController.displayConfirmation("Do you really want to quit ?")) {
            quit();
        }
    }

    private void quit() {
        try {
            displayController.displaySombreroLevels();
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
            displayController.displayToolbox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCellCount() { return cellCount; }
    public static void setCellCount(int cellCount) { SombreroToolboxController.cellCount = cellCount; }
    private static void setColor(String color) { SombreroToolboxController.color = color; }

}
