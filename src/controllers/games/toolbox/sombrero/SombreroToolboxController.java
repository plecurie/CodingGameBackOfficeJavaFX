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

    @FXML private GridPane gridPane;
    @FXML private TextField level_name;
    @FXML private ChoiceBox<Integer> f1;
    @FXML private ChoiceBox<Integer> f2;
    @FXML private ChoiceBox<Integer> f3;
    @FXML private ChoiceBox<Integer> f4;
    @FXML private ChoiceBox<Integer> difficulty_cb;

    private static String color;
    private static int cellCount;
    private static String action = "color";
    private ImageView position_iv = new ImageView();
    private Label description_iv = new Label();
    private boolean player;
    private boolean item;

    private DisplayerController displayController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onBlueClick(new ActionEvent());
        player = false;

        for (Node node : gridPane.getChildren()) {
            node.setStyle(BLACK);
            node.setOnMouseClicked((MouseEvent t) -> fillSombreroCell(node));
        }
        initFunctionsChoicebox();
        initDifficultyChoiceBox();
    }

    private void fillSombreroCell(Node node) {

        Pane selectedPane = (Pane) node.lookup("Pane");
        description_iv.setVisible(false);

        switch (action) {
            case "color" : {
                node.setStyle(color);
                if (color.equals(BLACK)) {
                    Pane pane = (Pane) node.lookup("Pane");
                    pane.getChildren().clear();
                }
                break;
            }
            case "up" : {
                if (selectedPane.getChildren().isEmpty()) {
                    description_iv.setText("up");
                    setArrowPosition(270, selectedPane);
                    selectedPane.getChildren().addAll(position_iv, description_iv);
                }
                break;
            }
            case "down" : {
                if (selectedPane.getChildren().isEmpty()) {
                    description_iv.setText("down");
                    setArrowPosition(90, selectedPane);
                    selectedPane.getChildren().addAll(position_iv, description_iv);
                }
                break;
            }
            case "left" : {
                if (selectedPane.getChildren().isEmpty()){
                    description_iv.setText("left");
                    setArrowPosition(180, selectedPane);
                    selectedPane.getChildren().addAll(position_iv, description_iv);
                }
                break;
            }
            case "right" : {
                if (selectedPane.getChildren().isEmpty()){
                    description_iv.setText("right");
                    setArrowPosition(0, selectedPane);
                    selectedPane.getChildren().addAll(position_iv, description_iv);
                }
                break;
            }
            case "item" : {
                if (selectedPane.getChildren().isEmpty()){
                    ImageView item_iv = new ImageView("file:src/contents/images/stargold.png");
                    Label description_it = new Label("item");
                    description_it.setVisible(false);
                    item_iv.setFitHeight(30);
                    item_iv.setFitWidth(30);
                    item_iv.relocate(selectedPane.getWidth()/2-15,selectedPane.getHeight()/2-15);
                    selectedPane.getChildren().addAll(item_iv, description_it);
                }
                break;
            }
            default:
                break;
        }
    }

    private void initDifficultyChoiceBox() {
        List<Integer> list_difficulty = new ArrayList<>() ;
        for (int i = 0; i < 10; i++) list_difficulty.add(i+1);
        difficulty_cb.setItems(FXCollections.observableArrayList(list_difficulty));
        difficulty_cb.setValue(list_difficulty.get(0));
    }

    private void initFunctionsChoicebox() {

        final List<Integer> list_functions = new ArrayList<>() ;
        for (int i = 0; i < 11; i++) list_functions.add(i);

        f1 = setItems(f1, list_functions);
        f2 = setItems(f2, list_functions);
        f3 = setItems(f3, list_functions);
        f4 = setItems(f4, list_functions);

        f1.setOnAction(event -> {
            if (f1.getValue() != 0) f2.setDisable(false);
            else {
                disableFunctions(f2, list_functions);
                disableFunctions(f3, list_functions);
                disableFunctions(f4, list_functions);
            }
        });

        f2.setOnAction(event -> {
            if (f2.getValue() != 0) f3.setDisable(false);
            else {
                disableFunctions(f3, list_functions);
                disableFunctions(f4, list_functions);
            }
        });

        f3.setOnAction(event -> {
            if (f3.getValue() != 0) f4.setDisable(false);
            else disableFunctions(f4, list_functions);
        });
    }

    private ChoiceBox<Integer> setItems(ChoiceBox<Integer> function, List<Integer> list) {
        function.setItems(FXCollections.observableArrayList(list));
        function.setValue(list.get(0));
        return function;
    }

    private void disableFunctions(ChoiceBox<Integer> function, List<Integer> list){
        function.setDisable(true);
        function.setValue(list.get(0));
    }

    private void setArrowPosition(int rotate, Pane selected_pane) {
        position_iv.setImage(new Image("file:src/contents/images/arrow.png"));
        position_iv.setRotate(rotate);
        position_iv.setFitHeight(Sombrero.getITEM_DEFAULT_HEIGHT());
        position_iv.setFitWidth(Sombrero.getITEM_DEFAULT_WIDTH());
        position_iv.relocate(selected_pane.getWidth()/2 - Sombrero.getITEM_DEFAULT_HEIGHT()/2,selected_pane.getHeight()/2 - Sombrero.getITEM_DEFAULT_WIDTH()/ 2);
    }

    @FXML private void onFifteenToFifteen() {
        if (ensureNotFilled()) changeGrid(15);
        else if(requestConfirmation()) changeGrid(15);
    }

    @FXML private void onTenToTen() {
        if (ensureNotFilled()) changeGrid(10);
        else if(requestConfirmation()) changeGrid(10);
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
        player = true;
        action = "left";
    }
    @FXML private void onArrowRightClick(MouseEvent event) {
        player = true;
        action = "right";
    }
    @FXML private void onArrowUpClick(MouseEvent event) {
        player = true;
        action = "up";
    }
    @FXML private void onArrowDownClick(MouseEvent event) {
        player = true;
        action = "down";
    }

    @FXML private void onItemClick() {
        item = true;
        action = "item";
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
        if (ensureNotFilled())
            displayController.displayAlert("No map ! :( ");
        else if (!player)
            displayController.displayAlert("No player on the map !");
        else if (!item)
            displayController.displayAlert("Put at least one star on the map !");
        else if (!ensureEverythingOnMap())
            displayController.displayAlert("Put everything on the map !");
        else if (level_name.getText().isEmpty())
            displayController.displayAlert("The level name cannot be empty !");
        else if (f1.getValue() == 0)
            displayController.displayAlert("No function selected !");
        else {
            try {
                displayController.displaySombreroTest(new Sombrero(gridPane, level_name.getText(), f1.getValue(),f2.getValue(),f3.getValue(),f4.getValue(), difficulty_cb.getValue(), cellCount));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onQuit() throws Exception {
        if (displayController.displayConfirmation("Do you really want to quit ?"))
            displayController.displaySombreroLevels();
    }

    private boolean ensureNotFilled() {
        for (Node node : gridPane.getChildren())
            if (!node.getStyle().equals(BLACK)) return false;
        return true;
    }

    private boolean ensureEverythingOnMap() {
        for (Node node : gridPane.getChildren()){
            if(node.getStyle().equals(BLACK)) {
                Pane pane = (Pane) node.lookup("Pane");
                try {
                    if (pane.getChildren().size() != 0) return false;
                } catch (Exception e) {
                    return true;
                }
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

    private static void setColor(String color) { SombreroToolboxController.color = color; }
    public void linkDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
    }
    public static int getCellCount() { return cellCount; }
    public static void setCellCount(int cellCount) { SombreroToolboxController.cellCount = cellCount; }

}
