/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.NoSuchElementException;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pns.VidController.manparts.motions.MotionBody;
import pns.api.mainClasses.Man;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternHead extends PatternDraw {

    protected Man theMan;
    protected MotionBody patternBody;//= new MotionBody();

    protected DSegment head;

    public PatternHead(Man man) {
        super();
        theMan = man;
        head = new DSegment();

        head.setIdNo(0);
        patternBody = new MotionBody(man);
    }

    public MotionBody getPatternBody() {
        return patternBody;
    }

    public Pane getPanel() {
        return panel;
    }

    public DSegment getHead() {
        return head;
    }

    public void setHead(DSegment head) {
        this.head = head;
    }

    public Light.Point drawHead(Light.Point pt) {
        head.setColor(Color.GREEN);
        head.setColorH(Color.BEIGE);

        head.setX(pt.getX());
        head.setY(pt.getY());
        head.setZ(pt.getZ());
        head.setAngle(90);

        panel.getChildren().clear();
        panel.getChildren().add(head.getPanel());
        panel.getChildren().add(patternBody.getPanel());

        // panel.setVisible(false);
        Light.Point ptt = head.draw();
        patternBody.drawBody(ptt);
        return ptt;

    }

    public void rotate(double dT) {
        //angle += dT;
        if (rotateT == null) {
            rotateT = new Rotate();
        }
        if (rotateTInv == null) {
            rotateTInv = new Rotate();
        }

        rotateT.setAngle(dT);
        rotateT.setPivotX(patternBody.getBody().getX());
        rotateT.setPivotY(patternBody.getBody().getY());
        if (panel.getTransforms().contains(rotateT)) {
            panel.getTransforms().remove(rotateT);
        }
        try {
            panel.getTransforms().add(rotateT);
        } catch (NoSuchElementException e) {
        }

        rotateTInv.setAngle(-dT);
        rotateTInv.setPivotX(patternBody.getBody().getX());
        rotateTInv.setPivotY(patternBody.getBody().getY());

        if (patternBody.getPanel().getTransforms().contains(rotateTInv)) {
            patternBody.getPanel().getTransforms().remove(rotateTInv);
        }
        try {
            patternBody.getPanel().getTransforms().add(rotateTInv);
        } catch (NoSuchElementException e) {
        }

    }

}
