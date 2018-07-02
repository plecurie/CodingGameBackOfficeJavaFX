package controllers.games.toolbox.sombrero;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SombreroTestController {
    public static GridPane grid_test;
    public static int cell_max;
    public static String functions;

    @FXML void onClick() {
        readGrid();
    }

    @FXML private void onPlay() {

    }

    @FXML private void onNext() {

    }

    @FXML private void onStop() {

    }

    private void readGrid() {
        System.out.println("\nNombre de fonctions disponibles : " + functions + "\n");
        int column = 0, line = 0;

        for (int i = 0; i < grid_test.getChildren().size() ; i++) {

            if (column == cell_max) {
                column = 0;
                line++;
            }
            if (column == 0 && line < cell_max) System.out.println(line);
            column++;

            Node node = grid_test.getChildren().get(i);
            Pane pane = (Pane) node.lookup("Pane");
            try {
                switch (pane.getStyle()) {
                    case "-fx-background-color:BLACK;" : {
                        System.out.println("BLACK");
                        break;
                    }
                    case "-fx-background-color:BLUE;" : {
                        System.out.println("BLUE");
                        break;
                    }
                    case "-fx-background-color:RED;" : {
                        System.out.println("RED");
                        break;
                    }
                    case "-fx-background-color:GREEN;" : {
                        System.out.println("GREEN");
                        break;
                    }
                }
                Label label = (Label) pane.getChildren().get(0).lookup("Label");
                System.out.println(label.getText());
            } catch (Exception ignored) {}
        }
    }
}
