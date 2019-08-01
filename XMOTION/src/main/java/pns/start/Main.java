package pns.start;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pns.VidController.MainVController;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL location = getClass().getResource("/fxml/MainSC.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        Parent root = fxmlLoader.load();
        //FXMLLoader.load(getClass().getResource("/fxml/MainSC.fxml"));

        // Scene scene = new Scene(root);
        Scene scene = new Scene(root, 10, 10, true);

        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("  == MAN  MOTION ==  ");
        stage.setWidth(400);
        stage.setHeight(200);
//        stage.setMaximized(true);
        stage.setScene(scene);

        pns.VidController.MainVController.fixStage(stage);
        stage.show();
        MainVController controller = (MainVController) fxmlLoader.getController();
        stage.setOnCloseRequest(event
                -> {
            controller.closeTasks();
            System.out.println("CLOSING   MAIN WINDOW");
            Platform.exit();
        });

    }
    public static final long timeout = 500;

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // LoggerCS.logInfo(" Starting Man Motion APP");
        launch(args);
    }

    public static Rectangle2D screenDimFind() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        double wd = primaryScreenBounds.getWidth();
        double ht = primaryScreenBounds.getHeight();
        return primaryScreenBounds;
    }
}
