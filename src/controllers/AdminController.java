package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;
import services.dao.DAOUser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable  {
    private DAOUser daoUser = new DAOUser();
    private DisplayerController displayerController = new DisplayerController();

    @FXML private TextField firstname_textfield;
    @FXML private TextField lastname_textfield;
    @FXML private TextField username_textfield;
    @FXML private TextField email_textfield;
    @FXML private TextField age_textfield;

    @FXML TableView<User> tab_admin;
    @FXML TableColumn<User, String> column_id;
    @FXML TableColumn<User, String> column_firstname;
    @FXML TableColumn<User, String> column_lastname;
    @FXML TableColumn<User, String> column_email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<User> list_admin = getInitialTableData();
        if (!list_admin.isEmpty()) tab_admin.setItems(list_admin);

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        column_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tab_admin.getColumns().setAll(column_id,column_firstname, column_lastname, column_email );
    }

    private ObservableList<User> getInitialTableData() {
        List<User> list ;
        list = daoUser.getAdminUsers();

        return FXCollections.observableList(list);
    }


    @FXML public void onCreateAdmin() {

        String username = username_textfield.getText();
        String firstname = firstname_textfield.getText();
        String lastname = lastname_textfield.getText();
        String email = email_textfield.getText();
        int age = Integer.valueOf(age_textfield.getText());

        if (daoUser.createAdmin(username, firstname, lastname, email, age))
            displayerController.displayInformation("Remember, default password is 'ESGI2018' ! ;)");
        else
            displayerController.displayAlert("Administrator not created");

    }


}
