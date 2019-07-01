/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import pns.api.mainClasses.Point9;

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

    private Text text = new Text();
    private Pane panelInfo;
    private Pane panelSpt;

    private Rotate rotate = new Rotate();
    int currFrame, nextFrame = 0;
    private ConvertToSegment toSegment = ConvertToSegment.getInstance();
    private List<Point9> point9List = new ArrayList<Point9>();
    private Light.Point center;
    private boolean cycleRun = true;
    private boolean cycleRunFoward = true;
    private boolean cycleRunBackword = true;
    private Line line = new Line();

    @FXML
    public void cycleRunFowardStop() {
        cycleRunFoward = !cycleRunFoward;
    }

    @FXML
    public void cycleRunStop() {
        cycleRun = !cycleRun;
    }

    @FXML
    public void cycleRunBackwordStop() {
        cycleRunBackword = !cycleRunBackword;
    }

    @FXML
    public void cycleRunALLStop() {
        cycleRunBackword = false;
        cycleRunFoward = false;
        cycleRun = false;
    }

    @FXML
    public void cycleForward() {
        cycleRunFoward = true;
        //createSegmentVisual();
        forward();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                //  while (cycleRun) {
                System.out.println("NNNNNNNNNNN  " + point9List.size());
                for (int k = currFrame; k < point9List.size(); k++) {
                    System.out.println("  ++++++++++++++++>>> cycleRun  " + cycleRun);
                    if (cycleRunFoward) {
                        this.updateProgress(k, 1000);
                        //  System.out.println("k=   k   =  " + k);
                        Thread.sleep(1 * 500);
                    } //       }
                    System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  ");
                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                drawSegmentForward();
//                System.out.println("hhh  workDone " + workDone);

                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }
        };

        (new Thread(task)).start();
        cycleRunFoward = false;
    }

    @FXML
    public void cycleBackward() {
        cycleRun = true;
        forward();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                //      while (cycleRun) {
                System.out.println("NNNNNNNNNNN  " + point9List.size());
                for (int k = 0; k < point9List.size(); k++) {
                    if (cycleRun) {
                        this.updateProgress(k, 1000);
                        System.out.println("k=   k   =  " + k);
                        Thread.sleep(1 * 700);
                    }
                }
                System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  ");
                //      }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                drawSegmentBackward();
//                System.out.println("hhh  workDone " + workDone);

                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }
        };

        (new Thread(task)).start();
    }

    @FXML
    public void forward() {
        prepareSegmentData();
        createSegmentVisual();
        drawSegmentForward();

    }

    @FXML
    public void backward() {
        prepareSegmentData();
        createSegmentVisual();
        drawSegmentBackward();

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
        text.setId("txt");
        text.setTranslateY(15);

        panel.setPrefSize(700, 600);
        center = new Light.Point(panel.getPrefWidth() / 2, panel.getPrefHeight() / 2, 2, Color.CORAL);
        rotate.setPivotX(0);//Set the Pivot X to be the same location as the Circle X. This is only used to help you see the Pivot point
        rotate.setPivotY(0);//Set the Pivot Y to be the same location as the Ci
        panelSpt.getTransforms().add(rotate);

        panelSpt.setTranslateX(center.getX());
        panelSpt.setTranslateY(center.getY());

        drawCoords();

    }
    private double angle = 0;

    private void drawSegmentForward() {
        if (point9List.size() > 1) {

            currFrame++;
            currFrame = currFrame % point9List.size();

            nextFrame = (currFrame + 1) % point9List.size();

            Point9 pt90 = point9List.get(currFrame);
            Point9 pt91 = point9List.get(nextFrame);
            angle = pt91.getV1() - pt90.getV1();
            angle = pt90.getV1();
            rotate.setAngle(angle);
            System.out.println(currFrame + "   " + nextFrame + "     angle " + angle + "  rotate.getAngle() " + rotate.getAngle());

            text.setText(nextFrame + "   " + pt90.getMoment() + " :    " + pt90.getX1() + " ,    angle " + angle + "    rotate.getAngle() " + rotate.getAngle());
        }
    }

    private void drawSegmentBackward() {

        if (point9List.size() > 1) {
            currFrame--;
            if (currFrame < 0) {
                currFrame = point9List.size() + currFrame;
            }
            nextFrame = (currFrame - 1) % point9List.size();
            if (nextFrame < 0) {
                nextFrame = point9List.size() + nextFrame;
            }

            System.out.println("  " + currFrame + "   " + nextFrame);

            Point9 pt90 = point9List.get(currFrame);
            Point9 pt91 = point9List.get(nextFrame);
            angle = pt91.getV1() - pt90.getV1();
            angle = pt90.getV1();
            rotate.setAngle(angle);
            System.out.println(currFrame + "   " + nextFrame + "     angle " + angle + "  rotate.getAngle() " + rotate.getAngle());
            text.setText(nextFrame + "   " + pt90.getMoment() + " :    " + pt90.getX1() + " ,    angle " + angle + "    rotate.getAngle() " + rotate.getAngle());
        }

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
        line.setEndY(100);
        line.setFill(null);
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        panelSpt.getChildren().remove(line);
        panelSpt.getChildren().add(line);
        transformInfoInitial();
    }

    private void transformInfoInitial() {

//        panelSpt.getTransforms().add(rotate);
//
//        panelSpt.setTranslateX(center.getX());
//        panelSpt.setTranslateY(center.getY());
        panelInfo.getChildren().remove(text);
        panelInfo.getChildren().add(text);

        panelInfo.getChildren().remove(text);
        panelInfo.getChildren().add(text);

        panel.getChildren().remove(panelSpt);
        panel.getChildren().add(panelSpt);

        panel.getChildren().remove(panelInfo);
        panel.getChildren().add(panelInfo);
    }

    private void prepareSegmentData() {
        point9List.clear();
        point9List.addAll(toSegment.getPoint9TreeSet());
    }

}
