package pns.xxstest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Pane pan;

    Rotate rotate = new Rotate();

    @FXML
    private void handleButtonAction(ActionEvent event) {
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
        (new Thread(task)).start();
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
}
