/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import pns.VidController.manparts.motions.MotionHands;
import pns.api.mainClasses.Man;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class PatternHand extends PatternDraw {

    private Pane panel = new Pane();
    protected DLimb RightHand;
    protected DLimb LeftHand;

    public PatternHand(Man man) {
        super(man);
        //  theMan = man;

        patternHand = new MotionHands(man);
        LeftHand = new DLimb();
        RightHand = new DLimb();
    }

    public void setTotalRotationsLimb(double[] totalRotationsLimb) {
        this.totalRotationsLimb = totalRotationsLimb;

        RightHand.setTotalRotationAngleTop(totalRotationsLimb[0]);
        RightHand.setTotalRotationAngleButtom(totalRotationsLimb[1]);

        LeftHand.setTotalRotationAngleTop(totalRotationsLimb[2]);
        LeftHand.setTotalRotationAngleButtom(totalRotationsLimb[3]);
    }

    public DLimb getRightHand() {
        return RightHand;
    }

    public DLimb getLeftHand() {
        return LeftHand;
    }

    public Pane getPanel() {
        return panel;
    }

    public void drawHands(Light.Point pt) {

        LeftHand.setZ(pt.getZ());
        LeftHand.setAngle(0);

        LeftHand.getTop().setAngle(90 + 45);
        LeftHand.getBottom().setAngle(90 + 30);

        RightHand.setAngle(0);
        RightHand.getTop().setAngle(30);
        RightHand.getBottom().setAngle(60);

        LeftHand.draw();
        RightHand.draw();

        panel.getChildren().clear();
        panel.getTransforms().add(new Translate(pt.getX(), pt.getY()));

        panel.getChildren().add(RightHand.getPanel());
        panel.getChildren().add(LeftHand.getPanel());

    }

}
