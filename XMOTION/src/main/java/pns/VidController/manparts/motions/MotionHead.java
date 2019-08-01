/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import java.util.List;
import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHead;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.api.utils.SizePositionUtils;
import pns.datatools.ConvertToHead;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DSegment;
import pns.interfaces.IMotion;
import pns.start.Main;
import utils.log. LoggerCS;

/**
 *
 * @author Movement
 */
public class MotionHead extends PatternHead implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHead ctoHead;
    private DSegment limb;
    private List<Segment> mover;
    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionHead(Man man) {
        super(man);
        ctoHead = ConvertToHead.getInstance(man);
        limb = ctoHead.getLimb();
        mover = SizePositionUtils.settolist(limb.getMoverSet());
    }

    /**
     * стартовое значение угла
     */
    private double dT = 0;

    @Override
    public void motionFoward() {
        isPausedForward = false;
        //    System.out.println(" head  mover  " + mover.size());
        task = new Task<Void>() {
            int step = stepFrom;

            @Override
            protected Void call() throws Exception {

                for (step = stepFrom; step < mover.size() && !isPausedForward; step++) {
                    stepFrom = step;
                    stepByStep = step;
                    updateProgress(Main.timeout, 1000);
                    Thread.sleep(Main.timeout);
                    if (step == 0) {
                        Thread.sleep(Main.timeout * 5);
                    }

                }
                System.out.println("done!!!");
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                goStepForward(step);
                // System.out.println(" head-step " + step);
            }
        };
        (new Thread(task)).start();
    }

    @Override
    public void motionPause() {
        isPausedForward = true;
        patternBody.motionPause();
        // LoggerCS.logInfo(" Head Paused Move . on Step:" + stepFrom);
    }

    @Override
    public void removePauseFoward() {
        isPausedForward = false;
        // LoggerCS.logInfo(" Head Paused Removed Move . on Step:" + stepFrom);
    }

    @Override
    public void toStart() {
        stepFrom = 0;
        stepByStep = 0;
        panel.getTransforms().clear();
    }

    @Override
    public void toEnd() {
        goStepForward(stepByStep);
        stepByStep++;
        stepFrom = stepByStep;
        // LoggerCS.logInfo(" Head Paused Move . Step by Step:" + stepFrom);
    }

    private void rotateInstance(int frame) {

        try {
            dT = mover.get(frame).getFixedPoint().getV1();
            rotate(dT);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void goStepForward(int frame) {
        rotateInstance(frame);
        // LoggerCS.logInfo(" Head Move . Step:" + frame);
    }

}
