/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.concurrent.Task;
import pns.VidController.manparts.PatternHead;
import pns.interfaces.IMotion;

/**
 *
 * @author Movement
 */
public class MotionHead extends PatternHead implements IMotion {

    private boolean isGoingRun = false;

    private static Task<Void> task;

    public static void taskClose() {
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void motionFoward() {

    }

    @Override
    public void motionBackward() {

        System.out.println("   FFFFFF     ----------> " + isGoingRun);
        if (!isGoingRun) {
            isGoingRun = true;

//            dataReceiver.prepareData();
//            createSegmentVisual();
            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int k = 0;
//                    while (k < dataReceiver.getPoint9List().size() && motionTools.isCycleRunFoward()) {
//                        updateProgress(k, 1000);
//                        Thread.sleep(200);
//                        k++;
//                    }
                    isGoingRun = false;
                    return null;
                }

                @Override

                protected void updateProgress(long workDone, long max) {
                    //  panelSpt.rotate((int) workDone, true);
                    //toolMethods.drawSegmentForward();
                    super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
                }
            };

            (new Thread(task)).start();
        }
    }

}
