/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

import pns.api.mainClasses.Man;
import pns.drawables.DSegment;

/**
 *
 * @author Movement
 */
public class ConvertToHead extends ConvertToMan {

    private static ConvertToHead instance;
    private DSegment limb;

    private ConvertToHead() {
    }

    private ConvertToHead(Man m) {
        man = m;
        limb = takeLimb();
    }

    public DSegment getLimb() {
//        SetArrayDisplayUtil.setDisplay(limb.getMoverSet());

        return limb;
    }

    public static ConvertToHead getInstance() {

        if (instance == null) {
            synchronized (ConvertToHead.class) {
                if (instance == null) {
                    instance = new ConvertToHead();
                }
            }
        }
        return instance;
    }

    public static ConvertToHead getInstance(Man m) {
        if (instance == null) {
            synchronized (ConvertToHead.class) {
                if (instance == null) {
                    instance = new ConvertToHead(m);
                }
            }
        }
        return instance;
    }

    private DSegment takeLimb() {

        if (man != null) {
            DSegment res = new DSegment();
            res.setMoverSet(man.getHead().getSegment());
            //          SetArrayDisplayUtil.setDisplay(res.getMoverSet());
            return res;
        }
        return null;
    }

}
