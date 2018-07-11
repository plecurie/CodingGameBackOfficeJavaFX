package controllers;

import controllers.games.GamesController;
import controllers.games.toolbox.SombreroLevelController;
import controllers.games.toolbox.sombrero.SombreroTestController;
import controllers.games.toolbox.sombrero.SombreroToolboxController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Game;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DisplayerController implements Initializable {

    @FXML AnchorPane main ;
    private static Stage stage ;

    public DisplayerController() { /* todo default constructor */ }
    private void setStage(Stage stage) {
        DisplayerController.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main.setStyle("-fx-background-color: white");
        try {
            onHome(new ActionEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML public void onHome(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayHome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onPreferences(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayPreferences());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onSettings(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displaySettings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onUsers(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void onGames(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayGames());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BorderPane displayHome() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/home.fxml"));
        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        HomeController homeControllerController = loader.getController();
        homeControllerController.setMain(this, main);

        return sceneRoot ;
    }

    private BorderPane displayPreferences() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/preferences.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot ;
    }

    private BorderPane displaySettings() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/gaming_settings.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot ;
    }

    BorderPane displayUsers() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/users.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot ;
    }

    BorderPane displayGames() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/games.fxml"));
        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        GamesController gamesControllerController = loader.getController();
        gamesControllerController.linkDisplayer(this);

        return sceneRoot ;
    }

    public void displaySombreroLevels() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/sombrero_levels.fxml"));
        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        SombreroLevelController sombreroLevelController = loader.getController();
        sombreroLevelController.linkDisplayer(this);

        main.getChildren().clear();
        main.getChildren().add(sceneRoot);
    }

    public void displaySombreroTest(GridPane sombrero_to_test, String level_name, Integer f1, Integer f2, Integer f3, Integer f4, Integer difficulty, int cellCount) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/sombrero_test.fxml"));

        SombreroTestController.sombrero_test = sombrero_to_test;
        SombreroTestController.level_name = level_name;
        SombreroTestController.f1 = f1;
        SombreroTestController.f2 = f2;
        SombreroTestController.f3 = f3;
        SombreroTestController.f4 = f4;
        SombreroTestController.level_difficulty = difficulty;
        SombreroTestController.cell_max = cellCount;

        GridPane sombrero_test = SombreroTestController.buildGrid();
        sombrero_test.setMaxSize(600,600);

        BorderPane borderPane = loader.load();
        borderPane.setLeft(sombrero_test);
        borderPane.setStyle("-fx-background-color: moccasin ");

        main.getChildren().clear();
        main.getChildren().add(borderPane);

    }

    public void displayToolbox() throws Exception {
        BorderPane sceneRoot = new BorderPane();

        switch (Game.GAME_ID) {
            case 1 : {
                displayHack();
                break;
            }
            case 2 : {
                sceneRoot = displayExplorer();
                break;
            }
            case 3 : {
                int cell = SombreroToolboxController.getCellCount();
                if (cell == 0) {
                    SombreroToolboxController.setCellCount(10);
                    cell = 10;
                }
                sceneRoot = displaySombrero(cell);
                break;
            }
            case 4 : {
                sceneRoot = displayQuizz();
                break;
            }
            default:
                break;
        }

        main.getChildren().clear();
        main.getChildren().add(sceneRoot);

    }

    private void displayHack() {
        try {
            stage.hide();
            Stage stage1 = new Stage();
            StackPane root = FXMLLoader.load(getClass().getResource("../contents/pepithack.fxml"));
            Scene scene = new Scene(root, 1200, 600, true);

            scene.setFill(Color.BLACK);
            root.setStyle("-fx-background-color: transparent");
            scene.setCamera(new PerspectiveCamera());

            stage1.setTitle("PepitHack");
            stage1.setResizable(false);
            stage1.setScene(scene);
            stage1.show();
            stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        stage.show();
                        main.getChildren().add(displayGames());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BorderPane displayExplorer() throws IOException {
        String resource_name = "../contents/explorer_toolbox.fxml";

        BorderPane sceneRoot = new BorderPane();

        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resource_name));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot;
    }

    public BorderPane displaySombrero(int cell) throws IOException {
        String resource_name = "../contents/sbr_toolbox_" + cell + "x" + cell + ".fxml";

        BorderPane sceneRoot = new BorderPane();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource_name));
        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        SombreroToolboxController sombreroToolboxController = loader.getController();
        sombreroToolboxController.linkDisplayer(this);

        return sceneRoot ;
    }

    public BorderPane displayQuizz() throws IOException {
        String resource_name = "../contents/quizz_toolbox.fxml";

        BorderPane sceneRoot = new BorderPane();

        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resource_name));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot;
    }

    public void displayAlert(String error_message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pepit'CodingGame - Error");
        alert.setHeaderText(null);
        alert.setContentText(error_message);
        alert.showAndWait();
    }

    public boolean displayConfirmation(String actionToConfirm) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pepit'CodingGame - Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(actionToConfirm);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    void displayDashboard(){
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("../contents/displayer.fxml"));
            sceneRoot.setCenter(anchorPane);
            Scene scene = new Scene(sceneRoot);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/contents/images/favicon.png"));
            stage.setScene(scene);
            setStage(stage);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    onDisconnect(new ActionEvent());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onDisconnect(ActionEvent actionEvent){
        stage.close();
        StarterController starterController = new StarterController();
        starterController.displayStarter();
    }

}
