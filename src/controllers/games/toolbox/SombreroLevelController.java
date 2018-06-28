package controllers.games.toolbox;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import models.Game;

public class SombreroLevelController {

    private static DisplayerController displayController;
    private static Game selected_game;

    @FXML TextArea description_ta;

    public void linkDisplayerGame(DisplayerController displayController, Game game) {
        SombreroLevelController.displayController = displayController;
        selected_game = game;
        description_ta.setText(selected_game.getDescription());
    }

    @FXML private void onSaveDescription(ActionEvent actionEvent) {
        // envoi description à l'API
        System.out.println(selected_game.getDescription());
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
