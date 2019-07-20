/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternBody;
import pns.api.utils.SetArrayDisplayUtil;
import pns.datatools.ConvertToBody;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DSegment;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class MotionBody extends PatternBody implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToBody ctoBody;
    private DSegment limb;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionBody() {
        ctoMan = new ConvertToMan();//ConvertToMan.getInstance();
        ctoMan.convert(dataReciever.getData());
        ctoBody = ConvertToBody.getInstance(ctoMan.getMan());
        limb = ctoBody.getLimb();
        //    System.out.println("DDDD   DDDDDDDDDDDDDDDD1");
        if (limb != null) {
            SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
        }

    }

    @Override
    public void motionFoward() {
//        System.out.println("start ");
//        //  SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
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
//                    System.out.println("      body " + k);
//                }
//                System.out.println("done!");
//                return null;
//            }
//
//            @Override
//
//            protected void updateProgress(long workDone, long max) {
//                int h = (int) workDone;
//                double dT = mover.get(h).getFixedPoint().getV1();
//                //System.out.println("  BODY FixedPoint L=   " + dT);
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
