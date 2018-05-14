package controllers.users;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SelectedUser {
    public SelectedUser() {
        /* todo default constructor */
    }

    public BorderPane displaySelectedUser() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../../contents/selected_user.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }
}
