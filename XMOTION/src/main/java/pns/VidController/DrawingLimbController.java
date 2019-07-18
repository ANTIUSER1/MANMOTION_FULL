/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Light;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pns.VidController.manparts.motions.MotionBody;
import pns.VidController.manparts.motions.MotionHands;
import pns.VidController.manparts.motions.MotionHead;
import pns.VidController.manparts.motions.MotionLegs;
import pns.datatools.ConvertToLegs;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.start.Main;

/**
 * FXML Controller class
 *
 * @author Movement
 */
public class DrawingLimbController implements Initializable {

    @FXML
    private AnchorPane mainPanel;
    @FXML
    private Pane supportPanel;
    @FXML
    private BorderPane borderPanel;
    @FXML
    private HBox tooltop;
    @FXML
    private HBox toolbottom;

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan = ConvertToMan.getInstance();
    private ConvertToLegs ctoLegs;

    private Pane manPanel;
    private MotionHands patternHand;
    private MotionHead patternHead;
    private MotionLegs patternLeg;
    private MotionBody patternBody;

    private Light.Point pt;

    private boolean openned = false;
    private boolean goFoward = false;
    private boolean goBack = false;

    private String data = "";

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("INITIALIZE!");
        mainPanel.setPrefWidth(0.55 * Main.screenDimFind().getWidth());
        mainPanel.setPrefHeight(6 * Main.screenDimFind().getHeight() / 7);

        borderPanel.setPrefWidth(0.55 * Main.screenDimFind().getWidth());
        borderPanel.setPrefHeight(6 * Main.screenDimFind().getHeight() / 7);

        tooltop.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        tooltop.setPrefHeight(25);

        toolbottom.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        toolbottom.setPrefHeight(25);

        supportPanel.setPrefWidth(0.55 * Main.screenDimFind().getWidth());
        supportPanel.setPrefHeight(borderPanel.getPrefHeight() - toolbottom.getPrefHeight() - tooltop.getPrefHeight());

        putComponents();

    }

    @FXML
    public void showMan() {
        patternHead.getPanel().setVisible(true);
        patternBody.getPanel().setVisible(true);
        patternHand.getPanel().setVisible(true);
        patternLeg.getPanel().setVisible(true);

    }

    @FXML
    public void showHead() {
        patternHead.getPanel().setVisible(true);
        patternBody.getPanel().setVisible(false);
        patternHand.getPanel().setVisible(false);
        patternLeg.getPanel().setVisible(false);
    }

    @FXML
    public void showBody() {
        patternHead.getPanel().setVisible(false);
        patternBody.getPanel().setVisible(true);
        patternHand.getPanel().setVisible(false);
        patternLeg.getPanel().setVisible(false);
    }

    @FXML
    public void showHands() {
        patternHead.getPanel().setVisible(false);
        patternBody.getPanel().setVisible(false);
        patternHand.getPanel().setVisible(true);
        patternLeg.getPanel().setVisible(false);
    }

    @FXML
    public void showLegs() {
        patternHead.getPanel().setVisible(false);
        patternBody.getPanel().setVisible(false);
        patternHand.getPanel().setVisible(false);
        patternLeg.getPanel().setVisible(true);
    }

    @FXML
    public void recieveData() {

        if (!openned) {
            openned = true;
            drawCoords();
            putComponents();
            drawMan();

            if (ctoMan.getMan() == null) {
                System.out.println("    ctoMan.hashCode()   " + ctoMan.hashCode());
                ctoMan.convert(dataReciever.getData());
//         }
            }
        }
    }

    @FXML
    public void forward() {
        goFoward = true;
        goBack = false;

        motion();
    }

    @FXML
    public void backward() {
        goFoward = false;
        goBack = true;

        motion();
    }

    private void drawMan() {

        pt = patternHead.drawHead(pt);
        supportPanel.getChildren().add(patternHead.getPanel());

        patternHand.drawHands(pt);
        supportPanel.getChildren().add(patternHand.getPanel());

        pt = patternBody.drawBody(pt);
        supportPanel.getChildren().add(patternBody.getPanel());
        patternLeg.drawLegs(pt);
        supportPanel.getChildren().add(patternLeg.getPanel());

    }

    private void putComponents() {
        patternBody = new MotionBody();
        patternHand = new MotionHands();
        patternHead = new MotionHead();
        patternLeg = new MotionLegs();

        manPanel = new Pane();
        manPanel.getChildren().clear();

        pt = new Light.Point();
        pt.setX(supportPanel.getPrefWidth() / 2);
        pt.setY(20);
        pt.setZ(0);
    }

    private void drawCoords() {
        System.out.println(" supportPanel.getPrefWidth() " + supportPanel.getPrefWidth());
        double x = 0;
        while (x <= mainPanel.getPrefWidth()) {
            Line line = new Line(x, 0, x, mainPanel.getPrefHeight());
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.22);
            x += 5;
            mainPanel.getChildren().add(line);
        }
        double y = 0;
        while (y <= supportPanel.getPrefHeight()) {
            Line line = new Line(0, y, mainPanel.getPrefWidth(), y);
            line.setStroke(Color.CHOCOLATE);
            line.setStrokeWidth(.22);
            y += 5;
            mainPanel.getChildren().add(line);
        }

    }

    private void motionBody() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternBody.motionFoward();
            } else if (goBack) {
                patternBody.motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHands() {
        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHand.motionFoward();
            } else if (goBack) {
                patternHand.motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionHead() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternHead.motionFoward();
            } else if (goBack) {
                patternHead.motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motionLegs() {

        if (!(goFoward && goBack)) {
            if (goFoward) {
                patternLeg.motionFoward();
            } else if (goBack) {
                patternLeg.motionBackward();
            }
        } else {
            goFoward = goBack = false;
        }
    }

    private void motion() {

        if (patternHead.getPanel().isVisible()) {
            // motion head
            motionHead();
        }
        if (patternBody.getPanel().isVisible()) {
            // motion body
            motionBody();
        }
        if (patternHand.getPanel().isVisible()) {
            // motion hands
            motionHands();
        }
        if (patternLeg.getPanel().isVisible()) {
            // motion legs
            motionLegs();
        }
    }

}
