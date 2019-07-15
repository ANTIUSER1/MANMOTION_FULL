/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.drawables;

import java.util.SortedSet;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import mathUtils.AffineCalc;
import pns.api.mainClasses.Limb;
import pns.api.mainClasses.Segment;
import pns.interfaces.IDrawing;

/**
 *
 * @author Movement
 */
public class DLimb extends Limb implements IDrawing {

    private double X = 0, Y = 0, Z = 0, radius = 2, angle;
    private int[] idNo = new int[2];
    private SortedSet< Segment> topSet;
    private SortedSet<Segment> bottomSet;
    private DSegment top;
    private DSegment bottom;
    private double topLength = 0;
    private double bottomLength = 0;

    private Color[] color = {Color.rgb(150, 0, 0), Color.rgb(200, 0, 0)};

    private Pane panel = new Pane();
    private Pane panelTop = new Pane();
    private Pane panelBottom = new Pane();

    public Pane getPanel() {
        return panel;
    }

    public DLimb() {
        panel.setId(pns.utils.strings.RStrings.rndLetterStringRNDLen(10));
        top = new DSegment();
        bottom = new DSegment();
    }

    public int[] getIdNo() {
        return idNo;
    }

    public void setIdNo(int[] idNo) {
        this.idNo = idNo;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
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

    public void setZ(double Z) {
        this.Z = Z;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTopLength() {
        return topLength;
    }

    public void setTopLength(double topLength) {
        this.topLength = topLength;
    }

    public double getBottomLength() {
        return bottomLength;
    }

    public void setBottomLength(double bottomLength) {
        this.bottomLength = bottomLength;
    }

    public Color[] getColor() {
        return color;
    }

    public void setColor(Color[] color) {
        this.color = color;
    }

    public DSegment getTop() {
        return top;
    }

    public void setTop(DSegment top) {
        this.top = top;
    }

    public DSegment getBottom() {
        return bottom;
    }

    public void setBottom(DSegment bottom) {
        this.bottom = bottom;
    }

    @Override
    public Light.Point draw() {

        Light.Point res = prepareLines();
        panel.setTranslateX(X);
        panel.setTranslateY(Y);
        panel.setTranslateZ(Z);
        panel.getTransforms().add(new Rotate(angle));
        panel.getChildren().clear();

        panel.getChildren().add(top.getPanel());
        panel.getChildren().add(bottom.getPanel());

        return res;

    }

    private Light.Point prepareLines() {

        top.setColor(color[0]);
        top.setRadius(2.7);
        top.setLength(topLength);

        Light.Point p1 = top.draw();

        bottom.setColor(color[1]);
        bottom.setRadius(2.3);
        bottom.setLength(bottomLength);

        Translate t = new Translate(
                top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()),
                top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));

        bottom.getPanel().getTransforms().add(t);

        Light.Point p2 = bottom.draw();
        return AffineCalc.addPoints(p2, p1);
    }

}
