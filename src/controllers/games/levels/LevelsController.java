package controllers.games.levels;

import controllers.DisplayerController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Level;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelsController {

    private DisplayerController displayController;
    private AnchorPane main ;
    @FXML Label game_label;

    public void init(DisplayerController dc, AnchorPane m, String game_name) {
        this.displayController = dc;
        this.main = m;
        game_label.setText(game_name);
    }

    @FXML private void onSave(ActionEvent actionEvent) {
        // envoi modifs à l'API
    }

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            main.getChildren().clear();
            main.getChildren().add(displayController.displaySelectedLevel(game_label.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onSelectedRow(MouseEvent event) {
/*
        int index_selected_level = tab_levels.getSelectionModel().getSelectedIndex();
        ObservableValue cell = column_id.getCellObservableValue(index_selected_level);
        Object id = cell.getValue();
        Level selected_level = daoLevel.getSelectedUser(Integer.valueOf(id.toString()));
        System.out.println(selected_level.getId());
*/
    }

    @FXML private void onModify(ActionEvent actionEvent) {

    }

    @FXML private void onDelete(ActionEvent actionEvent) {

    }




}
