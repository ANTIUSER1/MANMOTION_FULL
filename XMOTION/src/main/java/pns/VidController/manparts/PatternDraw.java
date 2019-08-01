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

    protected Pane panel;

    public PatternDraw() {
        panel = new Pane();
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
