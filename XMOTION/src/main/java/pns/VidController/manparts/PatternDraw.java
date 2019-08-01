/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 *
 * @author Movement
 */
public class PatternDraw {

    protected int stepFrom = 0;
    protected int stepByStep = 0;

    protected Pane panel;

    protected double totalAngle = 0;
    protected Rotate rotateT = new Rotate();
    protected Rotate rotateTInv = new Rotate();

    protected boolean isPausedForward = false;
    protected boolean isPausedBackward = false;

    public PatternDraw() {
        panel = new Pane();
    }

    public void setIsPausedForward(boolean isPausedForward) {
        this.isPausedForward = isPausedForward;
    }

    public void setIsPausedBackward(boolean isPausedBackward) {
        this.isPausedBackward = isPausedBackward;
    }

    protected double getTotalRotationsOfPane(Pane pan) {

        double res = 0;
        for (Transform tr : pan.getTransforms()) {
            if (tr instanceof Rotate) {
                Rotate ttr = (Rotate) tr;
                res += ttr.getAngle();
            }
        }
        return res;
    }
}
