/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
import pns.api.mainClasses.Man;
import pns.datatools.ConvertToHands;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionHands extends PatternHand implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();
    private int k = 0;

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHands ctoHands;

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
        ctoHands = new ConvertToHands(man);
        limbs = ctoHands.getLimbs();

        System.out.println("  HANDS==null   " + (limbs == null));
        System.out.println("   TOP  ");
        System.out.println(" HAND AS LIMBS[0]  " + limbs[0].getSegmentSetTop().size());
        System.out.println(" HAND AS LIMBS[1]  " + limbs[1].getSegmentSetTop().size());

        mkSegmSets();
    }

    /**
     * стартовые значения углов
     */
    private double dTLX = 0;
    private double dBLX = 0;
    private double dTRX = 0;
    private double dBRX = 0;

    private double dTLY = 0;
    private double dBLY = 0;
    private double dTRY = 0;
    private double dBRY = 0;

    private double dTLZ = 0;
    private double dBLZ = 0;
    private double dTRZ = 0;
    private double dBRZ = 0;

    @Override
    public void motionFoward() {

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()) {
                    if (!isPausedForward) {
                        try {
                            updateProgress(Main.timeout, 1000);
                        } catch (Exception e) {
                        }
                        if (!highSpeed) {
                            Thread.sleep(Main.timeout);
                        } else {
                            Thread.sleep(3);
                        }

                        if (k == 0) {
                            Thread.sleep(Main.timeout * 5);
                        }
                    }
                }
                highSpeed = false;
                System.out.println("done!");
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
                highSpeed = false;
                System.out.println("done!");
                if (task != null) {
                    task.cancel();
                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                try {
                    goStepBackward();
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
        goStepForward();
    }

    @Override
    public void stepBackward() throws Exception {
    }

    @Override
    public void motionPause() {
        isPausedBackward = isPausedForward = true;
    }

    @Override
    public void toStart() throws Exception {
        k = 0;

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
        LeftHand.rotate(-LeftHand.getTotalAngleTop(), -LeftHand.getTotalAngleBottom());
        RightHand.rotate(-RightHand.getTotalAngleTop(), -RightHand.getTotalAngleBottom());

    }

    @Override
    public void toEnd() throws Exception {
        highSpeed = true;
    }

    @Override
    public void removePauseFoward() {
        isPausedForward = false;
    }

    @Override
    public void removePauseBackward() {
        isPausedBackward = false;
    }

    private void rotateInstance() {
        System.out.println(" HAND::==>>  k=" + k + "      topL.size() " + topL.size());
        if (k > -1
                && k < topL.size() && k < topR.size()
                && k < bottomL.size() && k < bottomR.size()) {
            generateNewCoord();

            LeftHand.rotate(dTLX, dBLX);
            RightHand.rotate(dTRX, dBRX);
        } else {
            if (task != null) {
                task.cancel();
            }
        }

    }

    private void rotateInstanceInv() {
        if (k > -1
                && k < topL.size() && k < topR.size()
                && k < bottomL.size() && k < bottomR.size()) {
            generateNewCoord();
            LeftHand.rotate(-dTLX, -dBLX);
            RightHand.rotate(-dTRX, -dBRX);
            System.out.println("                hands::  k=" + k + "   dTLX=" + dTLX + "  dBLX=" + dBLX);
        } else {
            if (task != null) {
                task.cancel();
            }
        }
        if (k == 0) {
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

    private void generateNewCoord() {
        if (k > -1 && k < topL.size()) {

            dTLX = topL.get(k).getFixedPoint().getV1();
            dBLX = bottomL.get(k).getFixedPoint().getV1();
            dTRX = topL.get(k).getFixedPoint().getV1();
            dBRX = bottomL.get(k).getFixedPoint().getV1();

            dTLY = topL.get(k).getFixedPoint().getV2();
            dBLY = bottomL.get(k).getFixedPoint().getV2();
            dTRY = topL.get(k).getFixedPoint().getV2();
            dBRY = bottomL.get(k).getFixedPoint().getV2();

            dTLZ = topL.get(k).getFixedPoint().getV3();
            dBLZ = bottomL.get(k).getFixedPoint().getV3();
            dTRZ = topL.get(k).getFixedPoint().getV3();
            dBRZ = bottomL.get(k).getFixedPoint().getV3();
        }
    }

}
