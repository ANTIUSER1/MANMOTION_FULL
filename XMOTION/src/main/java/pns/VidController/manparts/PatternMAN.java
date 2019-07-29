/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.List;
import pns.VidController.manparts.motions.MotionHands;
import pns.VidController.manparts.motions.MotionLegs;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.datatools.ConvertToHands;
import pns.datatools.ConvertToLegs;
import pns.datatools.ConvertToMan;
import pns.datatools.DataReciever;
import pns.drawables.DLimb;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class PatternMAN {

    protected DataReciever dataReciever = DataReciever.getInstance();

    protected Man theMan;

    protected MotionLegs patternLeg;// = MotionLegs.getInstance();
    protected MotionHands patternHand;//= MotionHands.getInstance();

    protected double[] totalRotationsLimb = new double[4];

    protected ConvertToMan ctoMan;//= ConvertToMan.getInstance();
    protected ConvertToHands ctoHands;
    protected ConvertToLegs ctoLegs;

    protected List<Segment> mover;

    protected List<Segment> topL;
    protected List<Segment> bottomL;

    protected List<Segment> topR;
    protected List<Segment> bottomR;

    protected DLimb[] limbs;
    protected DSegment limb;
}
