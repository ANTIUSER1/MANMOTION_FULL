/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatools;

import pns.api.exeptions.SegmentExeption;
import pns.api.fileimports.ConvertingToSegment;
import pns.api.mainClasses.Segment;

/**
 *
 * @author Movement
 */
public class ConvertToSegment {

    private Segment segment;
    private DataReceiver dataReceiver = DataReceiver.getInstance();

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

    public void convert(String data) {
        dataReceiver.getPoint9TreeSet().clear();
        if (data.trim().length() > 0) {
            ConvertingToSegment cts = new ConvertingToSegment();
            Segment segment = cts.convert(data);
            try {
                segment.calcData();
            } catch (SegmentExeption ex) {
//                Logger.getLogger(ConvertToSegment.class.getName()).log(Level.SEVERE, null, ex);
            }
            dataReceiver.getPoint9TreeSet().addAll(segment.getPoint9TreeSet());
        }
        System.out.println("-------------");
        System.out.println("");
        System.out.println("");
        System.out.println("     dataReceiver.getPoint9TreeSet().size()  " + dataReceiver.getPoint9TreeSet().size());
        System.out.println("");
        System.out.println("");
        System.out.println("-----");
    }
}
