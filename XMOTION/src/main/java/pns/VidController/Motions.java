/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController;

import javafx.animation.RotateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Movement
 */
public class Motions {

    private static Motions instance;
    boolean stopped = false;
    boolean paused = false;

    private RotateTransition rotateTransition = new RotateTransition();

    public static Motions getInstance() {
        if (instance == null) {
            instance = new Motions();
        }
        return instance;
    }

    private Motions() {
    }

    public boolean isStopped() {
        return stopped;
    }

    public boolean isPaused() {
        return paused;
    }

    public void rotate(Pane pane, double millisec, double rate, double fromAngle, double toAngle, boolean autoreverse) {
        stopped = paused = false;
        //Setting the duration for the transition
        rotateTransition.setDuration(Duration.millis(millisec));

        //Setting the node for the transition
        rotateTransition.setNode(pane);

        //Setting the angle of the rotation
        rotateTransition.setByAngle(360);

        //Setting the cycle count for the transition
        rotateTransition.setCycleCount(1);

        //Set Angles from & to
        rotateTransition.setFromAngle(fromAngle);
        rotateTransition.setToAngle(toAngle);
        // System.out.println("   from:   " + fromAngle + "   to:    " + toAngle + "     millisec: " + millisec);

        //Setting auto reverse value to false
        rotateTransition.setAutoReverse(autoreverse);

        rotateTransition.setRate(rate);

        //Playing the animation
        rotateTransition.play();

    }

    public void stop() {
        rotateTransition.stop();
    }

    public void pause() {
        rotateTransition.pause();
    }

}
