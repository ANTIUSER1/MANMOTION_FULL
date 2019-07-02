/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.visualisators;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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

    public SegmentVisualisator() {
        setRotate(angle);
        angle = 0;
        line = new Line();
        pivot = new Light.Point();
    }

    public SegmentVisualisator(Color color) {
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = new Line();
        this.color = color;
    }

    public SegmentVisualisator(Line var) {
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = var;
        drawLine();
    }

    public SegmentVisualisator(Line var, Color color) {
        setRotate(angle);
        angle = 0;
        pivot = new Light.Point();
        line = var;
        drawLine();
        this.color = color;
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

    private void drawLine() {
        line.setStroke(color);
        line.setStrokeWidth(2);
        line.setEndX(100);

        line.setTranslateX(line.getEndX() / 2);
        setRotate(angle);

        getChildren().remove(line);
        this.getChildren().add(line);
    }

}
