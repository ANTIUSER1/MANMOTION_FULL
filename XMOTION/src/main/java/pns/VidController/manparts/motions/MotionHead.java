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

        System.out.println(" head  mover  " + mover.size());
        task = new Task<Void>() {
            int step = 0;

            @Override
            protected Void call() throws Exception {

                for (step = 0; step < mover.size(); step++) {
                    updateProgress(Main.timeout, 1000);
                    Thread.sleep(Main.timeout);
                    if (step == 0) {
                        Thread.sleep(Main.timeout * 5);
                    }

                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                goStepForward(step);
                System.out.println(" head-step " + step);
            }
        };
        (new Thread(task)).start();
    }

    //@Override
    public void motionFoward0() {
//
//
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//
//                while (k < mover.size()) {
//                    if (!isPausedForward) {
//                        try {
//                            updateProgress(Main.timeout, 1000);
//                        } catch (Exception e) {
//                        }
//                        Thread.sleep(Main.timeout);
//                        if (k == 0) {
//                            Thread.sleep(Main.timeout * 5);
//                        }
//
//                    }
//                }
//                System.out.println(" done!");
//                return null;
//            }
//
//            @Override
//            protected void updateProgress(long workDone, long max) {
//                goStepForward();
//                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        };
//
//        (new Thread(task)).start();
    }

    @Override
    public void motionPause() {
        isPausedBackward = isPausedForward = true;
        patternBody.motionPause();
    }

    @Override
    public void removePauseFoward() {
        isPausedForward = false;
        patternBody.removePauseFoward();
    }

    @Override
    public void toStart() {

    }

    @Override
    public void toEnd() {

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
    }

}
