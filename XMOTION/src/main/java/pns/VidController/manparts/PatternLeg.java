/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class PatternLeg extends PatternDraw {

    private DLimb LeftLeg;
    private DLimb RightLeg;

    public PatternLeg() {
        super();
        LeftLeg = new DLimb();
        RightLeg = new DLimb();
    }

    public Pane getPanel() {
        return panel;
    }

    public void drawLags(Light.Point pt) {
        System.out.println("    ------------------->>> <pt X   pt Y>    <" + pt.getX() + " , " + pt.getY() + ">");
        int[] left = {4, 5};
        int[] right = {6, 7};

        LeftLeg.setZ(pt.getZ());
        LeftLeg.setAngle(0);

        LeftLeg.setBottomLength(120);
        LeftLeg.setTopLength(80);
        LeftLeg.getTop().setAngle(90 + 40);
        LeftLeg.getBottom().setAngle(90 + 20);
        LeftLeg.getTop().setStroke(4);
        LeftLeg.getBottom().setTheta(20);

        RightLeg.setAngle(0);
        RightLeg.setTopLength(80);
        RightLeg.setBottomLength(150);
        RightLeg.getTop().setAngle(50);
        RightLeg.getBottom().setAngle(80);
        RightLeg.getTop().setStroke(3);
        RightLeg.getBottom().setTheta(5);

        LeftLeg.draw();
        RightLeg.draw();

        panel.getTransforms().add(new Translate(pt.getX(), pt.getY()));

        panel.getChildren().add(RightLeg.getPanel());
        panel.getChildren().add(LeftLeg.getPanel());
    }

}
