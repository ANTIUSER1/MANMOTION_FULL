/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternLegs;
import pns.api.mainClasses.Man;
import pns.api.utils.SizePositionUtils;
import pns.datatools.ConvertToLegs;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DLimb;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionLegs extends PatternLegs implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();
    private int id = 0;
    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToLegs ctoLegs;
    private DLimb[] limbs;

    private static MotionLegs instance;

    public MotionLegs(Man man) {
        super(man);
        id = pns.utils.numbers.RInts.rndInt(50000, 99999);

        ctoLegs = new ConvertToLegs(man);
        limbs = ctoLegs.getLimbs();

        topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());

    }

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void motionFoward() {
        isPausedForward = false;

        task = new Task<Void>() {
            int step = stepFrom;

            @Override
            protected Void call() throws Exception {
                for (step = stepFrom; step < limbs[0].getSegmentSetBottom().size()
                        && step < limbs[0].getSegmentSetTop().size()
                        && step < limbs[1].getSegmentSetBottom().size()
                        && step < limbs[1].getSegmentSetTop().size()
                        && !isPausedForward; step++) {
                    stepFrom = step;
                    stepByStep = step;
                    updateProgress(Main.timeout, 1000);
                    Thread.sleep(Main.timeout);
                    if (step == 0) {
                        Thread.sleep(Main.timeout * 5);
                    }
                }
                System.out.println("done!");
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                goStepForward(step);
                //    System.out.println(" legs-step " + step);
            }

        };

        (new Thread(task)).start();
    }

    @Override
    public void motionPause() {
        isPausedForward = true;
        // LoggerCS.logInfo(" Legs Paused Move . on Step:" + stepFrom);
    }

    @Override
    public void toStart() {
        stepFrom = 0;
        stepByStep = 0;

        LeftLeg.reversePosition();
        RightLeg.reversePosition();

    }

    @Override
    public void toEnd() {
        goStepForward(stepByStep);
        stepByStep++;
        stepFrom = stepByStep;
        // LoggerCS.logInfo(" Legs Paused Move . Step by Step:" + stepFrom);
    }

    @Override
    public void removePauseFoward() {

        // LoggerCS.logInfo(" Legs Paused Removed Move . on Step:" + stepFrom);
    }

    private void rotateInstance(int frame) {
        try {
            generateNewCoord(frame);
            LeftLeg.rotate(dTLX, dBLX);
            RightLeg.rotate(dTRX, dBRX);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void goStepForward(int frame) {
        rotateInstance(frame);
        rotateInstance(frame);
        // LoggerCS.logInfo(" Legs Move . Step:" + frame);
    }

}
