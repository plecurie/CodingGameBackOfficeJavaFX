package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;

    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    @FXML StackPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Group puzzle = new Group();
        double size = 400;
        puzzle.getTransforms().addAll(rotateX, rotateY);

        Box box_1 = createBox(0,0,0, size, new PhongMaterial(Color.GREEN));
        Box box_2 = createBox(0,-0.25,0, size, new PhongMaterial(Color.CADETBLUE));
        Box box_3 = createBox(-0.25, -0.25, 0, size, new PhongMaterial(Color.GHOSTWHITE));
        Box box_4 = createBox(-0.25, 0, 0, size, new PhongMaterial(Color.RED));
        Box box_5 = createBox(0, 0, -0.25, size, new PhongMaterial(Color.ORANGE));
        Box box_6 = createBox(0, -0.25, -0.25, size, new PhongMaterial(Color.GREENYELLOW));
        Box box_7 = createBox(-0.25, -0.25, -0.25, size, new PhongMaterial(Color.HOTPINK));
        Box box_8 = createBox(-0.25, 0, -0.25, size, new PhongMaterial(Color.CHOCOLATE));

        puzzle.getChildren().addAll(box_1,box_2,box_3,box_4,box_5,box_6,box_7,box_8);

        root.getChildren().add(puzzle);

        puzzle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getSceneX());
            }
        });

        root.setOnMousePressed(position -> {
            mouseOldX = position.getSceneX();
            mouseOldY = position.getSceneY();
        });

        root.setOnMouseDragged(position -> {
            mousePosX = position.getSceneX();
            mousePosY = position.getSceneY();
            rotateX.setAngle(rotateX.getAngle() - (mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle() + (mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });

    }

    private Box createBox (double x, double y, double z, double size, PhongMaterial material) {
        Box box = new Box(100,100,100);

        box.setTranslateX(x * size);
        box.setTranslateY(y * size);
        box.setTranslateZ(z * size);
        box.setMaterial(material);

        return box;
    }

}