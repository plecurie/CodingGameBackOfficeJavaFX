package controllers.games.toolbox.explorer;

import controllers.DisplayerController;
import controllers.games.toolbox.quizz.QuizzController;
import controllers.games.toolbox.quizz.QuizzLevelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Game;
import models.LevelExplorer;
import models.LevelQuizz;
import services.dao.DAOLevel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ExplorerLevelController implements Initializable{
    private static DisplayerController displayController;

    @FXML private TableView<LevelExplorer> tab_level_explorer;
    @FXML private TableColumn<LevelExplorer, String> column_explorer_question;
    @FXML private TableColumn<LevelExplorer, String> column_explorer_answer1;
    @FXML private TableColumn<LevelExplorer, String> column_explorer_answer2;
    @FXML private TableColumn<LevelExplorer, String> column_explorer_answer3;
    @FXML private TableColumn<LevelExplorer, String> column_explorer_correctAnswer;

    private DAOLevel daoLevel = new DAOLevel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<LevelExplorer> listLevelsExplorer = getInitialTableData();

        if(!listLevelsExplorer.isEmpty()){
            tab_level_explorer.setItems(listLevelsExplorer);
        }

        column_explorer_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        column_explorer_answer1.setCellValueFactory(new PropertyValueFactory<>("answer1"));
        column_explorer_answer2.setCellValueFactory(new PropertyValueFactory<>("answer2"));
        column_explorer_answer3.setCellValueFactory(new PropertyValueFactory<>("answer3"));
        column_explorer_correctAnswer.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

        tab_level_explorer.getColumns().setAll(column_explorer_question, column_explorer_answer1, column_explorer_answer2, column_explorer_answer3, column_explorer_correctAnswer);
    }

    private ObservableList<LevelExplorer> getInitialTableData() {
        List<LevelExplorer> list ;
        list = daoLevel.getLevelsExplorer();
        return FXCollections.observableList(list);
    }


    @FXML
    private void onCreateLevel(ActionEvent actionEvent){
        try {
            displayController.displayExplorer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void linkDisplayer(DisplayerController displayerController){
        ExplorerLevelController.displayController = displayerController;
        Game selected_game = Game.SELECTED_GAME;
    }
}
