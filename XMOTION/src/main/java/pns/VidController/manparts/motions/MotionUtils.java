/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.VidController.manparts.motions;

import javafx.scene.effect.Light;
import mathUtils.AffineCalc;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class MotionUtils {

    private static void manuallyMove(DLimb dlimb, double d0, double d1) {

        //reDrawLegs(dlimb, d0, d1);
        System.out.println("rotate on  " + d0 + "     and   " + d1);

    }

    public static Light.Point recalcDSegmentEnd(Light.Point pt, double len, double alpha, double theta) {
        Light.Point res = new Light.Point();

        System.out.println("      CALC START ");
        System.out.println("    length:=   " + len + "  alpha " + alpha);
        System.out.println("    length:=   " + len + "  alpha in rad " + (AffineCalc.radfromDegree * alpha));

        res.setX(-len * Math.cos(AffineCalc.radfromDegree * alpha));
        System.out.println("  res.getX() " + res.getX());

        res.setY(-len * Math.sin(AffineCalc.radfromDegree * alpha));
        System.out.println("  res.getY() " + res.getY());

        res.setZ(-len * Math.sin(AffineCalc.radfromDegree * theta));

        System.out.println("      CALC    END ");
        return res;
    }

    public static Light.Point recalcDSegmentEnd(double len, double alpha, double theta) {
        Light.Point res = new Light.Point();

        System.out.println("      CALC START ");
        System.out.println("    length:=   " + len + "  alpha " + alpha);
        System.out.println("    length:=   " + len + "  alpha in rad " + (AffineCalc.radfromDegree * alpha));

        res.setX(-len * Math.cos(AffineCalc.radfromDegree * alpha));
        System.out.println("  res.getX() " + res.getX());

        res.setY(-len * Math.sin(AffineCalc.radfromDegree * alpha));
        System.out.println("  res.getY() " + res.getY());

        res.setZ(-len * Math.sin(AffineCalc.radfromDegree * theta));

        System.out.println("      CALC    END ");
        return res;
    }

}
