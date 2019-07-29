/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.List;
import pns.api.mainClasses.Man;
import pns.api.utils.SizePositionUtils;

/**
 *
 * @author Movement
 */
public class PatternDraw extends PatternMAN {

    protected long properTimeout = 1000;

    protected boolean highSpeed = false;

    protected int startStep = 0;
    protected double totalAngle = 0;
    protected double totalRotationAngle = 0;

    protected boolean isPausedForward = false;
    protected boolean isPausedBackward = false;

    /**
     * стартовые значения углов
     */
    protected double dTLX = 0;
    protected double dBLX = 0;
    protected double dTRX = 0;
    protected double dBRX = 0;

    protected double dTLY = 0;
    protected double dBLY = 0;
    protected double dTRY = 0;
    protected double dBRY = 0;

    protected double dTLZ = 0;
    protected double dBLZ = 0;
    protected double dTRZ = 0;
    protected double dBRZ = 0;

    public PatternDraw(Man man) {

//        ctoHands = new ConvertToHands(man);
//        limbs = ctoHands.getLimbs();
//        theMan = man;
        mkMover();
        mkSegmSets();

    }

    protected void mkMover() {
        if (mover == null && limb != null) {
            mover = SizePositionUtils.settolist(limb.getMoverSet());
        }
    }

    public void setIsPausedForward(boolean isPausedForward) {
        this.isPausedForward = isPausedForward;
    }

    public void setIsPausedBackward(boolean isPausedBackward) {
        this.isPausedBackward = isPausedBackward;
    }

    public void setTotalRotationAngle(double totalRotationAngle) {
        this.totalRotationAngle = totalRotationAngle;
    }

    protected void mkSegmSets() {
        try {
            System.out.println("MK SEGMENTS!!");
            if (topL == null) {
                topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
            }
            if (bottomL == null) {
                bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());
            }

            if (topR == null) {
                topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
            }
            if (bottomR == null) {
                bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
            }
            System.out.println("   AFTER     topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());   " + limbs[0].getSegmentSetTop().size());

        } catch (Exception e) {
        }
    }

    protected int reduceK(List mv, int k) {
        if (k <= -1) {
            k = 0;
        }
        if (k > mv.size()) {
            k = mv.size();
        }
        return k;
    }

    protected void generateNewCoord(int frame) {
        System.out.println("   topL " + (topL == null));
        if (topL != null) {
            System.out.println("topL-size " + topL.size());
        }
        try {
            dTLX = topL.get(frame).getFixedPoint().getV1();
            dBLX = bottomL.get(frame).getFixedPoint().getV1();
            dTRX = topL.get(frame).getFixedPoint().getV1();
            dBRX = bottomL.get(frame).getFixedPoint().getV1();

            dTLY = topL.get(frame).getFixedPoint().getV2();
            dBLY = bottomL.get(frame).getFixedPoint().getV2();
            dTRY = topL.get(frame).getFixedPoint().getV2();
            dBRY = bottomL.get(frame).getFixedPoint().getV2();

            dTLZ = topL.get(frame).getFixedPoint().getV3();
            dBLZ = bottomL.get(frame).getFixedPoint().getV3();
            dTRZ = topL.get(frame).getFixedPoint().getV3();
            dBRZ = bottomL.get(frame).getFixedPoint().getV3();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(" @@@@@@@@@@@@@@@@@@@ exception frame: " + frame);

        }
    }
}
