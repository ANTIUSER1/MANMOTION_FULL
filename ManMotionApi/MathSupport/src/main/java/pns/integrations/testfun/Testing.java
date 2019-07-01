/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.integrations.testfun;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

/**
 *
 * @author Movement
 */
public class Testing {

    public static double[] polynomeValues(double[] args) {
        double[] c = {0, 0, 1};
        PolynomialFunction pf = new PolynomialFunction(c);

        double[] res = new double[args.length];
        for (int k = 0; k < args.length; k++) {
            res[k] = pf.value(args[k]);
        }
        System.out.println("pf = " + pf);
        return res;

    }
}
