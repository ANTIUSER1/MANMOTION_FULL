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
        // System.out.println("start F  ");
        //  SetArrayDisplayUtil.setDisplay(limb.getMoverSet());
        mover = SizePositionUtils.settolist(limb.getMoverSet());

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while (k < mover.size()) {
                    if (!isPausedForward) {
                        try {
                            updateProgress(Main.timeout, 1000);
                        } catch (Exception e) {
                        }
                        Thread.sleep(Main.timeout);
                        if (k == 0) {
                            Thread.sleep(Main.timeout * 5);
                        }
                    }
                }
                System.out.println("done!");
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                goStepForward();
                super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
            }

        };

        (new Thread(task)).start();
    }

    @Override
    public void motionPause() {
        isPausedBackward = isPausedForward = true;
        patternHand.motionPause();
        patternLeg.motionPause();
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

    @Override
    public void removePauseFoward() {

    }

    private void rotateInstance() {
        System.out.println("BODY ##  FORW   k=" + k);
        if (k > -1 && k < mover.size()) {
            dT = mover.get(k).getFixedPoint().getV1();
            rotate(dT);
        }
    }

    private void rotateInstanceInv() {
        System.out.println("BODY ##  INV   k=" + k);
        if (k > -1 && k < mover.size()) {

            dT = mover.get(k).getFixedPoint().getV1();
            rotate(-dT);
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
