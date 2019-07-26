/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import pns.api.mainClasses.Segment;
import pns.api.utils.SizePositionUtils;
import pns.drawables.DLimb;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternDraw {

    protected Pane panel;
    protected long properTimeout = 1000;

    protected boolean highSpeed = false;

    protected double totalAngle = 0;
    protected double totalRotationAngle = 0;

    protected Rotate rotateT = new Rotate();
    protected Rotate rotateTInv = new Rotate();

    protected boolean isPausedForward = false;
    protected boolean isPausedBackward = false;

    protected List<Segment> mover;

    protected List<Segment> topL;
    protected List<Segment> bottomL;

    protected List<Segment> topR;
    protected List<Segment> bottomR;

    protected DLimb[] limbs;
    protected DSegment limb;

    public PatternDraw() {
        panel = new Pane();
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

            System.out.println("   BEFORE     topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());   " + limbs[0].getSegmentSetTop().size());
//            // System.out.println("   BEFORE     topL = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());   " + limbs[1].getSegmentSetTop().size());
//
//            System.out.println("0:: ");
//            SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());
////            System.out.println("1");
////            SetArrayDisplayUtil.setDisplay(limbs[1].getSegmentSetTop());
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
//            //System.out.println("   AFTER     topL = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());   " + limbs[1].getSegmentSetTop().size());
//
//            System.out.println("0:: ");
//            SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());
////            System.out.println("1");
//            SetArrayDisplayUtil.setDisplay(limbs[1].getSegmentSetTop());
        } catch (Exception e) {
        }
    }
}
