package controllers.games.levels;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SelectedLevel {
    public SelectedLevel() {
        /* todo default constructor */
    }

    public BorderPane displaySelectedLevel() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../../../contents/sbr_toolbox_10x10.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }
}
