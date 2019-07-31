/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pns.VidController.manparts.motions.MotionHands;
import pns.VidController.manparts.motions.MotionLegs;
import pns.api.mainClasses.Man;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternBody extends PatternDraw {

    protected Man theMan;

    protected DSegment body;
    protected MotionLegs patternLeg;// = MotionLegs.getInstance();
    protected MotionHands patternHand;//= MotionHands.getInstance();
    private Pane LegsPlane = new Pane();
    private Pane HandsPlane = new Pane();

    public PatternBody(Man man) {
        super();
        theMan = man;
        body = new DSegment();
        body.setRadius(2);
        body.setLength(150);

        patternHand = new MotionHands(man);
        patternLeg = new MotionLegs(man);
        panel.getChildren().clear();
        LegsPlane.getChildren().clear();
        LegsPlane.getChildren().add(patternLeg.getPanel());
        HandsPlane.getChildren().clear();
        HandsPlane.getChildren().add(patternHand.getPanel());
        body.getPanel().getChildren().add(HandsPlane);
        body.getPanel().getChildren().add(LegsPlane);

        panel.getChildren().add(body.getPanel());

    }

    public Pane getPanel() {
        return panel;
    }

    public MotionLegs getPatternLeg() {
        return patternLeg;
    }

    public MotionHands getPatternHand() {
        return patternHand;
    }

    public Light.Point drawBody(Light.Point pt) {

        body.setColor(Color.GREEN);
        body.setColorH(Color.AZURE);
        body.setStroke(5);

        body.setX(pt.getX());
        body.setY(pt.getY());
        body.setZ(pt.getZ());
        body.setAngle(90);
        body.setIdNo(1);

        panel.getChildren().clear();
        panel.getChildren().add(body.getPanel());
        panel.getChildren().add(patternLeg.getPanel());
        panel.getChildren().add(patternHand.getPanel());
        //    panel.setVisible(false);

        patternHand.drawHands(new Light.Point(body.getX(), body.getY(), body.getZ(), Color.CORAL));
        Light.Point ptt = new Light.Point();
        ptt.setX(body.getX());
        ptt.setY(body.getLength() + body.getY());
        patternLeg.drawLegs(ptt);
        return body.draw();
    }

    public DSegment getBody() {
        return body;
    }

    protected double totalAngle = 0;

    public void rotate(double dT) {
        totalAngle += dT;

        rotateT = new Rotate();
        rotateTInv = new Rotate();

        rotateT.setAngle(dT);
        rotateT.setPivotX(body.getX());
        rotateT.setPivotY(body.getY());

        panel.getTransforms().add(rotateT);

        rotateTInv.setAngle(-dT);

        HandsPlane.getTransforms().add(rotateTInv);
        LegsPlane.getTransforms().add(rotateTInv);

    }

    @Override
    public String toString() {
        return "PatternBody{" + "body=" + body + ", patternLeg=" + patternLeg + ", patternHand=" + patternHand + '}';
    }

}
