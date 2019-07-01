/**
 * Интегрирование
 */
package pns.tools;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.StepFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import pns.utils.array.ArrayNumberUtils;

/**
 *
 * @author Movement
 */
public class Integral {

    public double doIntegrate(double[] x, double[] y, double step) {

        double res = 0;
        x = ArrayNumberUtils.sortArray(x);
        StepFunction f = new StepFunction(x, y);
        double t = x[0];
        while (t < x[x.length - 1] + step) {
            res += f.value(t) * step;
            t += step;
        }
        System.out.println("             res   " + res);
        return res;

    }

    public double[] doIntegrate(UnivariateFunction uf, double[] x) {
        double[] xx = new double[x.length + 1];
        double[] res = new double[xx.length];
        double rest = x[x.length - 1] - x[x.length - 2];
        xx[xx.length - 1] = x[x.length - 1] + rest;

        for (int k = 0; k < x.length; k++) {
            xx[k] = x[k];
        }

        SimpsonIntegrator simpsonIntegrator = new SimpsonIntegrator();
        for (int k = 1; k < xx.length; k++) {
            try {
                res[k] = simpsonIntegrator.integrate(100000, uf, xx[k - 1], xx[k]);
            } catch (Exception e) {
                System.out.println("k= " + k);
            }
        }
        return res;
    }

    /**
     * интеграл трапециями ------------- ручной
     *
     * @param x
     * @param y
     * @return
     */
    public double doIntegrateTrap(double[] x, double[] y) {

        double res = 0;
        x = ArrayNumberUtils.sortArray(x);
        StepFunction f = new StepFunction(x, y);

        for (int k = 0; k < x.length - 1; k++) {
            double step = x[k + 1] - x[k];
            double t0 = x[k];
            double t1 = x[k + 1];
            double f0 = f.value(t0);
            double f1 = f.value(t1);
            res += 0.5 * (f0 + f1) * step;

        }

        System.out.println("             res   " + res);
        return res;

    }

    /**
     * интеграл симпсон-методом
     *
     * @param uf
     * @param from
     * @param to
     * @return
     */
    public double doIntegrate(UnivariateFunction uf, double from, double to) {
        SimpsonIntegrator simpsonIntegrator = new SimpsonIntegrator();
        return simpsonIntegrator.integrate(100000, uf, from, to);
    }
}
