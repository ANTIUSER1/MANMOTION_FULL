/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
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

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHands ctoHands;
    private DLimb[] limbs;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionHands() {
        super();
        ctoMan = new ConvertToMan();;//ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoHands = new ConvertToHands(ctoMan.getMan());    // ConvertToHands.getInstance(ctoMan.getMan());
        limbs = ctoHands.getLimbs();

        System.out.println("  HANDS    " + (limbs == null));
    }

    @Override
    public void motionFoward() {

//        List<Segment> topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
//        List<Segment> bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());
//
//        List<Segment> topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
//        List<Segment> bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
////        SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());
//
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                int k = 0;
//                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()) {
//                    try {
//                        updateProgress(k, 1000);
//                    } catch (Exception e) {
//                    }
//                    Thread.sleep(Main.timeout);
//                    k++;
//                    System.out.println("            hands " + k);
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

    }

}
