/**
 * ******************************************************************************************************************************
 *
 * Это класс для рисования одной  конечности     extends Limb implements IDrawing
 *
 * Используется для отрисовкиобеих ног и обеих рук
 *
 * Содержит
 *
 * Поля
 *
 * private double X = 0, Y = 0, Z = 0, radius = 2, angle;  -- координаты, размер сустава, углы
 *
 * private SortedSet< Segment> topSet; private SortedSet<Segment> bottomSet; private DSegment top; private DSegment bottom; private double topLength = 0; private double
 * --- наборы данных для движения
 *
 * bottomLength = 0; -- длина
 *
 * private Color[] color = {Color.rgb(150, 0, 0), Color.rgb(200, 0, 0)}; цвет прорисовки сегментов
 *
 * private Pane panel = new Pane(); private Pane panelTop = new Pane(); private Pane panelBottom = new Pane(); -- общяя панель сегментов, панели верха и низа
 *
 * protected Light.Point topPt = new Light.Point();стартовая точка отрисовки
 *
 *
 *
 *
 * Методы
 *
 * сеттеры и геттеры и
 *
 *
 * @Override--из IDrawing public Light.Point draw() отрисовывает конечность и возвращает ее конец
 *
 * private Light.Point prepareLines() подготовка к рисованию --- свойства сегментов public Light.Point mkTopEnd() расчет конечной точки верха
 *
 * public Light.Point mkBottomEnd() расчет конечной точки низа
 *
 * private Translate mkTranslate() создание авинного переноса public void rotate(double dt, double db) создание
 *
 * Rotate rotateB = new Rotate(); rotateB.setAngle(db); bottom.getPanel().getTransforms().add(rotateB);
 *
 * }
 *
 *
 ********************************************************************************************************************************
 */
package pns.drawables;

import java.util.SortedSet;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import pns.api.mainClasses.Limb;
import pns.api.mainClasses.Segment;
import pns.interfaces.IDrawing;
import utils.math.AffineCalc;

/**
 *
 * @author Movement
 */
public class DLimb extends Limb implements IDrawing {

    private double X = 0, Y = 0, Z = 0, radius = 2, angle;
    private SortedSet< Segment> topSet;
    private SortedSet<Segment> bottomSet;
    protected DSegment top;
    protected DSegment bottom;
    private double topLength = 0;
    private double bottomLength = 0;

    private int id = 0;

    private Color[] color = {Color.rgb(150, 0, 0), Color.rgb(200, 0, 0)};

    protected double totalAngleTop = 0;
    protected double totalAngleBottom = 0;

    protected Rotate rotateT = new Rotate();
    protected Rotate rotateB = new Rotate();

    private Pane panel = new Pane();
    private Pane panelTop = new Pane();
    private Pane panelBottom = new Pane();

    protected Light.Point topPt = new Light.Point();
    protected Light.Point botPt = new Light.Point();

    public Pane getPanel() {
        return panel;
    }

    public Pane getPanelTop() {
        return panelTop;
    }

    public Pane getPanelBottom() {
        return panelBottom;
    }

    public double getTotalAngleTop() {
        return totalAngleTop;
    }

    public double getTotalAngleBottom() {
        return totalAngleBottom;
    }

    public DLimb() {
        id = pns.utils.numbers.RInts.rndInt(100, 999);
//        System.out.println(" DLIMB id= " + id);
        panel.setId(pns.utils.strings.RStrings.rndLetterStringRNDLen(10));
        top = new DSegment();
        bottom = new DSegment();

        panel.getChildren().clear();
        panel.getChildren().add(top.getPanel());
        panel.getChildren().add(bottom.getPanel());
    }

    public Light.Point getBotPt() {
        return botPt;
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

    public Light.Point getTopPt() {
        return topPt;
    }

    @Override
    public Light.Point draw() {

        Light.Point res = prepareLines();

        panel.setTranslateX(X);
        panel.setTranslateY(Y);
        panel.setTranslateZ(Z);
        panel.getTransforms().add(new Rotate(angle));

        panel.getChildren().clear();
        panelTop.getChildren().clear();
        panelBottom.getChildren().clear();

        panelTop.getChildren().add(top.getPanel());
        panelBottom.getChildren().add(bottom.getPanel());

        panel.getChildren().add(panelTop);
        panelTop.getChildren().add(panelBottom);

        topPt.setX(top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()));
        topPt.setY(top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));

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

        Translate t = mkTranslate();
        //bottom.getPanel().getTransforms().add(t);
        panelBottom.getTransforms().add(t);

        botPt = bottom.draw();
        return AffineCalc.addPoints(botPt, p1);
    }

    public Light.Point mkTopEnd() {
        Light.Point res = new Light.Point();

        res.setX(top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()));
        res.setY(top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));
        return res;
    }

    public Light.Point mkBottomEnd() {
        Light.Point res = new Light.Point();
        res.setX(bottom.getLength() * Math.cos(AffineCalc.radfromDegree * bottom.getAngle()));
        res.setY(bottom.getY() + bottom.getLength() * Math.sin(AffineCalc.radfromDegree * bottom.getAngle()));
        return res;
    }

    public Translate mkTranslate() {
        Translate t = new Translate(
                top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()),
                top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));
        return t;
    }

    public Translate mkTranslate(double angle) {
        Translate t = new Translate(
                top.getLength() * Math.cos(AffineCalc.radfromDegree * angle),
                top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * angle));
        return t;
    }

    public void rotate(double dt, double db) throws NullPointerException {

        System.out.println("LIMB Thread: " + Thread.currentThread().getName());

        if (rotateT == null) {
            rotateT = new Rotate();
        }
        rotateT.setAngle(dt);
        panelTop.getTransforms().add(rotateT);

        if (rotateB == null) {
            rotateB = new Rotate();
        }
        rotateB.setAngle(db);

        panelBottom.getTransforms().add(rotateB);

    }

    public void reversePosition() {
        panel.getTransforms().clear();
        getPanelTop().getTransforms().clear();
        getPanelBottom().getTransforms().clear();
        getPanelBottom().setTranslateX(mkTopEnd().getX());
        getPanelBottom().setTranslateY(mkTopEnd().getY());
//        System.out.println("    TO START!!   DLIMB id=" + id);
    }

}
