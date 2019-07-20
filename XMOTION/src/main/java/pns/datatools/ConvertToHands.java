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
public class ConvertToHands extends ConvertToMan {

    private static ConvertToHands instance;
    private DLimb[] limbs;

    private ConvertToHands() {
    }

    public ConvertToHands(Man m) {
        man = m;
        limbs = takeLegs();
    }

    public static ConvertToHands getInstance() {

        if (instance == null) {
            synchronized (ConvertToLegs.class) {
                if (instance == null) {
                    instance = new ConvertToHands();
                }
            }
        }
        return instance;
    }

    public static ConvertToHands getInstance(Man m) {
        if (instance == null) {
            synchronized (ConvertToHands.class) {
                if (instance == null) {
                    instance = new ConvertToHands(m);
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

            resTMP = man.getHandLeft();

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
            resTMP = man.getHandRight();

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

        res[0] = right;
        res[1] = left;
        return res;
    }

}
