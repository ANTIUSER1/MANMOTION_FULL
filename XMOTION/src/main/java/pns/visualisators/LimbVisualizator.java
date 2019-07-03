/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.visualisators;

import javafx.scene.layout.Pane;
import pns.interfaces.ISupporter;

/**
 *
 * @author Movement
 */
public class LimbVisualizator extends Pane implements ISupporter {

    private SegmentVisualisator topSegment = new SegmentVisualisator();
    private SegmentVisualisator bottomSegment = new SegmentVisualisator();

    private double angle = 0;
    private double shift = 0;
    private double length = 0;

    public LimbVisualizator() {
        this.getChildren().remove(topSegment);
        this.getChildren().remove(bottomSegment);

        this.getChildren().add(topSegment);
        this.getChildren().add(bottomSegment);
        //bottomSegment.setTranslateX(shift);
        bottomSegment.setTranslateX(topSegment.getLength());

        drawLimb();

    }

    public double getShift() {
        return shift;
    }

    public double getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    private void drawLimb() {

        topSegment.setAngle(70);
        bottomSegment.setAngle(80);

    }

}
