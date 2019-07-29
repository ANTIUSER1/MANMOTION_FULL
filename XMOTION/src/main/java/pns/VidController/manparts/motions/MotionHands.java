/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
import pns.api.mainClasses.Man;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionHands extends PatternHand implements IMotion {

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public MotionHands(Man man) {
        super(man);
    }

    @Override
    public void motionFoward() {
        task = new Task<Void>() {
            int step = startStep;

            @Override
            protected Void call() throws Exception {
                System.out.println("");
                for (step = startStep; (step < limbs[0].getSegmentSetBottom().size() && step < limbs[0].getSegmentSetTop().size()); step++) {

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
                    System.out.println("  cycle Hand " + step);
                    goStepForward(step);
                } catch (Exception e) {
                }
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();

    }

    @Override
    public void stepForward(int currFrame) throws Exception {
        goStepForward(currFrame);
        //  k = reduceStepNoToNormal(k);
    }

    @Override
    public void stepBackward(int currFrame) throws Exception {

    }

    @Override
    public void motionPause() {
        isPausedBackward = isPausedForward = true;
    }

    @Override
    public void toStart() throws Exception {

        dTLX = 0;
        dBLX = 0;
        dTRX = 0;
        dBRX = 0;

        dTLY = 0;
        dBLY = 0;
        dTRY = 0;
        dBRY = 0;

        dTLZ = 0;
        dBLZ = 0;
        dTRZ = 0;
        dBRZ = 0;
//        LeftHand.rotate(-LeftHand.getTotalAngleTop(), -LeftHand.getTotalAngleBottom());
//        RightHand.rotate(-RightHand.getTotalAngleTop(), -RightHand.getTotalAngleBottom());
        LeftHand.getPanel().getTransforms().clear();
        RightHand.getPanel().getTransforms().clear();
    }

    @Override
    public void toEnd() throws Exception {
        highSpeed = true;
    }

    @Override
    public void removePauseFoward() {
        isPausedForward = false;
    }

    private void rotateInstance(int frame) {
        try {
            generateNewCoord(frame);
            System.out.println(" HANDS:");
            System.out.println("   (dTLX, dBLX)::  " + dTLX + "  " + dBLX);
            System.out.println("   (dTRX, dBRX)::  " + dTRX + "  " + dBRX);
            LeftHand.rotate(dTLX, dBLX);
            RightHand.rotate(dTRX, dBRX);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  exception frame: " + frame);
        }
    }

    private void goStepForward(int frame) throws Exception {
        rotateInstance(frame);
    }

}
