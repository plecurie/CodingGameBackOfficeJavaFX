package controllers.games.toolbox;

import controllers.DisplayerController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Game;
import models.Level;
import services.dao.DAOLevel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SombreroLevelController implements Initializable {

    private static DisplayerController displayController;

    @FXML TextArea description_ta;
    @FXML private TableView<String> tab_levels;
    @FXML private TableColumn<String, Integer> column_id;
    @FXML private TableColumn<String, String> column_name;
    @FXML private TableColumn<String, String> column_difficulty;

    private DAOLevel daoLevel = new DAOLevel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList list_levels = getInitialTableData();

        if (!list_levels.isEmpty()) tab_levels.setItems(list_levels);

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));

        tab_levels.getColumns().setAll(column_id, column_name,column_difficulty);

    }

    private ObservableList getInitialTableData() {
        List list ;
        list = daoLevel.getLevels();

        return FXCollections.observableList(list);
    }

    @FXML private void onSaveDescription(ActionEvent actionEvent) {
        daoLevel.updateLevels(description_ta.getText());
    }

    @FXML private void onCreate(ActionEvent actionEvent){
        try {
            displayController.displayToolbox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onSelectedRow(MouseEvent event) throws Exception {

        int index_selected_level = tab_levels.getSelectionModel().getSelectedIndex();
        ObservableValue cell = column_id.getCellObservableValue(index_selected_level);
        Object id = cell.getValue();

        displayController.displayToolbox();
    }

    public void linkDisplayer(DisplayerController displayController) {
        SombreroLevelController.displayController = displayController;
        Game selected_game = Game.SELECTED_GAME;
        description_ta.setText(selected_game.getDescription());
    }

}
