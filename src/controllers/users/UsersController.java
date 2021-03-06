package controllers.users;

import controllers.DisplayerController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.User;
import services.dao.DAOUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    @FXML private TableView<User> tab_users;
    @FXML private TableColumn<User, Integer> column_id;
    @FXML private TableColumn<User, String> column_username;
    @FXML private TableColumn<User, String> column_firstname;
    @FXML private TableColumn<User, String> column_lastname;
    @FXML private TableColumn<User, Integer> column_age;
    @FXML private TableColumn<User, String> column_email;
    @FXML private TableColumn<User, String> column_profil;
    @FXML private TableColumn<User, Integer> column_level;
    @FXML private TableColumn<User, Float> column_exp;
    @FXML private TableColumn<User, String> column_type;

    private DAOUser daoUser = new DAOUser();
    private DisplayerController displayController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<User> list_users = getInitialTableData();
        if (!list_users.isEmpty()) tab_users.setItems(list_users);

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        column_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        column_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_profil.setCellValueFactory(new PropertyValueFactory<>("profil"));
        column_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        column_exp.setCellValueFactory(new PropertyValueFactory<>("exp"));
        column_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        tab_users.getColumns().setAll(column_id, column_username,column_firstname, column_lastname, column_age, column_email, column_profil, column_level, column_exp, column_type );
    }

    private ObservableList<User> getInitialTableData() {
        List<User> list ;
        list = daoUser.getUsers();

        return FXCollections.observableList(list);
    }

    @FXML protected void onSelectedRow() {

        int index_selected_user = tab_users.getSelectionModel().getSelectedIndex();
        ObservableValue<Integer> cell = column_id.getCellObservableValue(index_selected_user);
        Object id = cell.getValue();

        try {
            User selected_user = daoUser.getSelectedUser(Integer.valueOf(id.toString()));
            if (!daoUser.getHistorySelectedUser(selected_user.getId()).isEmpty())
                displayController.displaySelectedUser(selected_user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void linkDisplayer(DisplayerController displayerController) {
        this.displayController = displayerController;
    }
}