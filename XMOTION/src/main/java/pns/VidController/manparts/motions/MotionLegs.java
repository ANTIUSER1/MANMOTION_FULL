/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternLeg;
import pns.datatools.ConvertToLegs;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DLimb;
import pns.interfaces.IMotion;

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

    public MotionLegs() {
        super();
        ctoMan = new ConvertToMan();//ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoLegs = new ConvertToLegs(ctoMan.getMan());//ConvertToLegs.getInstance(ctoMan.getMan());
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
//        List<Segment> topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
//        List<Segment> bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());
//
//        List<Segment> topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
//        List<Segment> bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
//        //SetArrayDisplayUtil.setDisplay(legs[1].getSegmentSetBottom());
//
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                int k = 0;
//                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()
//                        && k < limbs[1].getSegmentSetBottom().size() && k < limbs[1].getSegmentSetTop().size()) {
//                    try {
//                        updateProgress(k, 1000);
//                    } catch (Exception e) {
//                    }
//                    Thread.sleep(Main.timeout);
//                    k++;
//                    System.out.println("           legs " + k);
//                }
//                System.out.println("done!");
//                return null;
//            }
//
//            @Override
//
//            protected void updateProgress(long workDone, long max) {
//                int h = (int) workDone;
//
//                double dTLX = topL.get(h).getFixedPoint().getV1();
//                double dBLX = bottomL.get(h).getFixedPoint().getV1();
//                double dTRX = topL.get(h).getFixedPoint().getV1();
//                double dBRX = bottomL.get(h).getFixedPoint().getV1();
//
//                double dTLY = topL.get(h).getFixedPoint().getV2();
//                double dBLY = bottomL.get(h).getFixedPoint().getV2();
//                double dTRY = topL.get(h).getFixedPoint().getV2();
//                double dBRY = bottomL.get(h).getFixedPoint().getV2();
//
//                double dTLZ = topL.get(h).getFixedPoint().getV3();
//                double dBLZ = bottomL.get(h).getFixedPoint().getV3();
//                double dTRZ = topL.get(h).getFixedPoint().getV3();
//                double dBRZ = bottomL.get(h).getFixedPoint().getV3();
//
//                LeftLeg.rotate(dTLX, dBLX);
//                RightLeg.rotate(dTRX, dBRX);
//
//                //  manuallyMove(dTL, dBL);
//                //  manuallyMove(dTR, dBR);
////                System.out.println(h);
//                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        };
//
//        (new Thread(task)).start();

    }

    @Override
    public void motionBackward() {

    }

    private void manuallyMove(double d0, double d1) {

        reDrawLegs(d0, d1);

        System.out.println("rotate on  " + d0 + "     and   " + d1);

    }

}
