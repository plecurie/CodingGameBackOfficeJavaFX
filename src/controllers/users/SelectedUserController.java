package controllers.users;

import controllers.DisplayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.History;
import models.User;
import services.dao.DAOUser;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SelectedUserController implements Initializable {

    @FXML private Label label_name;
    @FXML private TableView<History> tab_history;
    @FXML private TableColumn<History, Integer> column_game;
    @FXML private TableColumn<History, String> column_level;
    @FXML private TableColumn<History, String> column_score;
    @FXML private TableColumn<History, Date> column_date;

    private DAOUser daoUser = new DAOUser();
    private DisplayerController displayercontroller;
    private AnchorPane main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        label_name.setText(User.getSelectedUser().getLastname().toUpperCase() + " " + User.getSelectedUser().getFirstname());
        ObservableList<History> list_history = getInitialTableData();
        if (!list_history.isEmpty()) tab_history.setItems(list_history);

        column_game.setCellValueFactory(new PropertyValueFactory<>("game"));
        column_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        column_score.setCellValueFactory(new PropertyValueFactory<>("score"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        tab_history.getColumns().setAll(column_game, column_level,column_score, column_date);

    }

    private ObservableList<History> getInitialTableData() {
        List<History> list ;
        list = daoUser.getHistorySelectedUser(User.getSelectedUser().getId());

        return FXCollections.observableList(list);
    }

    @FXML void onReturn() throws Exception {
        main.getChildren().clear();
        main.getChildren().add(displayercontroller.displayUsers());
    }

    public void linkDisplayer(DisplayerController displayerController, AnchorPane main) {
        this.displayercontroller = displayerController;
        this.main = main;
    }


}
