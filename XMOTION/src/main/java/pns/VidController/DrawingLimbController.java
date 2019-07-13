/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Light;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pns.VidController.manparts.PatternBody;
import pns.VidController.manparts.PatternHand;
import pns.VidController.manparts.PatternHead;
import pns.VidController.manparts.PatternLeg;
import pns.datatools.ConvertToSegment;
import pns.datatools.DataReceiver;

/**
 * FXML Controller class
 *
 * @author Movement
 */
public class DrawingLimbController implements Initializable {

    @FXML
    private VBox spt;
    @FXML
    private HBox tools;
    @FXML
    private Pane panel;
//    @FXML
//    private Button stopCycle;

    private Pane supportPanel;
    private PatternHand patternHand;
    private PatternHead patternHead;
    private PatternLeg patternLeg;
    private PatternBody patternBody;

    private boolean manDrawn = false;

    //*****************************
    private Light.Point center;

    private DataReceiver dataReceiver = DataReceiver.getInstance();

    private ConvertToSegment ctoSegment = ConvertToSegment.getInstance();
    private boolean isGoingRun = false;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panel.setPrefSize(900, 820);
        supportPanel = new Pane();

        patternHand = new PatternHand();
        patternHead = new PatternHead();
        patternLeg = new PatternLeg();
        patternBody = new PatternBody();

        putComponents();

        drawCoords();

    }

    @FXML
    public void drawMan() {
        if (!manDrawn) {
            manDrawn = true;
            supportPanel.getChildren().clear();
            Light.Point pt = new Light.Point(panel.getPrefWidth() / 2, 10, 0, Color.CORAL);
            pt = patternHead.drawHead(pt);
            supportPanel.getChildren().add(patternHead.getPanel());

            System.out.println("  pt Y " + pt.getX() + "   " + pt.getY());
            patternHand.drawHands(pt);
            supportPanel.getChildren().add(patternHand.getPanel());

            pt = patternBody.drawBody(pt);
            supportPanel.getChildren().add(patternBody.getPanel());

            patternLeg.drawLagss(pt);
            supportPanel.getChildren().add(patternLeg.getPanel());

//***************
            panel.getChildren().add(supportPanel);
        }
    }

    @FXML
    public void cycleRunFowardStop() {
//        motionTools.setCycleRunFoward(!motionTools.isCycleRunFoward());
//        //stopCycle.setText("Resume");
//        if (motionTools.isCycleRunFoward()) {
//            cycleForward();
//        }
    }

    @FXML
    public void cycleForward() {

//        System.out.println("   FFFFFF     ----------> " + isGoingRun);
//        if (!isGoingRun) {
//            isGoingRun = true;
//
//            dataReceiver.prepareData();
//            createSegmentVisual();
//
//            task = new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
//                    int k = 0;
//                    while (k < dataReceiver.getPoint9List().size() && motionTools.isCycleRunFoward()) {
//                        updateProgress(k, 1000);
//                        Thread.sleep(200);
//                        k++;
//                    }
//                    isGoingRun = false;
//                    return null;
//                }
//
//                @Override
//
//                protected void updateProgress(long workDone, long max) {
//                    panelSpt.rotate((int) workDone, true);
//                    //toolMethods.drawSegmentForward();
//                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
//                }
//            };
//
//            (new Thread(task)).start();
//        }
    }

    @FXML
    public void cycleBackward() {
//        System.out.println("  BBBBBB     <<<-----------" + isGoingRun);
//        if (!isGoingRun) {
//            isGoingRun = true;
//
//            dataReceiver.prepareData();
//            createSegmentVisual();
//
//            task = new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
//                    int k = dataReceiver.getPoint9List().size() - 1;
//                    while (k > -1 && motionTools.isCycleRunFoward()) {
//                        updateProgress(k, 1000);
//                        Thread.sleep(200);
//                        k--;
//                    }
//                    isGoingRun = false;
//
//                    return null;
//                }
//
//                @Override
//                protected void updateProgress(long workDone, long max) {
//                    System.out.println(workDone + " ************  workDone   " + workDone + "     (int) workDone   " + (int) workDone);
//                    panelSpt.rotate((int) workDone, false);
//                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
//                }
//            };
//
//            (new Thread(task)).start();
//        }
    }

    @FXML
    public void forward() {
//        dataReceiver.prepareData();
//        createSegmentVisual();
//        panelSpt.rotate(motionTools.getCurrFrame(), true);
//        motionTools.setCurrFrame(motionTools.getCurrFrame() + 1);
////        toolMethods.drawSegmentForward();
    }

    @FXML
    public void backward() {
//        dataReceiver.prepareData();
//        createSegmentVisual();
//        panelSpt.rotate(motionTools.getCurrFrame(), false);
//        motionTools.setCurrFrame(motionTools.getCurrFrame() - 1);
//
        //    motionTools.drawSegmentBackward();
    }

    public void putComponents() {

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
            line.setStrokeWidth(.15);
            y += 5;
            panel.getChildren().add(line);
        }

    }

    private void createSegmentVisual() {
        // createSegmentVisualOnlyOne();
        //    createSegmentVisualLimb();

    }

    private void createSegmentVisualLimb() {

    }

}
