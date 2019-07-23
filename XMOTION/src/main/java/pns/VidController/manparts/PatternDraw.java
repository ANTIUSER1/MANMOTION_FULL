/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Movement
 */
public class PatternDraw {

    protected Pane panel;

    protected double totalAngle = 0;
    protected Rotate rotateT = new Rotate();

    protected boolean isPaused = false;

    public PatternDraw() {
        panel = new Pane();
    }

    protected void swapPause() {
        isPaused = !isPaused;
    }
}
