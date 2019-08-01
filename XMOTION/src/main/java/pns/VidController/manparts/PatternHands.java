/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import pns.api.mainClasses.Man;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class PatternHands extends PatternLimbs {

    private int id = 0;

    protected Man theMan;
    protected DLimb RightHand;
    protected DLimb LeftHand;

    public PatternHands(Man man) {
        super();
        id = pns.utils.numbers.RInts.rndInt(1000, 4999);
        theMan = man;
        RightHand = new DLimb();
        LeftHand = new DLimb();
        RightHand.setTopLength(60);
        RightHand.setBottomLength(70);
        LeftHand.setBottomLength(70);
        LeftHand.setTopLength(60);

    }

    public Pane getPanel() {
        return panel;
    }

    public void drawHands(Light.Point pt) {

        LeftHand.setZ(pt.getZ());
        LeftHand.setAngle(0);

        LeftHand.getTop().setAngle(90 + 45);
        LeftHand.getBottom().setAngle(90 + 30);

        RightHand.setAngle(0);

        RightHand.getTop().setAngle(30);
        RightHand.getBottom().setAngle(60);

        LeftHand.draw();
        RightHand.draw();

        panel.getChildren().clear();
        panel.getTransforms().add(new Translate(pt.getX(), pt.getY()));

        panel.getChildren().add(RightHand.getPanel());
        panel.getChildren().add(LeftHand.getPanel());
        //panel.setVisible(false);
    }

}
