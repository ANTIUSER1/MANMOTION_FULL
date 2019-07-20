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
import pns.VidController.manparts.motions.MotionBody;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternHead extends PatternDraw {

    private MotionBody patternBody = new MotionBody();

    protected DSegment head;

    public PatternHead() {
        super();
        head = new DSegment();
        head.setIdNo(0);
        patternBody = new MotionBody();
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
        Rotate rotateT = new Rotate();
        rotateT.setAngle(dT);

        rotateT.setPivotX(patternBody.getBody().getX());
        rotateT.setPivotY(patternBody.getBody().getY());
        panel.getTransforms().add(rotateT);

    }

}
