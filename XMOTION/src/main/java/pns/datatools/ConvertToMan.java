/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import pns.api.converting.SegmentSeparator;
import pns.api.fileimports.ConvertorUtil;
import pns.api.fileimports.FileCalculator;
import pns.api.formations.ManFormation;
import pns.api.mainClasses.Man;
import pns.api.mainClasses.Segment;
import pns.api.mainClasses.boxies.SimpleSegmentContainer;

/**
 *
 * @author Movement
 */
public class ConvertToMan {

    private DataReciever dataReceiver = DataReciever.getInstance();

    public ConvertToMan() {

    }
    private static ConvertToMan instance;
    public Man man;

    public Man getMan() {
        return man;
    }

    public List<Man> convert(String data) {
        if (data == null) {
            return null;
        }
        if (data.length() < 10) {
            return null;
        }
        if (man == null) {

            System.out.println("----------data-----------    ");

            SortedSet<Segment> segmSet = ConvertorUtil.convertData(data, 300.3);
            //SetArrayDisplayUtil.setDisplay(segmSet);
            System.out.println(""
                    + "    MAN GENERATE!!"
                    + "   segmSet.size()  " + segmSet.size());
            SimpleSegmentContainer ssc = new SimpleSegmentContainer();
            ssc.setSegment(segmSet);
            Map<String, SortedSet<Segment>> segmMap = SegmentSeparator.separate(segmSet);
//            SetArrayDisplayUtil.setDisplay(segmMap);
            System.out.println("   segmMap.size()  " + segmMap.size());
            segmMap = FileCalculator.integrate(segmMap);

            List<Map<String, SortedSet<Segment>>> listOfMapsSS = SegmentSeparator.separateToList(segmMap);
            man = new ManFormation().generateMan(segmMap);
            //   SetArrayDisplayUtil.setDisplay(man);
//            System.out.println("   man.getHead().getSegment().size() " + man.getHead().getSegment().size());
//            System.out.println("  man.getBody().getSegment().size() " + man.getBody().getSegment().size());
//            System.out.println("  man.getHandLeft().getSegmentSetTop().size() " + man.getHandLeft().getSegmentSetTop().size());
//            System.out.println("---");
//            System.out.println("---");
            if (man != null) {
                List<Man> res = new ArrayList<>();
                res.add(man);
            }
        }

        return null;
    }

}
