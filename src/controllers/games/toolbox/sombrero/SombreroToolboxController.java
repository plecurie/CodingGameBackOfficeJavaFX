package controllers.games.toolbox.sombrero;

import controllers.DisplayerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static settings.Colors.*;

public class SombreroToolboxController implements Initializable {

    private static final double ITEM_DEFAULT_HEIGHT = 30;
    private static final double ITEM_DEFAULT_WIDTH = 30;
    @FXML private GridPane gridPane;
    @FXML private TextField level_name;
    @FXML private ChoiceBox<Integer> f1;
    @FXML private ChoiceBox<Integer> f2;
    @FXML private ChoiceBox<Integer> f3;
    @FXML private ChoiceBox<Integer> f4;
    @FXML private ChoiceBox<Integer> difficuly_cb;
    private DisplayerController displayController;

    private static String color;
    private static int cellCount;
    private static String action = "color";
    private ImageView position_iv = new ImageView();
    private Label description_iv = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        description_iv.setVisible(true);
        onBlueClick(new ActionEvent());

        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
            node.setOnMouseClicked((MouseEvent t) -> {
                Pane selectedPane = (Pane) node.lookup("Pane");
                switch (action) {
                    case "color" : {
                        node.setStyle(color);
                        break;
                    }
                    case "up" : {
                        if (selectedPane.getChildren().isEmpty()) {
                            description_iv.setText("up");
                            setArrowPosition(270, selectedPane);
                            selectedPane.getChildren().add(0, position_iv);
                            selectedPane.getChildren().add(1, description_iv);
                        }
                        break;
                    }
                    case "down" : {
                        if (selectedPane.getChildren().isEmpty()) {
                            description_iv.setText("down");
                            setArrowPosition(90, selectedPane);
                            selectedPane.getChildren().add(0, position_iv);
                            selectedPane.getChildren().add(1, description_iv);
                        }
                        break;
                    }
                    case "left" : {
                        if (selectedPane.getChildren().isEmpty()){
                            description_iv.setText("left");
                            setArrowPosition(180, selectedPane);
                            selectedPane.getChildren().add(0, position_iv);
                            selectedPane.getChildren().add(1, description_iv);
                        }
                        break;
                    }
                    case "right" : {
                        if (selectedPane.getChildren().isEmpty()){
                            description_iv.setText("right");
                            setArrowPosition(0, selectedPane);
                            selectedPane.getChildren().add(0, position_iv);
                            selectedPane.getChildren().add(1, description_iv);
                        }
                        break;
                    }
                    case "item" : {
                        if (selectedPane.getChildren().isEmpty()){
                            ImageView item_iv = new ImageView("file:src/contents/images/stargold.png");
                            Label description_it = new Label("item");
                            description_it.setVisible(true);
                            item_iv.setFitHeight(30);
                            item_iv.setFitWidth(30);
                            item_iv.relocate(selectedPane.getWidth()/2-15,selectedPane.getHeight()/2-15);
                            item_iv.setUserData("star");
                            selectedPane.getChildren().add(0, item_iv);
                            selectedPane.getChildren().add(1, description_it);
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
        f1.setItems(FXCollections.observableArrayList(list_functions));
        f2.setItems(FXCollections.observableArrayList(list_functions));
        f3.setItems(FXCollections.observableArrayList(list_functions));
        f4.setItems(FXCollections.observableArrayList(list_functions));
        f1.setValue(list_functions.get(0));
        f2.setValue(list_functions.get(0));
        f3.setValue(list_functions.get(0));
        f4.setValue(list_functions.get(0));


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

    private void setArrowPosition(int rotate, Pane selected_pane) {
        position_iv.setImage(new Image("file:src/contents/images/arrow.png"));
        position_iv.setRotate(rotate);
        position_iv.setFitHeight(ITEM_DEFAULT_HEIGHT);
        position_iv.setFitWidth(ITEM_DEFAULT_WIDTH);
        position_iv.relocate(selected_pane.getWidth()/2 - ITEM_DEFAULT_HEIGHT/2,selected_pane.getHeight()/2 - ITEM_DEFAULT_WIDTH / 2);
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
        if (level_name.getText().isEmpty()){
            displayController.displayAlert("The level name cannot be empty !");
        }
        else {
            try {
                displayController.displaySombreroTest(gridPane, level_name.getText(), f1.getValue(),f2.getValue(),f3.getValue(),f4.getValue(), difficuly_cb.getValue(), cellCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onQuit() throws Exception {
        if (displayController.displayConfirmation("Do you really want to quit ?")) {
            displayController.displaySombreroLevels();
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
