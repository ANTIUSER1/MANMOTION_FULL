/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.tools;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

/**
 *
 * @author Movement
 */
public class Interpolate {

    public UnivariateFunction interpolate(double[] x, double[] y) {
        UnivariateInterpolator interpolator = new SplineInterpolator();
        return interpolator.interpolate(x, y);
    }
}
