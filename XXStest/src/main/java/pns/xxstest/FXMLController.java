package pns.xxstest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Pane pan;

    Rotate rotate = new Rotate();

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        pan.getChildren().remove(line);

        pan.getChildren().add(line);
        label.setText("Hello World!");
        line.setStrokeWidth(3);

//        drawLines();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                double d = 0;
                while (d < 90) {
                    System.out.println("   ddd  " + d);
                    updateProgress(d, 300);
                    Thread.sleep(5 * 1000);
                    d += 5;
                }
                return null;
            }

            @Override
            protected void updateMessage(String message) {
                super.updateMessage(message); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void updateProgress(double workDone, double max) {
                drawLines(workDone);
                System.out.println("hhh " + workDone);

                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };
//        (new Thread(task)).start();

        newWindow();

//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/wwwIndow.fxml"));
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        stage.setWidth(300);
//        stage.setHeight(300);
//        stage.setTitle("JavaFX    " + Math.random());
//        stage.setScene(scene);
//        stage.setOnShowing(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                System.out.println("  event.getTarget()   " + event.getTarget());;
//                Stage stt = (Stage) event.getTarget();
//                System.out.println("title " + stt.getTitle());
//
//            }
//        });
//
//        stage.show();
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    Line line = new Line(10, 10, 30, 10);

    private void drawLines(double d) {

        rotate.setAngle(d);
        rotate.setPivotX(10);
        rotate.setPivotY(10);
        line.getTransforms().add(rotate);
    }

    private void newWindow() {
        URL location = getClass().getResource("/fxml/wwwIndow.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(location);

        Stage stage = new Stage();
        try {
            Parent root = fxmlLoader.load();
            WwwIndowController cctr = (WwwIndowController) fxmlLoader.getController();
            Scene scene = new Scene(root);
            stage.setTitle("JavaFX    " + Math.random());
            stage.setWidth(300);
            stage.setHeight(300);
            stage.setScene(scene);
            stage.show();
            System.out.println("   cctr.getTarea()  " + (cctr == null));
            cctr.getTarea().setText("  HHHHHHHHHH   " + Math.E + "  " + Math.random());
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
