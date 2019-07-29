/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import pns.VidController.manparts.motions.MotionLegs;
import pns.api.mainClasses.Man;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class PatternLeg extends PatternDraw {

    protected DLimb LeftLeg;
    protected DLimb RightLeg;

    private Pane panel = new Pane();

    //  private Light.Point startPT = new Light.Point();
    public PatternLeg(Man man) {
        super(man);
        //theMan = man;

        patternLeg = new MotionLegs(man);
        LeftLeg = new DLimb();
        RightLeg = new DLimb();
    }

    public Pane getPanel() {
        return panel;
    }

    public void drawLegs(Light.Point pt) {
        LeftLeg.setZ(pt.getZ());

        LeftLeg.setAngle(0);
        LeftLeg.getTop().setAngle(90 + 35);
        LeftLeg.getTop().setAbsoluteAngle(90 + 40);
        LeftLeg.getBottom().setAngle(90 + 10);
        LeftLeg.getTop().setStroke(4);

        RightLeg.setAngle(0);
        RightLeg.getTop().setAngle(20);
        RightLeg.getBottom().setAngle(85);
        RightLeg.getTop().setStroke(3);

        LeftLeg.draw();

        RightLeg.draw();

        panel.getTransforms().add(new Translate(pt.getX(), pt.getY()));

        panel.getChildren().clear();
        panel.getChildren().add(RightLeg.getPanel());
        panel.getChildren().add(LeftLeg.getPanel());
    }

    protected void reDrawLegs(double d0, double d1) {
        LeftLeg.rotate(d0, d1);
    }

    public void setTotalRotationsLimb(double[] totalRotationsLimb) {
        this.totalRotationsLimb = totalRotationsLimb;

        RightLeg.setTotalRotationAngleTop(totalRotationsLimb[0]);
        RightLeg.setTotalRotationAngleButtom(totalRotationsLimb[1]);

        LeftLeg.setTotalRotationAngleTop(totalRotationsLimb[2]);
        LeftLeg.setTotalRotationAngleButtom(totalRotationsLimb[3]);
    }

    @Override
    public String toString() {
        return "PatternLeg{" + "LeftLeg=" + LeftLeg + ", RightLeg=" + RightLeg + '}';
    }

}
