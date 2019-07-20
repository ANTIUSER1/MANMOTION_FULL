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
public class ConvertToBody extends ConvertToMan {

    private static ConvertToBody instance;
    private DSegment limb;

    private ConvertToBody() {
    }

    private ConvertToBody(Man m) {
        man = m;
        limb = takeLimb();
    }

    public DSegment getLimb() {
//        SetArrayDisplayUtil.setDisplay(limb.getMoverSet());

        return limb;
    }

    public static ConvertToBody getInstance() {

        if (instance == null) {
            synchronized (ConvertToBody.class) {
                if (instance == null) {
                    instance = new ConvertToBody();
                }
            }
        }
        return instance;
    }

    public static ConvertToBody getInstance(Man m) {
        if (instance == null) {
            synchronized (ConvertToBody.class) {
                if (instance == null) {
                    instance = new ConvertToBody(m);
                }
            }
        }
        return instance;
    }

    private DSegment takeLimb() {

        if (man != null) {
            DSegment res = new DSegment();
            res.setMoverSet(man.getBody().getSegment());
            //          SetArrayDisplayUtil.setDisplay(res.getMoverSet());
            return res;
        }
        return null;
    }

}
