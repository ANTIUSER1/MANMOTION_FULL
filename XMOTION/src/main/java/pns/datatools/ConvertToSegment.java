/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

import java.util.SortedSet;
import pns.api.fileimports.ConvertorUtil;
import pns.api.mainClasses.Segment;
import pns.api.mainClasses.boxies.SimpleSegmentContainer;

/**
 *
 * @author Movement
 */
public class ConvertToSegment {

    private DataReceiver dataReceiver = DataReceiver.getInstance();

    private ConvertToSegment() {
    }
    private static ConvertToSegment instance;

    public static ConvertToSegment getInstance() {
        if (instance == null) {
            synchronized (ConvertToSegment.class) {
                if (instance == null) {
                    instance = new ConvertToSegment();
                }
            }
        }
        return instance;
    }

    public void convert(String selectedFileContent) {

        SortedSet<Segment> segmSet = ConvertorUtil.convertData(selectedFileContent, 300.3);
        SimpleSegmentContainer ssc = new SimpleSegmentContainer();

    }

}
