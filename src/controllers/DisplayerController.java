package controllers;

import controllers.games.GamesController;
import controllers.games.levels.LevelsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayerController implements Initializable {

    @FXML AnchorPane main ;
    private static Stage stage ;

    public DisplayerController() {
        /* todo default constructor */
    }

    void setStage(Stage stage) {
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

    BorderPane displayHome() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/home.fxml"));
        final AnchorPane anchorPane = loader.load();
        HomeController homeControllerController = loader.getController();
        homeControllerController.setMain(this, main);
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }

    BorderPane displayPreferences() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/preferences.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }

    BorderPane displaySettings() throws Exception {
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
        GamesController gamesControllerController = loader.getController();
        gamesControllerController.setMain(this, main);
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
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
    public BorderPane displayLevels(int selected_game) throws Exception {
        BorderPane sceneRoot = new BorderPane();

        if (selected_game == 1) {
            displayHack();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/levels.fxml"));
            final AnchorPane anchorPane = loader.load();
            LevelsController levelsController = loader.getController();
            levelsController.init(this, main, selected_game);
            sceneRoot.setCenter(anchorPane);
            sceneRoot.setVisible(true);
        }

        return sceneRoot ;
    }

    public BorderPane displaySelectedLevel(String game) throws Exception {
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/sbr_toolbox_10x10.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }

    void displayAlert() {
        BorderPane sceneRoot = new BorderPane();
        try {
            final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/error.fxml"));
            sceneRoot.setCenter(anchorPane);
            Scene scene = new Scene(sceneRoot);
            stage.getIcons().add(new Image("/contents/images/favicon.png"));
            stage.setTitle("Pepit'CodingGame - Error");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
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
