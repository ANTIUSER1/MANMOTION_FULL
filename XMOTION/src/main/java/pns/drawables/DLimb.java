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
    private SortedSet< Segment> topSet;
    private SortedSet<Segment> bottomSet;
    private DSegment top;
    private DSegment bottom;
    private double topLength = 0;
    private double bottomLength = 0;

    private Color[] color = {Color.rgb(150, 0, 0), Color.rgb(200, 0, 0)};

    protected double totalAngleTop = 0;
    protected double totalAngleBottom = 0;

    protected double totalRotationAngleTop = 0;
    protected double totalRotationAngleButtom = 0;

    protected Rotate rotateT = new Rotate();
    protected Rotate rotateB = new Rotate();

    private Pane panel = new Pane();
    private Pane panelTop = new Pane();
    private Pane panelBottom = new Pane();

    protected Light.Point topPt = new Light.Point();

    public void setTotalRotationAngleTop(double totalRotationAngleTop) {
        this.totalRotationAngleTop = totalRotationAngleTop;
    }

    public void setTotalRotationAngleButtom(double totalRotationAngleButtom) {
        this.totalRotationAngleButtom = totalRotationAngleButtom;
    }

    public Pane getPanel() {
        return panel;
    }

    public double getTotalAngleTop() {
        return totalAngleTop;
    }

    public double getTotalAngleBottom() {
        return totalAngleBottom;
    }

    public DLimb() {
        panel.setId(pns.utils.strings.RStrings.rndLetterStringRNDLen(10));
        top = new DSegment();
        bottom = new DSegment();

        panel.getChildren().clear();
        panel.getChildren().add(top.getPanel());
        panel.getChildren().add(bottom.getPanel());
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

        panel.getChildren().add(top.getPanel());
        panel.getChildren().add(bottom.getPanel());

        topPt.setX(top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()));
        topPt.setY(top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));

//        Rectangle rectangle = new Rectangle();
//
//        rectangle.setFill(Color.rgb(200, 200, 200, 0.5));
//        //Setting the properties of the rectangle
//        rectangle.setX(0.0f);
//        rectangle.setY(0.0f);
//        rectangle.setWidth(30.0f);
//        rectangle.setHeight(30.0f);
//        panel.getChildren().add(rectangle);
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
        bottom.getPanel().getTransforms().add(t);

        Light.Point p2 = bottom.draw();
        return AffineCalc.addPoints(p2, p1);
    }

    public Light.Point mkTopEnd() {
        Light.Point res = new Light.Point();
        System.out.println("   LIMB top Angle: " + top.getAngle() + "  angle    " + angle);
        System.out.println("    LIMB top Angle:    getAbsoluteAngle()" + top.getAbsoluteAngle());

        res.setX(top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAbsoluteAngle()));
        res.setY(top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAbsoluteAngle()));
        return res;
    }

    public Light.Point mkBottomEnd() {
        Light.Point res = new Light.Point();
        res.setX(bottom.getLength() * Math.cos(AffineCalc.radfromDegree * bottom.getAngle()));
        res.setY(bottom.getY() + bottom.getLength() * Math.sin(AffineCalc.radfromDegree * bottom.getAngle()));
        return res;
    }

    private Translate mkTranslate() {
        Translate t = new Translate(
                top.getLength() * Math.cos(AffineCalc.radfromDegree * top.getAngle()),
                top.getY() + top.getLength() * Math.sin(AffineCalc.radfromDegree * top.getAngle()));
        return t;
    }

    public void rotate(double dt, double db) {
        totalAngleTop += dt;

        rotateT = new Rotate();
        rotateT.setAngle(dt);
        panel.getTransforms().add(rotateT);

        rotateB = new Rotate();
        totalAngleBottom += db;
        rotateB.setAngle(db);
        bottom.getPanel().getTransforms().add(rotateB);
    }

}
