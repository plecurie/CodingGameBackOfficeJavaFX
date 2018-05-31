import controllers.StarterController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        StarterController starterController = new StarterController();
        starterController.setStage(stage);
        starterController.displayStarter();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
