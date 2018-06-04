package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {

    private final Group root = new Group();

    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;

    private double mouseOldX, mouseNewX;
    private double mouseOldY, mouseNewY;

    @Override
    public void start(Stage stage) throws Exception {
        // Create Camera
        camera = new PerspectiveCamera(true);
        cameraRotateX = new Rotate(-20, Rotate.X_AXIS);
        cameraRotateY = new Rotate(0, Rotate.Y_AXIS);
        cameraRotateZ = new Rotate(0, Rotate.Z_AXIS);
        cameraTranslate = new Translate(0, 0, -20);
        camera.getTransforms().addAll(
                cameraRotateX,
                cameraRotateY,
                cameraRotateZ,
                cameraTranslate);
        root.getChildren().add(camera);

        // Create Material
        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.DARKRED);
        phongMaterial.setSpecularColor(Color.RED);

        // Create Box
        Box box = new Box(5, 5, 5);
        box.setMaterial(phongMaterial);
        root.getChildren().add(box);

        // Create Scene and Handlers
        Scene scene = new Scene(root, 800, 450, true);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onMousePressed(mouseEvent);
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onMouseDragged(mouseEvent);
            }
        });

        // Set the Stage
        stage.setTitle("Test Cube 3D");
        stage.setScene(scene);
        scene.setFill(Color.GRAY);
        scene.setCamera(camera);
        stage.show();
    }

    public void onMousePressed(MouseEvent mouseEvent) {
        mouseOldX = mouseNewX = mouseEvent.getSceneX();
        mouseOldY = mouseNewY = mouseEvent.getSceneY();
    }

    public void onMouseDragged(MouseEvent mouseEvent) {
        mouseOldX = mouseNewX;
        mouseOldY = mouseNewY;
        mouseNewX = mouseEvent.getSceneX();
        mouseNewY = mouseEvent.getSceneY();

        double mouseDeltaX = (mouseNewX - mouseOldX);
        double mouseDeltaY = (mouseNewY - mouseOldY);

        cameraRotateX.setAngle(cameraRotateX.getAngle() + mouseDeltaY);
        cameraRotateY.setAngle(cameraRotateY.getAngle() + mouseDeltaX);
    }

    public static void main(String[] args) {
        launch();
    }

}
