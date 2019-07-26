/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHead;
import pns.api.mainClasses.Man;
import pns.datatools.ConvertToHead;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionHead extends PatternHead implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();
    private int k = 0;

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHead ctoHead;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public MotionHead(Man man) {
        super(man);
        ctoHead = ConvertToHead.getInstance(man);
        limb = ctoHead.getLimb();
        //    System.out.println("DDDD   DDDDDDDDDDDDDDDD1");
        if (limb != null) {
//            SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
//
//            System.out.println("HEAD");
        }

    }

    /**
     * стартовое значение угла
     */
    private double dT = 0;

    @Override
    public void motionFoward() {
        mkMover();

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while (k < mover.size()) {
                    if (!isPausedForward) {
                        try {
                            updateProgress(Main.timeout, 1000);
                        } catch (Exception e) {
                        }
                        if (!highSpeed) {
                            Thread.sleep(Main.timeout);
                        } else {
                            Thread.sleep(properTimeout);
                        }
                        if (k == 0) {
                            Thread.sleep(Main.timeout * 5);
                        }

                    }
                }
                highSpeed = false;
                System.out.println(" done!");
                if (task != null) {
                    task.cancel();
                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                try {
                    goStepForward();
                } catch (Exception e) {
                }
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();
    }

    @Override
    public void motionBackward() {
        mkMover();

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while (k > -1) {

                    if (!isPausedBackward) {
                        try {
                            updateProgress(Main.timeout, 1000);
                        } catch (Exception e) {
                        }
                        Thread.sleep(Main.timeout);

                    }
                }
                System.out.println(" done!");
                if (task != null) {
                    task.cancel();
                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                try {
                    goStepForward();
                } catch (Exception e) {
                }
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();
    }

    @Override
    public void stepForward() throws Exception {
        if (task != null) {
            task.cancel();
        }
        mkMover();
        goStepForward();
        patternBody.stepForward();
    }

    @Override
    public void stepBackward() throws Exception {
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
    public void removePauseBackward() {
        isPausedBackward = false;
        patternBody.removePauseBackward();
    }

    @Override
    public void toStart() throws Exception {
        k = 0;
        patternBody.toStart();
        rotate(-totalAngle);

    }

    @Override
    public void toEnd() throws Exception {
        highSpeed = true;
        patternBody.toEnd();
    }

    private void rotateInstance() throws Exception {
        System.out.println(" HEAD::==>>  k=" + k + "   mover size " + mover.size());
        if (k > -1 && k < mover.size()) {
            dT = mover.get(k).getFixedPoint().getV1();
            rotate(dT);
        } else {
            if (task != null) {
                task.cancel();
            }
        }

    }

    private void rotateInstanceInv() throws Exception {

        if (k > -1 && k < mover.size()) {

            dT = mover.get(k).getFixedPoint().getV1();
            rotate(-dT);
        } else {
            if (task != null) {
                task.cancel();
            }
        }
    }

    private void goStepForward() throws Exception {
        rotateInstance();
        k++;
    }

    private void goStepBackward() throws Exception {
        k--;
        rotateInstanceInv();

    }

}
