package controllers.games.toolbox;

import controllers.DisplayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SombreroController {

    public void setDisplayController(DisplayerController displayController) {
        this.displayController = displayController;
    }

    private DisplayerController displayController;
    @FXML Label game_label;


    @FXML private void onSave(ActionEvent actionEvent) {
        // envoi modifs à l'API
    }

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            displayController.displayToolbox(3);
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
