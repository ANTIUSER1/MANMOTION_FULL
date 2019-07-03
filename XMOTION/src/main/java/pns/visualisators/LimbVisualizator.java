/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.visualisators;

import javafx.scene.layout.Pane;
import pns.interfaces.ISupporter;

/**
 *
 * @author Movement
 */
public class LimbVisualizator extends Pane implements ISupporter {

    private SegmentVisualisator topSegment = new SegmentVisualisator();
    private SegmentVisualisator bottomSegment = new SegmentVisualisator();

    public LimbVisualizator() {
        this.getChildren().remove(topSegment);
        this.getChildren().remove(bottomSegment);
        this.getChildren().add(topSegment);
        this.getChildren().add(bottomSegment);

    }

}
