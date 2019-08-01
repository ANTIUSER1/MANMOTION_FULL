/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.math;

import javafx.scene.effect.Light;

/**
 *
 * @author Movement
 */
public class AffineCalc {

    public static final double radfromDegree = Math.PI / 180;
    public static final double degreefromRad = 180 / Math.PI;

    public static Light.Point addPoints(Light.Point pt0, Light.Point pt1) {
        Light.Point res = new Light.Point();
        res.setX(pt0.getX() + pt1.getX());
        res.setY(pt0.getY() + pt1.getY());
        res.setZ(pt0.getZ() + pt1.getZ());
        return res;
    }
}
