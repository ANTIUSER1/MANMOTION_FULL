/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.visualisators;

import datatools.ConvertToSegment;
import datatools.DataReceiver;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import pns.VidController.MotionTools;
import pns.VidController.Motions;
import pns.interfaces.ISupporter;

/**
 *
 * @author Movement
 */
public class SegmentVisualisator extends Pane implements ISupporter {

    private Line line;
    private Color color;
    private Light.Point pivot;
    private double angle;
    private double length = 120;

    private int detector = 1;

    private DataReceiver dataReceiver = DataReceiver.getInstance();
    private ConvertToSegment ctoSegment = ConvertToSegment.getInstance();

    private MotionTools motionTools = new MotionTools();

    public SegmentVisualisator() {
        String sID = pns.utils.strings.RStrings.rndLetterStringRNDLen(10) + System.currentTimeMillis() + System.nanoTime();
        setId(sID);
        setRotate(angle);
        angle = 0;
        line = new Line();
        pivot = new Light.Point();
    }

    public SegmentVisualisator(Color color) {
        String sID = pns.utils.strings.RStrings.rndLetterStringRNDLen(10) + System.currentTimeMillis() + System.nanoTime();
        setId(sID);
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = new Line();
        this.color = color;
    }

    public SegmentVisualisator(Line var) {
        String sID = pns.utils.strings.RStrings.rndLetterStringRNDLen(10) + System.currentTimeMillis() + System.nanoTime();
        setId(sID);
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = var;
        drawLine();
    }

    public SegmentVisualisator(Line var, Color color) {
        String sID = pns.utils.strings.RStrings.rndLetterStringRNDLen(10) + System.currentTimeMillis() + System.nanoTime();
        setId(sID);
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = var;
        drawLine();
        this.color = color;
    }

    public int getDetector() {
        return detector;
    }

    public void setDetector(int detector) {
        this.detector = detector;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        drawLine();
        this.line = line;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Light.Point getPivot() {
        return pivot;
    }

    public void setPivot(Light.Point pivot) {
        this.pivot = pivot;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    private void drawLine() {

        line.setStroke(color);
        line.setStrokeWidth(2);
        line.setEndX(length);

        line.setTranslateX(line.getEndX() / 2);
        Circle c = new Circle(line.getEndX() / 2, 0, 2.5);

        getChildren().remove(c);
        this.getChildren().add(c);
        getChildren().remove(line);
        this.getChildren().add(line);
        Motions.getInstance().rotate(this, angle);

    }

    public boolean rotate(int k, boolean reverse) {
        if (dataReceiver.getPoint9List().size() > 1) {
            int kNext = (k + 1) % dataReceiver.getPoint9List().size();

            double from = dataReceiver.getPoint9List().get(k).getV1();
            double to = dataReceiver.getPoint9List().get(kNext).getV1();
            if (!reverse) {
                from = dataReceiver.getPoint9List().get(kNext).getV1();
                to = dataReceiver.getPoint9List().get(k).getV1();
            }
            System.out.println(from + "=" + to + "   ~~~~~~~~~~~~~~ " + k + "    A " + (from + angle) + "  " + (to + angle));

            motionTools.getText().setText(" Frame: " + k + " Current Angle " + dataReceiver.getPoint9List().get(k).getX1() + " Current Speed " + dataReceiver.getPoint9List().get(k).getV1());
            Motions.getInstance().rotate(this, 250, 40, from + angle, to + angle, reverse);
            //motions.rotate(panelSpt, 250, 40, from, to, reverse);
            return true;
        }
        return false;
    }
}
