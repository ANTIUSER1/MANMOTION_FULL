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

/**
 *
 * @author Movement
 */
public class PatternDraw {

    protected Pane panel;

    public PatternDraw() {
        panel = new Pane();
    }

    public void moveTo(Light.Point npt) {

        System.out.println("     npt      ( " + npt.getX() + " ;  " + npt.getY() + "  ;   " + npt.getZ() + "  )");

        Translate t = new Translate(npt.getX(), npt.getY());
        if (this instanceof MotionLegs) {
            MotionLegs ML = MotionLegs.getInstance();
            ML.panel.getTransforms().add(t);

        }
    }
}
