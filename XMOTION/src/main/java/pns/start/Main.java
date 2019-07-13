package pns.start;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainSC.fxml"));

        // Scene scene = new Scene(root);
        Scene scene = new Scene(root, 0, 0, true);

        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("  MOTION   ");
        stage.setWidth(900);
        stage.setHeight(800);
        stage.setScene(scene);

        pns.VidController.MainVController.fixStage(stage);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
