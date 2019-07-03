/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController;

import datatools.ConvertToSegment;
import datatools.DataReceiver;
import javafx.animation.RotateTransition;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Movement
 */
public class MotionTools {

    private boolean visualizated = false;
    private boolean cycleRunFoward = true;
    private boolean cycleRunBackword = true;
    private int currFrame, nextFrame = 0;
    private double angle = 0;

    private DataReceiver dataReceiver = DataReceiver.getInstance();
    private ConvertToSegment ctoSegment = ConvertToSegment.getInstance();

    private Rotate rotate = new Rotate();
    private RotateTransition rotateTransition = new RotateTransition();
    private Text text = new Text();

    public MotionTools() {
        System.out.println("     dataReceiver.getPoint9TreeSet().size(); " + dataReceiver.getPoint9TreeSet().size());
        System.out.println("");
        System.out.println("");
        System.out.println("-----------");
    }

    public boolean isVisualizated() {
        return visualizated;
    }

    public void setVisualizated(boolean visualizated) {
        this.visualizated = visualizated;
    }

    public boolean isCycleRunFoward() {
        return cycleRunFoward;
    }

    public void setCycleRunFoward(boolean cycleRunFoward) {
        this.cycleRunFoward = cycleRunFoward;
    }

    public boolean isCycleRunBackword() {
        return cycleRunBackword;
    }

    public void setCycleRunBackword(boolean cycleRunBackword) {
        this.cycleRunBackword = cycleRunBackword;
    }

    public int getCurrFrame() {
        return currFrame;
    }

    public void setCurrFrame(int currFrame) {
        if (dataReceiver.getPoint9List().size() == 0) {
            return;
        }
        currFrame--;
        if (currFrame < 0) {
            currFrame = dataReceiver.getPoint9List().size() + currFrame;
        }
        nextFrame = (currFrame - 1) % dataReceiver.getPoint9List().size();
        if (nextFrame < 0) {
            nextFrame = dataReceiver.getPoint9List().size() + nextFrame;
        }
        currFrame++;
        currFrame = currFrame % dataReceiver.getPoint9List().size();

        nextFrame = (currFrame + 1) % dataReceiver.getPoint9List().size();

        this.currFrame = currFrame;
    }

    public int getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(int nextFrame) {
        this.nextFrame = nextFrame;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Rotate getRotate() {
        return rotate;
    }

    public void setRotate(Rotate rotate) {
        this.rotate = rotate;
    }

    public RotateTransition getRt() {
        return rotateTransition;
    }

    public void setRt(RotateTransition rt) {
        this.rotateTransition = rt;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

}
