/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class PatternMotion implements IMotion {

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    @Override
    public void motionFoward() {

    }

    @Override
    public void motionPause() {

    }

    @Override
    public void toStart() {

    }

    @Override
    public void toEnd() {

    }

    @Override
    public void removePauseFoward() {

    }

    @Override
    public void stepForward(int currFrame) throws Exception {

    }

    @Override
    public void stepBackward(int currFrame) throws Exception {

    }

}
