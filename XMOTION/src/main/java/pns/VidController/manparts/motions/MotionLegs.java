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

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToLegs ctoLegs;
    private DLimb[] limbs;

    private static MotionLegs instance;

    public MotionLegs(Man man) {
        super(man);
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

        System.out.println("  legs mover  " + limbs[0].getSegmentSetTop().size());
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
                System.out.println(" legs-step " + step);
            }

        };

        (new Thread(task)).start();
    }

    @Override
    public void motionPause() {
        isPausedForward = true;
    }

    @Override
    public void toStart() {
        stepFrom = 0;
        double aTop = reverceLeftTop();
        double aBottom = reverceLeftBottom();
        LeftLeg.rotate(-aTop, -aBottom);

//        Rotate leftTopRotation = (Rotate) LeftLeg.getTop().getPanel().getTransforms().get(0);
//        Rotate leftBottomRotation = (Rotate) LeftLeg.getBottom().getPanel().getTransforms().get(0);
//        double totalTop = leftTopRotation.angleProperty().get();
//        double totalBottom = leftBottomRotation.angleProperty().get();
//        LeftLeg.rotate(-totalTop, -totalBottom);
//        Rotate rightTopRotation = (Rotate) RightLeg.getTop().getPanel().getTransforms().get(0);
//
//        Rotate rightBottomRotation;// = (Rotate) RightLeg.getBottom().getPanel().getTransforms().get(0);
//
//        totalTop = rightTopRotation.angleProperty().get();
//        totalBottom = rightBottomRotation.angleProperty().get();
//        RightLeg.rotate(-totalTop, -totalBottom);
    }

    @Override
    public void toEnd() {

    }

    @Override
    public void removePauseFoward() {

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
    }

}
