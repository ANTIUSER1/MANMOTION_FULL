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
public class PatternHand extends PatternDraw {

    protected DLimb RightHand;
    protected DLimb LeftHand;

    public PatternHand() {
        super();
        RightHand = new DLimb();
        LeftHand = new DLimb();
        RightHand.setTopLength(80);
        RightHand.setBottomLength(150);
        LeftHand.setBottomLength(120);
        LeftHand.setTopLength(80);

    }

    public Pane getPanel() {
        return panel;
    }

    public void drawHands(Light.Point pt) {
        System.out.println("    ------------------->>> <pt X   pt Y>    <" + pt.getX() + " , " + pt.getY() + ">");
        int[] left = {2, 3};
        int[] right = {4, 5};

        LeftHand.setZ(pt.getZ());
        LeftHand.setAngle(0);
        LeftHand.setIdNo(left);

        LeftHand.getTop().setAngle(90 + 40);
        LeftHand.getBottom().setAngle(90 + 20);

        RightHand.setAngle(0);
        RightHand.setIdNo(right);

        RightHand.getTop().setAngle(30);
        RightHand.getBottom().setAngle(70);

        LeftHand.draw();
        RightHand.draw();

        panel.getChildren().clear();
        panel.getTransforms().add(new Translate(pt.getX(), pt.getY()));

        panel.getChildren().add(RightHand.getPanel());
        panel.getChildren().add(LeftHand.getPanel());
        panel.setVisible(false);
    }

}
