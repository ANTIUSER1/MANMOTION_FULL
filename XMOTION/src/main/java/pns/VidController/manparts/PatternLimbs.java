/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts;

import java.util.List;
import pns.api.mainClasses.Segment;

/**
 *
 * @author Movement
 */
public class PatternLimbs extends PatternDraw {

    /**
     * стартовые значения углов
     */
    protected double dTLX = 0;
    protected double dBLX = 0;
    protected double dTRX = 0;
    protected double dBRX = 0;

    protected double dTLY = 0;
    protected double dBLY = 0;
    protected double dTRY = 0;
    protected double dBRY = 0;

    protected double dTLZ = 0;
    protected double dBLZ = 0;
    protected double dTRZ = 0;
    protected double dBRZ = 0;

    protected List<Segment> topL;
    protected List<Segment> bottomL;

    protected List<Segment> topR;
    protected List<Segment> bottomR;

    protected void generateNewCoord(int frame) {
        try {

            dTLX = topL.get(frame).getFixedPoint().getV1();
            dBLX = bottomL.get(frame).getFixedPoint().getV1();
            dTRX = topL.get(frame).getFixedPoint().getV1();
            dBRX = bottomL.get(frame).getFixedPoint().getV1();

            dTLY = topL.get(frame).getFixedPoint().getV2();
            dBLY = bottomL.get(frame).getFixedPoint().getV2();
            dTRY = topL.get(frame).getFixedPoint().getV2();
            dBRY = bottomL.get(frame).getFixedPoint().getV2();

            dTLZ = topL.get(frame).getFixedPoint().getV3();
            dBLZ = bottomL.get(frame).getFixedPoint().getV3();
            dTRZ = topL.get(frame).getFixedPoint().getV3();
            dBRZ = bottomL.get(frame).getFixedPoint().getV3();
        } catch (IndexOutOfBoundsException e) {
        }
    }

}
