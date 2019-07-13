/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.drawables;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import mathUtils.AffineCalc;
import pns.api.mainClasses.Segment;
import pns.interfaces.IDrawing;

/**
 *
 * @author Movement
 */
public class DSegment extends Segment implements IDrawing {

    private double X = 0, Y = 0, Z = 0, radius = 2, angle = 0;

    private Color color = Color.BLACK;
    private Color colorH = Color.BLACK;

    private Pane panel = new Pane();
    private Line line = new Line();

    public DSegment() {
        panel.setId(pns.utils.strings.RStrings.rndLetterStringRNDLen(5));
    }

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public double getZ() {
        return Z;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorH() {
        return colorH;
    }

    public void setColorH(Color colorH) {
        this.colorH = colorH;
    }

    public Pane getPanel() {
        return panel;
    }

    @Override
    public Light.Point draw() {
        Rotate rotate = new Rotate();
        rotate.setPivotX(0);
        rotate.setPivotY(0);
        rotate.setPivotZ(0);
        rotate.setAngle(angle);
        double translLineTo = 0;
        line.setStroke(color);
        line.setStrokeWidth(2);
        line.setSmooth(true);
        line.setEndX(length);

        Circle c = new Circle(translLineTo, 0, radius);
        if (radius > 10) {
            translLineTo = length / 2 + radius;
            line.setTranslateX(length / 2 + radius);
            c.setCenterX(length / 2);
        }

        c.setStrokeWidth(1);
        c.setStroke(color);
        c.setFill(colorH);
        panel.getTransforms().add(rotate);

        Light.Point res = new Light.Point();
        res.setX(X + (translLineTo + length) * Math.cos(AffineCalc.radfromDegree * angle));
        res.setY(Y + (translLineTo + length) * Math.sin(AffineCalc.radfromDegree * angle));
        panel.setTranslateX(X);
        panel.setTranslateY(Y);

        panel.getChildren().clear();
        panel.getChildren().add(c);

        panel.getChildren().add(line);
        return res;
    }

}
