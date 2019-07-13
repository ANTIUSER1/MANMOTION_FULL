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
    private Pane panelMan;
    @FXML
    private Pane panelBody;
    @FXML
    private Pane panelHands;
    @FXML
    private Pane panelLegs;
    @FXML
    private Pane panelHead;

//    @FXML
//    private Button stopCycle;
    private Pane supportPanel;
    private PatternHand patternHand;
    private PatternHead patternHead;
    private PatternLeg patternLeg;
    private PatternBody patternBody;

    private boolean manDrawn = false;
    Light.Point pt;
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

        putComponents();

        drawCoords(panelMan);
        drawCoords(panelHands);
        drawCoords(panelBody);
        drawCoords(panelLegs);
        drawCoords(panelHead);

    }

    @FXML
    public void drawMan() {
        putComponents();
        System.out.println("SSSSSSSSSSSSSSSSSS22222222");
        if (!manDrawn) {
            manDrawn = true;

            supportPanel.getChildren().clear();

            pt = patternHead.drawHead(pt);
            supportPanel.getChildren().add(patternHead.getPanel());

            System.out.println("  pt Y " + pt.getX() + "   " + pt.getY());
            patternHand.drawHands(pt);
            supportPanel.getChildren().add(patternHand.getPanel());

            pt = patternBody.drawBody(pt);
            supportPanel.getChildren().add(patternBody.getPanel());

            patternLeg.drawLags(pt);
            supportPanel.getChildren().add(patternLeg.getPanel());

//***************
            panelMan.getChildren().add(supportPanel);
        }
    }

    @FXML
    public void drawLegs() {
        if (!manDrawn) {
            manDrawn = true;

            supportPanel.getChildren().clear();

            patternLeg.drawLags(pt);
            supportPanel.getChildren().add(patternLeg.getPanel());

//***************
            panelLegs.getChildren().add(supportPanel);
        }
    }

    @FXML
    public void drawBody() {
        if (!manDrawn) {
            manDrawn = true;

            supportPanel.getChildren().clear();

            pt = patternBody.drawBody(pt);
            supportPanel.getChildren().add(patternBody.getPanel());

//***************
            panelBody.getChildren().add(supportPanel);
        }
    }

    @FXML
    public void drawHands() {
        if (!manDrawn) {
            manDrawn = true;

            supportPanel.getChildren().clear();
            patternHand.drawHands(pt);
            supportPanel.getChildren().add(patternHand.getPanel());

//***************
            panelHands.getChildren().add(supportPanel);
        }
    }

    @FXML
    public void drawHead() {
        if (!manDrawn) {
            manDrawn = true;

            supportPanel.getChildren().clear();
            pt = patternHead.drawHead(pt);
            supportPanel.getChildren().add(patternHead.getPanel());

//***************
            panelHead.getChildren().add(supportPanel);
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

        if (panelMan == null) {
            panelMan.setPrefSize(900, 820);
        }
        if (panelBody == null) {
            panelBody.setPrefSize(900, 820);
        }
        if (panelHands == null) {
            panelHands.setPrefSize(900, 820);
        }
        if (panelLegs == null) {
            panelLegs.setPrefSize(900, 820);
        }
        if (panelHead == null) {
            panelHead.setPrefSize(900, 820);
        }

    }

    private void drawCoords(Pane panel) {
        double x = 0;
        System.out.println("PPP   ----PPPPPPPPPP " + (panel == null));
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
