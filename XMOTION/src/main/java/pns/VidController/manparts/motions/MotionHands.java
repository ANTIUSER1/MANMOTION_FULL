/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class MotionHands extends PatternHand implements IMotion {

    private boolean isGoingRun = false;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void motionFoward() {

    }

    @Override
    public void motionBackward() {

    }

}
