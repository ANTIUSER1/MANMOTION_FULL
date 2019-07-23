/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import java.util.List;
import javafx.concurrent.Task;
import pns.VidController.manparts.PatternBody;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.api.utils.SizePositionUtils;
import pns.datatools.ConvertToBody;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DSegment;
import pns.interfaces.IMotion;
import pns.start.Main;

/**
 *
 * @author Movement
 */
public class MotionBody extends PatternBody implements IMotion {

    private DataReciever dataReciever = DataReciever.getInstance();
    private int k = 0;

    private ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    private ConvertToBody ctoBody;
    private DSegment limb;
    private List<Segment> mover;
    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    public MotionBody(Man man) {
        super(man);
        ctoBody = ConvertToBody.getInstance(man);
        limb = ctoBody.getLimb();
        //    System.out.println("DDDD   DDDDDDDDDDDDDDDD1");
        if (limb != null) {
            //      SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
        }

    }

    /**
     * стартовое значение угла
     */
    private double dT = 0;

    @Override
    public void motionFoward() {
        System.out.println("start ");
        //  SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
        mover = SizePositionUtils.settolist(limb.getMoverSet());
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while (k < mover.size()) {
                    if (!isPaused) {
                        try {
                            updateProgress(k, 1000);
                        } catch (Exception e) {
                        }
                        Thread.sleep(Main.timeout);
                        if (k == 0) {
                            Thread.sleep(Main.timeout * 5);
                        }
                        k++;
                        System.out.println("      body " + k);
                    }
                }
                System.out.println("done!");
                return null;
            }

            @Override

            protected void updateProgress(long workDone, long max) {

                dT = mover.get(k).getFixedPoint().getV1();
                System.out.println("  BODY FixedPoint L=   " + dT + "    gen rotation: totalAngle " + totalAngle);
                System.out.println("---------------------------- gen rotation: totalAngle " + totalAngle);
                System.out.println("  BODY FixedPoint L=   " + dT + "    gen rotation: totalAngle " + totalAngle);
                rotate(dT);

                //System.out.println(h);
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();

    }

    @Override
    public void motionPause() {
        swapPause();
        patternHand.motionPause();
        patternLeg.motionPause();
    }

    @Override
    public void motionBackward() {

    }

    @Override
    public void toStart() {
        k = 0;
        System.out.println("IIIIIIIIIIII   -  " + getClass().getCanonicalName());
        System.out.println("IIIIIIIIIIII   RRRRRRRRRRRRRRR---     :::        " + totalAngle);
        System.out.println("IIIIIIIIIIII   -");
        rotate(-totalAngle);

        patternHand.toStart();
        patternLeg.toStart();
    }

    @Override
    public void toEnd() {
        if (mover == null) {
            k = 0;
        }

        k = mover.size() - 1;
        patternHand.toEnd();
        patternLeg.toEnd();
    }

}
