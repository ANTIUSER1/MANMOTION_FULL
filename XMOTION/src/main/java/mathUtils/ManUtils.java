/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathUtils;

import java.util.Set;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.api.mainClasses.boxies.ManHead;
import pns.api.mainClasses.boxies.SegmentBox;

/**
 *
 * @author Movement
 */
public class ManUtils {

    private static ManHead head;
    private static SegmentBox body;

    public static double calcTotalRotationHead(Man man) {
        //SetArrayDisplayUtil.setDisplay(man);
        head = man.getHead();
        Set<Segment> headSGSet = head.getSegment();
        return calcTotalAngle(headSGSet);
    }

    public static double calcTotalRotationBody(Man man) {
        //SetArrayDisplayUtil.setDisplay(man);
        body = man.getBody();
        Set<Segment> headSGSet = body.getSegment();
        return calcTotalAngle(headSGSet);
    }

    public static double[] calcTotalRotationHandsSegmentList(Man man) {
        //SetArrayDisplayUtil.setDisplay(man);
        Set<Segment> RTLimb = man.getHandRight().getSegmentSetTop();
        Set<Segment> LTLimb = man.getHandLeft().getSegmentSetTop();
        Set<Segment> RBLimb = man.getHandRight().getSegmentSetBottom();
        Set<Segment> LBLimb = man.getHandLeft().getSegmentSetBottom();

        double[] res = new double[4];
        res[0] = calcTotalAngle(RTLimb);
        res[1] = calcTotalAngle(LTLimb);
        res[2] = calcTotalAngle(RBLimb);
        res[3] = calcTotalAngle(LBLimb);

        return res;
    }

    public static double[] calcTotalRotationLegsSegmentList(Man man) {
        //SetArrayDisplayUtil.setDisplay(man);
        Set<Segment> RTLimb = man.getLegRight().getSegmentSetTop();
        Set<Segment> LTLimb = man.getLegLeft().getSegmentSetTop();
        Set<Segment> RBLimb = man.getLegRight().getSegmentSetBottom();
        Set<Segment> LBLimb = man.getLegLeft().getSegmentSetBottom();

        double[] res = new double[4];
        res[0] = calcTotalAngle(RTLimb);
        res[1] = calcTotalAngle(LTLimb);
        res[2] = calcTotalAngle(RBLimb);
        res[3] = calcTotalAngle(LBLimb);

        return res;
    }

    private static double calcTotalAngle(Set<Segment> dataSet) {
        double res = 0;
        for (Segment sg : dataSet) {
            res += sg.getFixedPoint().getV1();
        }
        return res;
    }

    public double calcTotalAngle() {
        double res = 0;
        //   System.out.println(moverSet.size() + "     calcTotalAngle     -----------    **************    -----  " + res);
//        for (Segment sg : moverSet) {
//            System.out.println(moverSet.size() + "" + res);
//            res += sg.getFixedPoint().getV1();
//        }
        return res;
    }

    private static void controlMan(Man man) {
        System.out.println("");
    }
}
