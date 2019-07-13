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
public class PatternBody extends PatternDraw {

    private DSegment body;

    public PatternBody() {
        super();
        body = new DSegment();
    }

    public Pane getPanel() {
        return panel;
    }

    public Light.Point drawBody(Light.Point pt) {
        body.setColor(Color.GREEN);
        body.setColorH(Color.AZURE);
        body.setRadius(2);
        body.setLength(190);

        body.setX(pt.getX());
        body.setY(pt.getY());
        body.setZ(pt.getZ());
        body.setAngle(90);

        panel.getChildren().add(body.getPanel());

        return body.draw();
    }
}
