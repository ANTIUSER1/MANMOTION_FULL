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
import pns.api.mainClasses.Man;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternHead extends PatternDraw {

    private Pane panel;
    protected DSegment head;
    protected MotionBody patternBody;

    public PatternHead(Man man) {
        super(man);
        theMan = man;

        panel = new Pane();
        head = new DSegment();
        System.out.println("   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!!!!!!!!!!!!!");

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
        patternBody = new MotionBody(theMan);
        head.setColor(Color.GREEN);

        head.setColorH(Color.BEIGE);

        head.setX(pt.getX());
        head.setY(pt.getY());
        head.setZ(pt.getZ());
        head.setAngle(90);

        panel.getChildren().clear();
        panel.getChildren().add(head.getPanel());

        panel.getChildren().add(patternBody.getPanel());

        Light.Point ptt = head.draw();
//        patternBody.drawBody(ptt);
        return ptt;

    }

    public void rotate(double dT) {
        totalAngle += dT;
        Rotate rotateT = new Rotate();
        Rotate rotateTInv = new Rotate();

        rotateT.setPivotX(patternBody.getBody().getX());
        rotateT.setPivotY(patternBody.getBody().getY());
        rotateT.setAngle(totalAngle);
        panel.getTransforms().add(rotateT);

        rotateTInv.setAngle(-totalAngle);
        rotateTInv.setPivotX(patternBody.getBody().getX());
        rotateTInv.setPivotY(patternBody.getBody().getY());

//        patternBody.getPanel().getTransforms().clear();
        patternBody.getPanel().getTransforms().add(rotateTInv);
        System.out.println("   HEAD: " + dT);
    }

}
