/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import java.util.List;
import javafx.concurrent.Task;
import pns.VidController.manparts.PatternLeg;
import pns.api.mainClasses.Segment;
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
public class MotionLegs extends PatternLeg implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToLegs ctoLegs;
    private DLimb[] limbs;

    private static MotionLegs instance;

    private MotionLegs() {
        ctoMan = ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoLegs = ConvertToLegs.getInstance(ctoMan.getMan());
        limbs = ctoLegs.getLimbs();
    }

    public static MotionLegs getInstance() {
        if (instance == null) {
            synchronized (MotionLegs.class) {
                if (instance == null) {
                    instance = new MotionLegs();
                }
            }
        }
        return instance;
    }

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void motionFoward() {
        List<Segment> topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        List<Segment> bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        List<Segment> topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        List<Segment> bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
        //SetArrayDisplayUtil.setDisplay(legs[1].getSegmentSetBottom());

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int k = 0;
                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()
                        && k < limbs[1].getSegmentSetBottom().size() && k < limbs[1].getSegmentSetTop().size()) {
                    updateProgress(k, 1000);
                    Thread.sleep(Main.timeout);
                    k++;
                }
                return null;
            }

            @Override

            protected void updateProgress(long workDone, long max) {
                int h = (int) workDone;
                double dTL = topL.get(h).getFixedPoint().getV1();
                double dBL = bottomL.get(h).getFixedPoint().getV1();
                double dTR = topL.get(h).getFixedPoint().getV1();
                double dBR = bottomL.get(h).getFixedPoint().getV1();
//                System.out.println("  LEG FixedPoint L=   " + dTL + "      " + dBL);
//                System.out.println("  LEG FixedPoint R=   " + dTR + "      " + dBR);
                LeftLeg.rotate(dTL, dBL);
                RightLeg.rotate(dTR, dBR);

                //  manuallyMove(dTL, dBL);
                //  manuallyMove(dTR, dBR);
                System.out.println(h);
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();

    }

    @Override
    public void motionBackward() {

    }

    @Override
    public void stop() {
        if (task != null) {
            task.cancel();
        }
    }

    private void manuallyMove(double d0, double d1) {

        reDrawLegs(d0, d1);

        System.out.println("rotate on  " + d0 + "     and   " + d1);

    }

}
