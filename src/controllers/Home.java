package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Home {

    public Home() {
        /* todo default constructor */
    }

    public BorderPane displayHome() throws Exception {
        BorderPane sceneRoot = new BorderPane();
        final AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../contents/home.fxml"));
        sceneRoot.setCenter(anchorPane);
        sceneRoot.setVisible(true);
        return sceneRoot ;
    }

}
