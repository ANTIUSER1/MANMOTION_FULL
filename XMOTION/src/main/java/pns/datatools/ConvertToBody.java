/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

import pns.VidController.manparts.PatternBody;
import pns.api.mainClasses.Man;

/**
 *
 * @author Movement
 */
public class ConvertToBody extends ConvertToMan {

    private static ConvertToBody instance;
    private PatternBody limbs;

    private ConvertToBody() {
    }

    private ConvertToBody(Man m) {
        System.out.println("   m   ------------>>   (m == null):  " + (m == null));
        man = m;
        limbs = takeLimb();
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
        System.out.println("   m   ------------>>" + (m == null));

        if (instance == null) {
            synchronized (ConvertToBody.class) {
                if (instance == null) {
                    instance = new ConvertToBody(m);
                }
            }
        }
        return instance;
    }

    private PatternBody takeLimb() {

        if (man != null) {
            PatternBody res = new PatternBody();

            res.setBodySet(man.getBody().getSegment());
            return res;
        }
        return null;
    }

}
