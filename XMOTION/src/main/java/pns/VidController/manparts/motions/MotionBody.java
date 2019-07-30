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
        mover = SizePositionUtils.settolist(limb.getMoverSet());
    }

    /**
     * стартовое значение угла
     */
    private double dT = 0;

    @Override
    public void motionFoward() {
        isPausedForward = false;

        System.out.println("  body mover  " + mover.size());
        task = new Task<Void>() {
            int step = stepFrom;

            @Override
            protected Void call() throws Exception {

                for (step = stepFrom; step < mover.size() && !isPausedForward; step++) {
                    stepFrom = step;
                    updateProgress(Main.timeout, 1000);
                    Thread.sleep(Main.timeout);
                    if (step == 0) {
                        Thread.sleep(Main.timeout * 5);
                    }

                }
                return null;
            }

            @Override
            protected void updateProgress(long workDone, long max) {
                goStepForward(step);
                System.out.println(" body-step " + step);
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
    }

    @Override
    public void toEnd() {
    }

    @Override
    public void removePauseFoward() {
        isPausedForward = false;

    }

    private void rotateInstance(int frame) {

        try {
            dT = mover.get(frame).getFixedPoint().getV1();
            rotate(dT);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void goStepForward(int frame) {
        rotateInstance(frame);
    }
}
