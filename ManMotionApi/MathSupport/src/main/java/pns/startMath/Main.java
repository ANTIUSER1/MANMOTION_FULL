/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.startMath;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

/**
 *
 * @author abrakadabra
 */
public class Main {

    public static void main(String[] args) {
        /*
        double[] xx = ArrayEmmulator.doubleValuesSimpleEmmulator(0, 1.05, 0.05);
        //xx = ArrayNumberUtils.addMidlePT(xx);
        double[] yy = Testing.polynomeValues(xx);
        double[] zz = {0, 1};
        PolynomialFunction pf = new PolynomialFunction(zz);
        Integral integral = new Integral();
        integral.doIntegrateTrap(xx, yy);
        System.out.println(pf);
        double i = integral.doIntegrate(pf, 0, 1);
        System.out.println("i=" + i);
        System.out.println("    ");
        double i0 = 0;
        double[] ii = integral.doIntegrate(pf, xx);
        for (double d : ii) {
            i0 += d;
            System.out.println("  ii=" + d);
        }
        System.out.println("i0=" + i0);
        Interpolate interpolate = new Interpolate();

        double[] a = {0, .1, .2, .3};
        double[] b = {1, 2, 3, 4};
        UnivariateFunction f = interpolate.interpolate(xx, yy);

        //double uint = f.value(.1001);
        // System.out.println("uint " + uint);
        double uint = integral.doIntegrate(f, xx[0], xx[xx.length - 1]);

        System.out.println("uint    ------   " + uint);
         */
    }

    private static PolynomialFunction[] lagr(double[] x, double[] y) {

        if (x.length != y.length) {
            return null;
        }
        if (x.length - 1 < 1) {
            return null;
        }
        double[] unit = {1};
        PolynomialFunction u = new PolynomialFunction(unit);
        System.out.println(" u " + u);
        ;
        PolynomialFunction[] tmp = new PolynomialFunction[x.length];
        for (int k = 0; k < x.length; k++) {

            double[] mm = {-x[k], 1};
            tmp[k] = new PolynomialFunction(mm);
            System.out.println("                tmp[" + k + "]    " + tmp[k]);

        }

        PolynomialFunction[] res = new PolynomialFunction[x.length];

        for (int n = 0; n < tmp.length; n++) {

            res[n] = productPFArr(tmp, x, n);
            double[] d = {y[n]};
            PolynomialFunction pdf = new PolynomialFunction(d);
            //System.out.println("     pdf = " + pdf);
            System.out.println("x[" + n + "] = " + x[n]);
            double[] dd = {1 / res[n].value(x[n])};
            PolynomialFunction pf = new PolynomialFunction(dd);
            res[n] = res[n].multiply(pf);
            res[n] = res[n].multiply(pdf);
            System.out.println(" k " + n + "        res[k]      " + res[n] + "   value=" + res[n].value(x[n]));
        }

        return res;
    }

    private static PolynomialFunction productPFArr(PolynomialFunction[] prod, int from, int to) {
        double[] u = {1};
        PolynomialFunction res = new PolynomialFunction(u);
        for (int n = from; n < to; n++) {
            if (prod[n] != null) {
                res = res.multiply(prod[n]);
            }
            System.out.println("       ------   n " + n + "    res " + res);
        }
        return res;
    }

    private static PolynomialFunction productPFArr(PolynomialFunction[] prod) {
        double[] u = {1};
        PolynomialFunction res = new PolynomialFunction(u);
        for (int n = 0; n < prod.length; n++) {
            if (prod[n] != null) {
                res = res.multiply(prod[n]);
            }
        }
        return res;
    }

    private static PolynomialFunction productPFArr(PolynomialFunction[] prod, double[] x, int exept) {
        double[] u = {1};
        PolynomialFunction res = new PolynomialFunction(u);
        for (int n = 0; n < prod.length; n++) {
            if (prod[n] != null) {
                if (n != exept) {
                    double[] d = {prod[n].value(x[n])};
                    PolynomialFunction pp = new PolynomialFunction(d);
                    System.out.println("      d=" + pp);
                    res = res.multiply(prod[n]);
                }
            }
        }
        return res;
    }

    private static PolynomialFunction chebysh(PolynomialFunction[] lag) {
        double[] z = {0};
        PolynomialFunction res = new PolynomialFunction(z);
        for (PolynomialFunction f : lag) {
            res = res.add(f);
        }
        return res;
    }

}
