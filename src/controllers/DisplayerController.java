package controllers;

import controllers.games.GamesController;
import controllers.games.toolbox.SombreroLevelController;
import controllers.games.toolbox.explorer.ExplorerController;
import controllers.games.toolbox.explorer.ExplorerLevelController;
import controllers.games.toolbox.sombrero.Sombrero;
import controllers.games.toolbox.sombrero.SombreroFactory;
import controllers.games.toolbox.quizz.QuizzController;
import controllers.games.toolbox.quizz.QuizzLevelController;
import controllers.games.toolbox.sombrero.SombreroTestController;
import controllers.games.toolbox.sombrero.SombreroToolboxController;
import controllers.users.SelectedUserController;
import controllers.users.UsersController;
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
import models.User;
import services.DataFactory;
import services.dao.DAOAuth;

import javax.json.JsonObject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/administration.fxml"));
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

    public BorderPane displayUsers() throws Exception {

        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/users.fxml"));
        AnchorPane anchorPane = loader.load();
        UsersController usersController = loader.getController();
        usersController.linkDisplayer(this);
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        return sceneRoot ;
    }

    public void displaySelectedUser(User selected_user) throws IOException {

        BorderPane sceneRoot = new BorderPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/selected_user.fxml"));

        User.setSelectedUser(selected_user);

        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        SelectedUserController selectedUserController = loader.getController();
        selectedUserController.linkDisplayer(this, main);

        main.getChildren().clear();
        main.getChildren().add(sceneRoot);
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

    public void displaySombreroTest(Sombrero sombrero) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/sombrero_test.fxml"));

        SombreroFactory sombreroFactory = new SombreroFactory();

        JsonObject obj_sombrero = sombreroFactory.convertSombreroToJsonObject(sombrero);
        List sombrero_list = new DataFactory().parseJSON(String.valueOf(obj_sombrero));

        sombreroFactory.browseAndBuildSombrero(sombrero_list, true);
        Sombrero.getSelectedSombrero().getGridpane().setMaxSize(600,600);

        BorderPane borderPane = loader.load();
        borderPane.setLeft(Sombrero.getSelectedSombrero().getGridpane());
        borderPane.setStyle("-fx-background-color: moccasin ");

        SombreroTestController testController = loader.getController();
        testController.linkDisplayer(this);

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
                sceneRoot = displayLevelsExplorer();
                break;
            }
            case 3 : {
                sceneRoot = displaySombrero();
                break;
            }
            case 4 : {
                sceneRoot = displayLevelsQuizz();
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

    private BorderPane displaySombrero() throws IOException {
        String resource_name = "../contents/sbr_toolbox_10x10.fxml";

        BorderPane sceneRoot = new BorderPane();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../contents/sbr_toolbox_10x10.fxml"));
        GridPane gridPane;
        if (Sombrero.getSelectedSombrero() != null){
            SombreroFactory sombreroFactory = new SombreroFactory();
            JsonObject obj_sombrero = sombreroFactory.convertSombreroToJsonObject(Sombrero.getSelectedSombrero());
            List sombrero_list = new DataFactory().parseJSON(String.valueOf(obj_sombrero));

            sombreroFactory.browseAndBuildSombrero(sombrero_list, false);
            gridPane = Sombrero.getSelectedSombrero().getGridpane();
        }
        else{
            gridPane = new SombreroFactory().buildEmptyGridpane(10);
            Sombrero.setSelectedSombrero(new Sombrero(gridPane,"", 0, 0, 0, 0, 1, 10));
        }

        gridPane.setMaxSize(600,600);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(gridPane);

        final AnchorPane anchorPane = loader.load();
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        anchorPane.getChildren().add(borderPane);

        SombreroToolboxController sombreroToolboxController = loader.getController();
        sombreroToolboxController.linkDisplayer(this);

        return sceneRoot ;
    }

    public void displayQuizz() throws IOException {
        String resource_name = "../contents/quizz_toolbox.fxml";

        BorderPane sceneRoot = new BorderPane();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource_name));
        //AnchorPane anchorPane = loader.getController();
        final AnchorPane anchorPane = loader.load();
        //final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resource_name));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        System.out.println("toolbox level " + loader.getController().toString());

        QuizzController quizzController = loader.getController();
        quizzController.linkDisplayer(this);

        main.getChildren().clear();
        main.getChildren().add(sceneRoot);
    }

    public BorderPane displayLevelsQuizz() throws IOException {
        String resource_name = "../contents/quizz_levels.fxml";

        BorderPane sceneRoot = new BorderPane();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource_name));
        //AnchorPane anchorPane = loader.getController();
        final AnchorPane anchorPane = loader.load();
        //final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resource_name));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        System.out.println("list level " + loader.getController().toString());

        QuizzLevelController quizzLevelController = loader.getController();
        quizzLevelController.linkDisplayer(this);

        return sceneRoot;
    }

    public void displayExplorer() throws IOException {
        String resource_name = "../contents/explorer_toolbox.fxml";

        BorderPane sceneRoot = new BorderPane();

        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(resource_name));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        main.getChildren().clear();
        main.getChildren().add(sceneRoot);
    }

    public BorderPane displayLevelsExplorer() throws IOException {
        String resource_name = "../contents/explorer_levels.fxml";

        BorderPane sceneRoot = new BorderPane();



        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource_name));
        final AnchorPane anchorPane = loader.load();
        System.out.println("jdsofpofjfds");
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);

        System.out.println("list level " + loader.getController().toString());

        ExplorerLevelController explorerLevelController = loader.getController();
        explorerLevelController.linkDisplayer(this);

        return sceneRoot;
    }

    public void displayAlert(String error_message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pepit'CodingGame - Error");
        alert.setHeaderText(null);
        alert.setContentText(error_message);
        alert.showAndWait();
    }

    public void displayInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pepit'CodingGame - Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
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
            stage.setOnCloseRequest(event -> onDisconnect());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onDisconnect(){
        DAOAuth daoAuth = new DAOAuth();
        if (daoAuth.logout()) {
            stage.close();
            StarterController starterController = new StarterController();
            starterController.displayStarter();
        }
    }

}
