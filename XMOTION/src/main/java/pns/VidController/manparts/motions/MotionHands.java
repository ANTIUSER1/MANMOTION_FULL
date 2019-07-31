/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHands;
import pns.api.mainClasses.Man;
import pns.api.utils.SizePositionUtils;
import pns.datatools.ConvertToHands;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DLimb;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionHands extends PatternHands implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHands ctoHands;
    private DLimb[] limbs;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionHands(Man man) {
        super(man);
        ctoHands = new ConvertToHands(man);
        limbs = ctoHands.getLimbs();

        topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());

    }

    @Override
    public void motionFoward() {
        isPausedForward = false;

        System.out.println("  hands mover  " + limbs[0].getSegmentSetTop().size());
        task = new Task<Void>() {
            int step = stepFrom;

            @Override
            protected Void call() throws Exception {
                for (step = stepFrom; step < limbs[0].getSegmentSetBottom().size() && step < limbs[0].getSegmentSetTop().size()
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
                System.out.println(" hands-step " + step);
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

    }

    @Override
    public void toEnd() {
        stepFrom = 0;
        LeftHand.getPanelTop().getTransforms().clear();
        LeftHand.getPanelBottom().getTransforms().clear();
        LeftHand.getPanelBottom().setTranslateX(LeftHand.mkTopEnd().getX());
        LeftHand.getPanelBottom().setTranslateY(LeftHand.mkTopEnd().getY());

        RightHand.getPanelTop().getTransforms().clear();
        RightHand.getPanelBottom().getTransforms().clear();
        RightHand.getPanelBottom().setTranslateX(RightHand.mkTopEnd().getX());
        RightHand.getPanelBottom().setTranslateY(RightHand.mkTopEnd().getY());

    }

    @Override
    public void removePauseFoward() {

    }

    private void rotateInstance(int frame) {
        try {
            generateNewCoord(frame);
            LeftHand.rotate(dTLX, dBLX);
            RightHand.rotate(dTRX, dBRX);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void goStepForward(int frame) {
        rotateInstance(frame);
    }

}
