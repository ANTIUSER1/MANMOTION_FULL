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
import pns.VidController.Motions;
import pns.visualisators.SegmentVisualisator;

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
    private SegmentVisualisator panelSpt;

    private Line line = new Line();

    private Light.Point center;

    private ConvertToSegment toSegment = ConvertToSegment.getInstance();
    private MotionTools motionTools = new MotionTools();
    private boolean isGoingRun = false;
    private Motions motions = Motions.getInstance();

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @FXML
    public void cycleRunFowardStop() {
        motionTools.setCycleRunFoward(!motionTools.isCycleRunFoward());
        stopCycle.setText("Resume");
        if (motionTools.isCycleRunFoward()) {
            cycleForward();
        }
    }

    @FXML
    public void cycleForward() {

        System.out.println("   FFFFFF     ----------> " + isGoingRun);
        if (!isGoingRun) {
            isGoingRun = true;
            prepareSegmentData();
            createSegmentVisual();

//            toolMethods.setCycleRunFoward(true);
//            toolMethods.setCycleRunBackword(false);
//            stopCycle.setText("Pause");
//            //createSegmentVisual();
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int k = 0; k < motionTools.getPoint9List().size() - 1; k++) {
                        updateProgress(k, 1000);
                        Thread.sleep(200);
                    }
                    isGoingRun = false;
                    System.out.println("  ++++++ fff = " + isGoingRun);
                    return null;
                }

                @Override

                protected void updateProgress(long workDone, long max) {
                    rotate(panelSpt, (int) workDone, true);
                    //toolMethods.drawSegmentForward();
                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
                }
            };

            (new Thread(task)).start();
        }
    }

    @FXML
    public void cycleBackward() {
        System.out.println("  BBBBBB     <<<-----------" + isGoingRun);
        if (!isGoingRun) {
            isGoingRun = true;
            prepareSegmentData();
            createSegmentVisual();

//            toolMethods.setCycleRunBackword(true);
//            toolMethods.setCycleRunFoward(false);
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int k = motionTools.getPoint9List().size() - 1; k > -1; k--) {
                        updateProgress(k, 1000);
                        Thread.sleep(200);
                    }
                    isGoingRun = false;
                    System.out.println("  ********** bbb = " + isGoingRun);
                    return null;
                }

                @Override

                protected void updateProgress(long workDone, long max) {
                    rotate(panelSpt, (int) workDone, false);
                    //toolMethods.drawSegmentForward();
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
        rotate(panelSpt, motionTools.getCurrFrame(), true);
        motionTools.setCurrFrame(motionTools.getCurrFrame() + 1);
//        toolMethods.drawSegmentForward();
    }

    @FXML
    public void backward() {
        System.out.println("   back ==========>>>");
        prepareSegmentData();
        createSegmentVisual();
//        if (motionTools.getCurrFrame() != motionTools.getPoint9List().size() - 1) {
//            motionTools.setCurrFrame(motionTools.getPoint9List().size() - 1);
//        }
        rotate(panelSpt, motionTools.getCurrFrame(), false);
        motionTools.setCurrFrame(motionTools.getCurrFrame() - 1);

        //    motionTools.drawSegmentBackward();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        putComponents();
        panelSpt = new SegmentVisualisator();
        panelSpt.setAngle(90);
        panelSpt.setId("spt");

        panelInfo = new AnchorPane();
        panelInfo.setId("info");
        motionTools.getText().setId("txt");
        motionTools.getText().setTranslateY(15);

        panel.setPrefSize(700, 600);
        center = new Light.Point(panel.getPrefWidth() / 2, panel.getPrefHeight() / 2, 2, Color.BISQUE);
        motionTools.getRotate().setPivotX(0);//Set the Pivot X to be the same location as the Circle X. This is only used to help you see the Pivot point
        motionTools.getRotate().setPivotY(0);//Set the Pivot Y to be the same location as the Ci
        motionTools.getRotate().setPivotZ(100);
        //panelSpt.getTransforms().add(motionTools.getRotate());

        panelSpt.setTranslateX(center.getX());
        panelSpt.setTranslateY(center.getY());

        motionTools.getRt().setNode(panelSpt);
        drawCoords();

    }

    public void putComponents() {

        panelSpt = new SegmentVisualisator();
        panelSpt.setAngle(90);
        panelSpt.setId("spt");

        panelInfo = new AnchorPane();
        panelInfo.setId("info");
        motionTools.getText().setId("txt");
        motionTools.getText().setTranslateY(15);

        panel.setPrefSize(700, 600);
        center = new Light.Point(panel.getPrefWidth() / 2, panel.getPrefHeight() / 2, 2, Color.BISQUE);
        motionTools.getRotate().setPivotX(0);//Set the Pivot X to be the same location as the Circle X. This is only used to help you see the Pivot point
        motionTools.getRotate().setPivotY(0);//Set the Pivot Y to be the same location as the Ci
        motionTools.getRotate().setPivotZ(100);
        //panelSpt.getTransforms().add(motionTools.getRotate());

        panelSpt.setTranslateX(center.getX());
        panelSpt.setTranslateY(center.getY());

    }

    private void drawCoords() {
        double x = 0;
        while (x <= panel.getPrefWidth()) {
            Line line = new Line(x, 0, x, panel.getPrefHeight());
            line.setStroke(Color.CYAN);
            line.setStrokeWidth(.25);
            x += 5;
            panel.getChildren().add(line);
        }
        double y = 0;
        while (y <= panel.getPrefWidth()) {
            Line line = new Line(0, y, panel.getPrefWidth(), y);
            line.setStroke(Color.CORAL);
            line.setStrokeWidth(.25);
            y += 5;
            panel.getChildren().add(line);
        }

    }

    private void createSegmentVisual() {
        if (!motionTools.isVisualizated()) {

            panelSpt.setColor(Color.CHOCOLATE);
            panelSpt.setLine(line);
            panelSpt.setRotate(90);

            panel.getChildren().remove(panelSpt);
            panel.getChildren().add(panelSpt);

            panel.getChildren().remove(panelInfo);
            panel.getChildren().add(panelInfo);

            panel.getChildren().remove(panelInfo);
            panel.getChildren().add(panelInfo);
            panelInfo.getChildren().remove(motionTools.getText());
            panelInfo.getChildren().add(motionTools.getText());

            motionTools.setVisualizated(true);            // transformInfoInitial();
        }
    }

//    private void transformInfoInitial() {
//
//        panelInfo.getChildren().remove(toolMethods.getText());
//        panelInfo.getChildren().add(toolMethods.getText());
//
//        panelInfo.getChildren().remove(toolMethods.getText());
//        panelInfo.getChildren().add(toolMethods.getText());
//
//        panel.getChildren().remove(panelSpt);
//        panel.getChildren().add(panelSpt);
//
//        panel.getChildren().remove(panelInfo);
//        panel.getChildren().add(panelInfo);
//    }
    /**
     * изготавливается набор координатных данных из файлы
     */
    private void prepareSegmentData() {
        motionTools.getPoint9List().clear();
        motionTools.getPoint9List().addAll(toSegment.getPoint9TreeSet());
    }

    private void rotate(SegmentVisualisator panelSpt, int k, boolean reverse) {
        if (motionTools.getPoint9List().size() > 1) {
            int kNext = (k + 1) % motionTools.getPoint9List().size();
            if (!reverse) {
                kNext = (k - 1) % motionTools.getPoint9List().size();
            }
            double from = motionTools.getPoint9List().get(k).getV1();
            double to = motionTools.getPoint9List().get(kNext).getV1();
            motionTools.getText().setText(" Frame: " + k + " Current Angle " + motionTools.getPoint9List().get(k).getX1() + " Current Speed " + motionTools.getPoint9List().get(k).getV1());
            System.out.print("     k= == " + k);
            motions.rotate(panelSpt, 210, 40, from, to, reverse);
        }
    }

}
