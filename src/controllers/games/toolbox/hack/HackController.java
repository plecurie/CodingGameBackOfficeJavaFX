package controllers.games.toolbox.hack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;

public class HackController implements Initializable {

    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;

    private double PARENT_WIDTH, PARENT_HEIGHT;

    private final Rotate rotateX = new Rotate(10, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    @FXML StackPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Group puzzle = new Group();
        double size = 400;
        puzzle.getTransforms().addAll(rotateX, rotateY);

        Box box_1 = createBox(0.125,0.125,0.125, size, new PhongMaterial(Color.GREEN));
        Box box_2 = createBox(0.125,-0.125,0.125, size, new PhongMaterial(Color.CADETBLUE));
        Box box_3 = createBox(-0.125, -0.125, 0.125, size, new PhongMaterial(Color.GHOSTWHITE));
        Box box_4 = createBox(-0.125, 0.125, 0.125, size, new PhongMaterial(Color.RED));
        Box box_5 = createBox(0.125, 0.125, -0.125, size, new PhongMaterial(Color.ORANGE));
        Box box_6 = createBox(0.125, -0.125, -0.125, size, new PhongMaterial(Color.GREENYELLOW));
        Box box_7 = createBox(-0.125, -0.125, -0.125, size, new PhongMaterial(Color.HOTPINK));
        Box box_8 = createBox(-0.125, 0.125, -0.125, size, new PhongMaterial(Color.CHOCOLATE));

        puzzle.getChildren().addAll(box_1,box_2,box_3,box_4,box_5,box_6,box_7,box_8);

        root.getChildren().add(puzzle);
        root.setOnMousePressed(position -> {
            mouseOldX = position.getSceneX();
            mouseOldY = position.getSceneY();

            PARENT_HEIGHT = root.getParent().getLayoutBounds().getHeight();
            PARENT_WIDTH = root.getParent().getLayoutBounds().getWidth();

            if ((mouseOldY > PARENT_HEIGHT/2 - 100)
                    && (mouseOldY < PARENT_HEIGHT/2 + 100)){
                if (mouseOldX < PARENT_WIDTH/2 - 100 ) {
                    rotateY.setAngle(rotateY.getAngle() - 90);
                }
                else if (mouseOldX > PARENT_WIDTH/2 + 100) {
                    rotateY.setAngle(rotateY.getAngle() + 90);
                }
            }
            else if ((mouseOldX > PARENT_WIDTH/2 - 100)
                    && (mouseOldX < PARENT_WIDTH/2 + 100)) {
                if (mouseOldY < PARENT_HEIGHT/2 - 100) {
                    rotateX.setAngle(rotateX.getAngle() - 90);
                }
                else if (mouseOldY > PARENT_HEIGHT/2 + 100) {
                    rotateX.setAngle(rotateX.getAngle() + 90);
                }
            }
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
/*
    public Image createImage(double size) {
        Random rnd = new Random();
        int width = (int) size/2;
        int height = (int) size/2;
        WritableImage wr = new WritableImage(width, height);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                pw.setColor(x, y, color);
            }
        }
        return wr;
    }
*/


    private Box createBox (double x, double y, double z, double size, PhongMaterial material) {
        Box box = new Box(100,100,100);

        box.setTranslateX(x * size);
        box.setTranslateY(y * size);
        box.setTranslateZ(z * size);
        box.setMaterial(material);

        return box;
    }

}