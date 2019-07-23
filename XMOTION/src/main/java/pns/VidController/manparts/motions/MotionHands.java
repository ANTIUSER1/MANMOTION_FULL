/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import java.util.List;
import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.api.utils.SizePositionUtils;
import pns.datatools.ConvertToHands;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DLimb;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class MotionHands extends PatternHand implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();
    private int k = 0;

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

        System.out.println("  HANDS    " + (limbs == null));

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

    List<Segment> topL;
    List<Segment> bottomL;
    List<Segment> topR;
    List<Segment> bottomR;

    @Override
    public void motionFoward() {

        topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
        goStepForward();

//        SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());
//
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()) {
//                    if (!isPausedForward) {
//                        try {
//                            updateProgress(k, 1000);
//                        } catch (Exception e) {
//                        }
//                        Thread.sleep(Main.timeout);
//                        if (k == 0) {
//                            Thread.sleep(Main.timeout * 5);
//                        }
//                        k++;
//                        System.out.println("            hands " + k);
//                    }
//                }
//                System.out.println("done!");
//                return null;
//            }
//
//            @Override
//
//            protected void updateProgress(long workDone, long max) {
//
//                dTLX = topL.get(k).getFixedPoint().getV1();
//                dBLX = bottomL.get(k).getFixedPoint().getV1();
//                dTRX = topL.get(k).getFixedPoint().getV1();
//                dBRX = bottomL.get(k).getFixedPoint().getV1();
//
//                dTLY = topL.get(k).getFixedPoint().getV2();
//                dBLY = bottomL.get(k).getFixedPoint().getV2();
//                dTRY = topL.get(k).getFixedPoint().getV2();
//                dBRY = bottomL.get(k).getFixedPoint().getV2();
//
//                dTLZ = topL.get(k).getFixedPoint().getV3();
//                dBLZ = bottomL.get(k).getFixedPoint().getV3();
//                dTRZ = topL.get(k).getFixedPoint().getV3();
//                dBRZ = bottomL.get(k).getFixedPoint().getV3();
//
//                LeftHand.rotate(dTLX, dBLX);
//                RightHand.rotate(dTRX, dBRX);
//
//                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        };
//
//        (new Thread(task)).start();
    }

    @Override
    public void motionBackward() {

        topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
        goStepBackward();

//        SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                while (k > -1) {
//                    if (!isPausedBackward) {
//                        try {
//                            //updateProgress(k, 1000);
//                        } catch (Exception e) {
//                        }
//                        Thread.sleep(Main.timeout);
//                        if (k == 0) {
//                            Thread.sleep(Main.timeout * 5);
//                        }
//                        k--;
//                    }
//                }
//                System.out.println("done!");
//                return null;
//            }
//
//            @Override
//
//            protected void updateProgress(long workDone, long max) {
//
//                dTLX = topL.get(k).getFixedPoint().getV1();
//                dBLX = bottomL.get(k).getFixedPoint().getV1();
//                dTRX = topL.get(k).getFixedPoint().getV1();
//                dBRX = bottomL.get(k).getFixedPoint().getV1();
//
//                dTLY = topL.get(k).getFixedPoint().getV2();
//                dBLY = bottomL.get(k).getFixedPoint().getV2();
//                dTRY = topL.get(k).getFixedPoint().getV2();
//                dBRY = bottomL.get(k).getFixedPoint().getV2();
//
//                dTLZ = topL.get(k).getFixedPoint().getV3();
//                dBLZ = bottomL.get(k).getFixedPoint().getV3();
//                dTRZ = topL.get(k).getFixedPoint().getV3();
//                dBRZ = bottomL.get(k).getFixedPoint().getV3();
//                System.out.println("  HANDS      k= " + k);
//                LeftHand.rotate(-dTLX, -dBLX);
//                RightHand.rotate(-dTRX, -dBRX);
//
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
    }

    @Override
    public void toStart() {
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
        LeftHand.rotate(-LeftHand.getTotalAngleT(), -LeftHand.getTotalAngleB());
        RightHand.rotate(-RightHand.getTotalAngleT(), -RightHand.getTotalAngleB());

    }

    @Override
    public void toEnd() {
        if (limbs == null) {
            k = 0;
        }

        int min0 = Math.min(limbs[0].getSegmentSetBottom().size(), limbs[0].getSegmentSetTop().size());
        int min1 = Math.min(limbs[1].getSegmentSetBottom().size(), limbs[1].getSegmentSetTop().size());
        int min = Math.min(min0, min1);
        k = min - 1;
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
        if (k < topL.size()) {
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

            LeftHand.rotate(dTLX, dBLX);
            RightHand.rotate(dTRX, dBRX);
            System.out.println("               hands::  k=" + k + "   dTLX=" + dTLX + "  dBLX=" + dBLX);
        }
    }

    private void rotateInstanceInv() {
        if (k > -1) {
            dTLX = -topL.get(k).getFixedPoint().getV1();
            dBLX = -bottomL.get(k).getFixedPoint().getV1();
            dTRX = -topL.get(k).getFixedPoint().getV1();
            dBRX = -bottomL.get(k).getFixedPoint().getV1();

            dTLY = -topL.get(k).getFixedPoint().getV2();
            dBLY = -bottomL.get(k).getFixedPoint().getV2();
            dTRY = -topL.get(k).getFixedPoint().getV2();
            dBRY = -bottomL.get(k).getFixedPoint().getV2();

            dTLZ = -topL.get(k).getFixedPoint().getV3();
            dBLZ = -bottomL.get(k).getFixedPoint().getV3();
            dTRZ = -topL.get(k).getFixedPoint().getV3();
            dBRZ = -bottomL.get(k).getFixedPoint().getV3();

            LeftHand.rotate(dTLX, dBLX);
            RightHand.rotate(dTRX, dBRX);
            System.out.println("                hands::  k=" + k + "   dTLX=" + dTLX + "  dBLX=" + dBLX);
        }
    }

    private void goStepForward() {
        rotateInstance();
        k++;
    }

    private void goStepBackward() {
        k--;
        rotateInstanceInv();

    }

}
