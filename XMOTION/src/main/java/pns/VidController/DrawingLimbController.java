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
import pns.VidController.manparts.PatternBody;
import pns.VidController.manparts.PatternHand;
import pns.VidController.manparts.PatternLeg;
import pns.VidController.manparts.motions.MotionBody;
import pns.VidController.manparts.motions.MotionHands;
import pns.VidController.manparts.motions.MotionHead;
import pns.VidController.manparts.motions.MotionLegs;
import pns.datatools.ConvertToSegment;
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

    private Pane manPanel;
    private PatternHand patternHand;
    private MotionHead patternHead;
    private PatternLeg patternLeg;
    private PatternBody patternBody;

    Light.Point pt;
    private ConvertToSegment ctoSegment = ConvertToSegment.getInstance();

    private boolean manDrawn = false;
    private boolean headDrawn = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPanel.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        mainPanel.setPrefHeight(4 * Main.screenDimFind().getHeight() / 5);

        borderPanel.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        borderPanel.setPrefHeight(4 * Main.screenDimFind().getHeight() / 5);

        tooltop.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        tooltop.setPrefHeight(25);

        toolbottom.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        toolbottom.setPrefHeight(25);

        supportPanel.setPrefWidth(4 * Main.screenDimFind().getWidth() / 5);
        supportPanel.setPrefHeight(borderPanel.getPrefHeight() - toolbottom.getPrefHeight() - tooltop.getPrefHeight());

        putComponents();

        drawCoords();
        putComponents();
        drawMan();
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

    public void drawMan() {

        manDrawn = true;

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

}
