/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternHead extends PatternDraw {

    private DSegment head;

    public PatternHead() {
        super();
        head = new DSegment();

    }

    public Pane getPanel() {
        return panel;
    }

    public Light.Point drawHead(Light.Point pt) {
        head.setColor(Color.GREEN);
        head.setColorH(Color.BEIGE);
        head.setRadius(22);
        head.setLength(7);

        head.setX(pt.getX());
        head.setY(pt.getY());
        head.setZ(pt.getZ());
        head.setAngle(90);

        panel.getChildren().add(head.getPanel());
        return head.draw();

    }

}
