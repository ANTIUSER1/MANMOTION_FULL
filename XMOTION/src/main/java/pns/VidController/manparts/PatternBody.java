/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.SortedSet;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pns.api.mainClasses.Segment;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternBody extends PatternDraw {

    protected DSegment body;
    private SortedSet< Segment> bodySet;

    public PatternBody() {
        super();
        body = new DSegment();
        body.setRadius(2);
        body.setLength(190);
    }

    public Pane getPanel() {
        return panel;
    }

    public Light.Point drawBody(Light.Point pt) {
        body.setColor(Color.GREEN);
        body.setColorH(Color.AZURE);
        body.setStroke(5);

        body.setX(pt.getX());
        body.setY(pt.getY());
        body.setZ(pt.getZ());
        body.setAngle(90);
        body.setIdNo(1);

        panel.getChildren().add(body.getPanel());
        panel.setVisible(false);
        return body.draw();
    }

    public DSegment getBody() {
        return body;
    }

    public void setBodySet(SortedSet<Segment> bodySet) {
        this.bodySet = bodySet;
    }

}
