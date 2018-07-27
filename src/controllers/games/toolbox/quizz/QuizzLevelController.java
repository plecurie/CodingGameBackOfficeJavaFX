package controllers.games.toolbox.quizz;

import controllers.DisplayerController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Game;
import models.Level;
import models.LevelQuizz;
import services.dao.DAOLevel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizzLevelController implements Initializable{

    private static DisplayerController displayController;


    @FXML private TableView<LevelQuizz> tab_level_quizz;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_question;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_answer1;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_answer2;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_answer3;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_answer4;
    @FXML private TableColumn<LevelQuizz, String> column_quizz_difficulty;

    @FXML private Button btn_delete_level_quizz;

    private DAOLevel daoLevel = new DAOLevel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<LevelQuizz> listLevelsQuizz = getInitialTableData();

        if(!listLevelsQuizz.isEmpty()){
            tab_level_quizz.setItems(listLevelsQuizz);
        }

        column_quizz_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        column_quizz_answer1.setCellValueFactory(new PropertyValueFactory<>("answer1"));
        column_quizz_answer2.setCellValueFactory(new PropertyValueFactory<>("answer2"));
        column_quizz_answer3.setCellValueFactory(new PropertyValueFactory<>("answer3"));
        column_quizz_answer4.setCellValueFactory(new PropertyValueFactory<>("answer4"));
        column_quizz_difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        tab_level_quizz.getColumns().setAll(column_quizz_question, column_quizz_answer1, column_quizz_answer2, column_quizz_answer3, column_quizz_answer4, column_quizz_difficulty);


    }
    private ObservableList<LevelQuizz> getInitialTableData() {
        List<LevelQuizz> list ;
        list = daoLevel.getLevelsQuizz();

        return FXCollections.observableList(list);
    }

    public void linkDisplayer(DisplayerController displayerController){
        QuizzLevelController.displayController = displayerController;
        Game selected_game = Game.SELECTED_GAME;

    }

    @FXML private void onCreateLevel(ActionEvent actionEvent){
        try {
            displayController.displayQuizz();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void deleteLevelQuizz(ActionEvent actionEvent){
        LevelQuizz selectedItem = tab_level_quizz.getSelectionModel().getSelectedItem();
        daoLevel.deleteLevel(selectedItem.getId_levels());
        System.out.println("selected row : " + selectedItem.getId());
    }


}
