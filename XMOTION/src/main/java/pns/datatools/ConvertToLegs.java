/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

import pns.api.mainClasses.Limb;
import pns.api.mainClasses.Man;
import pns.drawables.DLimb;

/**
 *
 * @author Movement
 */
public class ConvertToLegs extends ConvertToMan {

    private static ConvertToLegs instance;
    private DLimb[] limbs;

    private ConvertToLegs() {
    }

    private ConvertToLegs(Man m) {
        System.out.println("   m   ------------>>   (m == null):  " + (m == null));
        man = m;
        limbs = takeLegs();
    }

    public static ConvertToLegs getInstance() {

        if (instance == null) {
            synchronized (ConvertToLegs.class) {
                if (instance == null) {
                    instance = new ConvertToLegs();
                }
            }
        }
        return instance;
    }

    public static ConvertToLegs getInstance(Man m) {
        System.out.println("   m   ------------>>" + (m == null));

        if (instance == null) {
            synchronized (ConvertToLegs.class) {
                if (instance == null) {
                    instance = new ConvertToLegs(m);
                }
            }
        }
        return instance;
    }

    public DLimb[] getLimbs() {
        return limbs;
    }

    private DLimb takeLeftLeg() {

        if (man != null) {
            Limb resTMP = new Limb();
            DLimb res = new DLimb();

            resTMP = man.getLegLeft();

            res.setSegmentSetTop(resTMP.getSegmentSetTop());
            res.setSegmentSetBottom(resTMP.getSegmentSetBottom());

            return res;
        }
        return null;
    }

    private DLimb takeRightLeg() {
        if (man != null) {
            Limb resTMP = new Limb();
            DLimb res = new DLimb();
            resTMP = man.getLegRight();

            res.setSegmentSetTop(resTMP.getSegmentSetTop());
            res.setSegmentSetBottom(resTMP.getSegmentSetBottom());

            return res;
        }
        return null;
    }

    private DLimb[] takeLegs() {
        DLimb[] res = new DLimb[2];
        DLimb left = takeLeftLeg();
        DLimb right = takeRightLeg();
        System.out.println("     right==null " + (right == null));
        System.out.println("     left==null " + (left == null));

        res[0] = right;
        res[1] = left;
        return res;
    }

}
