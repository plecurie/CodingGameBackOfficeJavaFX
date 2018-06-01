package controllers;

import controllers.games.GamesController;
import controllers.games.levels.LevelsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public BorderPane displayLevels() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/levels.fxml"));
        final AnchorPane anchorPane = loader.load();
        LevelsController levelsControllerController = loader.getController();
        levelsControllerController.setMain(this, main);
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }

    public BorderPane displaySelectedLevel() throws Exception {
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
