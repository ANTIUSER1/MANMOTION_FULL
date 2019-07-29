/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternBody;
import pns.api.mainClasses.Man;
import pns.datatools.ConvertToBody;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionBody extends PatternBody implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToBody ctoBody;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public MotionBody(Man man) {
        super(man);
        System.out.println("BBB DDDD !!!");
    }

    /**
     * стартовое значение угла
     */
    private double dT = 0;

    @Override
    public void motionFoward() {

        task = null;
        task = new Task<Void>() {
            int step = startStep;

            @Override
            protected Void call() throws Exception {

                for (step = startStep; step < mover.size(); step++) {
                    updateProgress(Main.timeout, 1000);

                    if (!highSpeed) {
                        Thread.sleep(Main.timeout);
                    } else {
                        Thread.sleep(properTimeout);
                    }
                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                try {
                    goStepForward(step);
                } catch (Exception ee) {
                }
            }

        ;

        }

    ;        (new Thread(task)).start();

    }

    @Override
    public void removePauseFoward() {

    }

    @Override
    public void motionPause() {

    }

    @Override
    public void toStart() throws Exception {
    }

    @Override
    public void toEnd() throws Exception {

    }

    @Override
    public void stepForward(int currFrame) throws Exception {

    }

    @Override
    public void stepBackward(int currFrame) throws Exception {

    }

    private void goStepForward(int frame) throws Exception {
        rotateInstance(frame);
    }

    private void rotateInstance(int frame) throws Exception {
        if (frame > -1 && frame < mover.size()) {
            dT = mover.get(frame).getFixedPoint().getV1();
            rotate(dT);
        }
    }

}
