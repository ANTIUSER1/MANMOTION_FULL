/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import java.util.List;
import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHand;
import pns.api.mainClasses.Segment;
import pns.api.utils.SetArrayDisplayUtil;
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
        ctoMan = ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoHands = ConvertToHands.getInstance(ctoMan.getMan());
        limbs = ctoHands.getLimbs();
    }

    @Override
    public void motionFoward() {
//        manuallyMove(5, 10);

        List<Segment> topL = SizePositionUtils.settolist(limbs[0].getSegmentSetTop());
        List<Segment> bottomL = SizePositionUtils.settolist(limbs[0].getSegmentSetBottom());

        List<Segment> topR = SizePositionUtils.settolist(limbs[1].getSegmentSetTop());
        List<Segment> bottomR = SizePositionUtils.settolist(limbs[1].getSegmentSetBottom());
        SetArrayDisplayUtil.setDisplay(limbs[0].getSegmentSetTop());

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int k = 0;
                while (k < limbs[0].getSegmentSetBottom().size() && k < limbs[0].getSegmentSetTop().size()) {
                    updateProgress(k, 1000);
                    Thread.sleep(4000);
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
                System.out.println("  HAND FixedPoint L=   " + dTL + "      " + dBL);
                System.out.println("  HAND FixedPoint R=   " + dTR + "      " + dBR);
                LeftHand.rotate(dTL, dBL);
                RightHand.rotate(dTR, dBR);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
