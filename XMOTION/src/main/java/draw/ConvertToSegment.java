/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.util.SortedSet;
import java.util.TreeSet;
import pns.api.fileimports.ConvertingToSegment;
import pns.api.mainClasses.Point9;
import pns.api.mainClasses.Segment;

/**
 *
 * @author Movement
 */
public class ConvertToSegment {

    private Segment segment;

    private SortedSet<Point9> point9TreeSet = new TreeSet<Point9>();

    private ConvertToSegment() {
    }
    private static ConvertToSegment instance;

    public static ConvertToSegment getInstance() {
        if (instance == null) {
            instance = new ConvertToSegment();
        }

        return instance;
    }

    public Segment getSegment() {
        return segment;
    }

    public SortedSet<Point9> getPoint9TreeSet() {
        return point9TreeSet;
    }

    public void convert(String data) {
        point9TreeSet.clear();
        if (data.trim().length() > 0) {
            ConvertingToSegment cts = new ConvertingToSegment();
            Segment segment = cts.convert(data);
            segment.calcData();
            point9TreeSet.addAll(segment.getPoint9TreeSet());
        }
    }
}
