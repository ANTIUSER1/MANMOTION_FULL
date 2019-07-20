/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHead;
import pns.api.utils.SetArrayDisplayUtil;
import pns.datatools.ConvertToHead;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DSegment;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class MotionHead extends PatternHead implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToHead ctoHead;
    private DSegment limb;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionHead() {
        ctoMan = new ConvertToMan();;  //ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoHead = ConvertToHead.getInstance(ctoMan.getMan());
        limb = ctoHead.getLimb();
        //    System.out.println("DDDD   DDDDDDDDDDDDDDDD1");
        if (limb != null) {
            SetArrayDisplayUtil.setDisplay(limb.getMoverSet());

            System.out.println("HEAD");
        }

    }

    @Override
    public void motionFoward() {

//        System.out.println("start ");
//        SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
//        List<Segment> mover = SizePositionUtils.settolist(limb.getMoverSet());
//        task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                int k = 0;
//                while (k < mover.size()) {
//                    try {
//                        updateProgress(k, 1000);
//                    } catch (Exception e) {
//                    }
//                    Thread.sleep(Main.timeout);
//                    k++;
//                    System.out.println("   k " + k);
//                }
//                System.out.println(" done!");
//                return null;
//            }
//
//            @Override
//
//            protected void updateProgress(long workDone, long max) {
//                int h = (int) workDone;
//                double dT = mover.get(h).getFixedPoint().getV1();
//                rotate(dT);
//
//                //System.out.println(h);
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
