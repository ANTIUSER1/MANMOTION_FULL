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

    protected ConvertToMan() {

    }
    private static ConvertToMan instance;
    protected Man man;

    public static ConvertToMan getInstance() {
        if (instance == null) {
            synchronized (ConvertToMan.class) {
                if (instance == null) {
                    instance = new ConvertToMan();
                }
            }
        }
        return instance;
    }

    public Man getMan() {
        return man;
    }

    public List<Man> convert(String data) {
        if (man == null) {
            System.out.println("data-----------    ");

            SortedSet<Segment> segmSet = ConvertorUtil.convertData(data, 300.3);
            System.out.println(""
                    + "    MAN GENERATE!!"
                    + "");
            SimpleSegmentContainer ssc = new SimpleSegmentContainer();
            ssc.setSegment(segmSet);
            Map<String, SortedSet<Segment>> segmMap = SegmentSeparator.separate(segmSet);
            segmMap = FileCalculator.integrate(segmMap);
            List<Map<String, SortedSet<Segment>>> listOfMapsSS = SegmentSeparator.separateToList(segmMap);
            man = new ManFormation().generateMan(segmMap);
            List<Man> res = new ArrayList<>();
            res.add(man);
        }

        return null;
    }

}
