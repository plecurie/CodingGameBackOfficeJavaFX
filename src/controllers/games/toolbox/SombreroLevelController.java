package controllers.games.toolbox;

import controllers.DisplayerController;
import dao.DAOGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Game;

public class SombreroLevelController {

    private DisplayerController displayController;
    Game selected_game;

    @FXML TextArea description_ta;

    public void linkDisplayerGame(DisplayerController displayController, Game game) {
        this.displayController = displayController;
        this.selected_game = game;
        description_ta.setText(selected_game.getDescription());
    }

    @FXML private void onSaveDescription(ActionEvent actionEvent) {
        // envoi description à l'API

    }

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            displayController.displayToolbox(selected_game);
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
