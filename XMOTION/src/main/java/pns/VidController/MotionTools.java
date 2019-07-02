/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.RotateTransition;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import pns.api.mainClasses.Point9;

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

    private Rotate rotate = new Rotate();
    private RotateTransition rotateTransition = new RotateTransition();
    private Text text = new Text();

    private List<Point9> point9List = new ArrayList<Point9>();

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

    public List<Point9> getPoint9List() {
        return point9List;
    }

    public void setPoint9List(List<Point9> point9List) {
        this.point9List = point9List;
    }

    public void drawSegmentForward() {
        if (point9List.size() > 1) {
            currFrame++;
            currFrame = currFrame % point9List.size();

            nextFrame = (currFrame + 1) % point9List.size();

            Point9 pt90 = point9List.get(currFrame);
            Point9 pt91 = point9List.get(nextFrame);
            angle = pt91.getV1() - pt90.getV1();
            angle = pt90.getV1();

            rotate.setAngle(angle);

            text.setText(nextFrame + "   " + pt90.getMoment() + " :    " + pt90.getX1() + " ,    angle " + angle + "    rotate.getAngle() " + rotate.getAngle());
        }
    }

    public void drawSegmentBackward() {
        if (point9List.size() > 1) {
            currFrame--;
            if (currFrame < 0) {
                currFrame = point9List.size() + currFrame;
            }
            nextFrame = (currFrame - 1) % point9List.size();
            if (nextFrame < 0) {
                nextFrame = point9List.size() + nextFrame;
            }

            Point9 pt90 = point9List.get(currFrame);
            Point9 pt91 = point9List.get(nextFrame);
            angle = pt91.getV1() - pt90.getV1();
            angle = pt90.getV1();
            rotate.setAngle(angle);
            text.setText(nextFrame + "   " + pt90.getMoment() + " :    " + pt90.getX1() + " ,    angle " + angle + "    rotate.getAngle() " + rotate.getAngle());
        }

    }

}
