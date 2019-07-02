/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pns.VidController.MotionTools;

/**
 * FXML Controller class
 *
 * @author Movement
 */
public class DrawingController implements Initializable {

    @FXML
    private VBox spt;
    @FXML
    private HBox tools;
    @FXML
    private Pane panel;
    @FXML
    private Button stopCycle;

    private Pane panelInfo;
    private Pane panelSpt;

    private Line line = new Line();

    private Light.Point center;

    private ConvertToSegment toSegment = ConvertToSegment.getInstance();
    private MotionTools toolMethods = new MotionTools();
    private boolean isGoingRun = false;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @FXML
    public void cycleRunFowardStop() {
        toolMethods.setCycleRunFoward(!toolMethods.isCycleRunFoward());
        stopCycle.setText("Resume");
        if (toolMethods.isCycleRunFoward()) {
            cycleForward();
        }
    }

    @FXML
    public void cycleForward() {
        System.out.println("     isGoingRun    " + isGoingRun);
        if (!isGoingRun) {
            isGoingRun = true;
            prepareSegmentData();
            createSegmentVisual();
            toolMethods.setCycleRunFoward(true);
            toolMethods.setCycleRunBackword(false);
            stopCycle.setText("Pause");
            //createSegmentVisual();
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (toolMethods.isCycleRunFoward()) {
                        updateProgress(1, 1000);
                        Thread.sleep(1 * 2000);
                    }
                    isGoingRun = false;
                    return null;
                }

                @Override
                protected void updateProgress(long workDone, long max) {
                    toolMethods.drawSegmentForward();
                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
                }
            };

            (new Thread(task)).start();
        }
    }

    @FXML
    public void cycleBackward() {
        if (!isGoingRun) {
            isGoingRun = true;
            prepareSegmentData();
            createSegmentVisual();
            toolMethods.setCycleRunBackword(true);
            toolMethods.setCycleRunFoward(false);
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (toolMethods.isCycleRunBackword()) {
                        updateProgress(1, 1100);
                        Thread.sleep(1 * 1000);
                    }
                    isGoingRun = false;
                    return null;
                }

                @Override
                protected void updateProgress(long workDone, long max) {
                    toolMethods.drawSegmentBackward();
                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
                }
            };

            (new Thread(task)).start();
        }
    }

    @FXML
    public void forward() {
        prepareSegmentData();
        createSegmentVisual();
        toolMethods.drawSegmentForward();
    }

    @FXML
    public void backward() {
        prepareSegmentData();
        createSegmentVisual();
        toolMethods.drawSegmentBackward();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        panelSpt = new AnchorPane();
        panelSpt.setId("spt");
        panelInfo = new AnchorPane();
        panelInfo.setId("info");
        toolMethods.getText().setId("txt");
        toolMethods.getText().setTranslateY(15);

        panel.setPrefSize(700, 600);
        center = new Light.Point(panel.getPrefWidth() / 2, panel.getPrefHeight() / 2, 2, Color.CORAL);
        toolMethods.getRotate().setPivotX(0);//Set the Pivot X to be the same location as the Circle X. This is only used to help you see the Pivot point
        toolMethods.getRotate().setPivotY(0);//Set the Pivot Y to be the same location as the Ci
        toolMethods.getRotate().setPivotZ(100);
        panelSpt.getTransforms().add(toolMethods.getRotate());

        panelSpt.setTranslateX(center.getX());
        panelSpt.setTranslateY(center.getY());

        toolMethods.getRt().setNode(panelSpt);
        drawCoords();

    }

    private void drawCoords() {
        double x = 0;
        while (x <= panel.getPrefWidth()) {
            Line line = new Line(x, 0, x, panel.getPrefHeight());
            line.setStroke(Color.CYAN);
            line.setStrokeWidth(.15);
            x += 5;
            panel.getChildren().add(line);
        }
        double y = 0;
        while (y <= panel.getPrefWidth()) {
            Line line = new Line(0, y, panel.getPrefWidth(), y);
            line.setStroke(Color.CORAL);
            line.setStrokeWidth(.1);
            y += 5;
            panel.getChildren().add(line);
        }

    }

    private void createSegmentVisual() {
        if (!toolMethods.isVisualizated()) {

            line.setEndY(100);
            line.setFill(null);
            line.setStroke(Color.RED);
            line.setStrokeWidth(2);

            panelSpt.getChildren().remove(line);
            panelSpt.getChildren().add(line);
            toolMethods.setVisualizated(true);
            transformInfoInitial();
        }
    }

    private void transformInfoInitial() {

        panelInfo.getChildren().remove(toolMethods.getText());
        panelInfo.getChildren().add(toolMethods.getText());

        panelInfo.getChildren().remove(toolMethods.getText());
        panelInfo.getChildren().add(toolMethods.getText());

        panel.getChildren().remove(panelSpt);
        panel.getChildren().add(panelSpt);

        panel.getChildren().remove(panelInfo);
        panel.getChildren().add(panelInfo);
    }

    private void prepareSegmentData() {
        toolMethods.getPoint9List().clear();
        toolMethods.getPoint9List().addAll(toSegment.getPoint9TreeSet());
    }

}
