/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatools;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import pns.api.mainClasses.Point9;

/**
 *
 * @author Movement
 */
public class DataReceiver {

    private SortedSet<Point9> point9TreeSet = new TreeSet<Point9>();
    private List<Point9> point9List = new ArrayList<Point9>();

    private static DataReceiver instance;

    public static DataReceiver getInstance() {
        if (instance == null) {
            instance = new DataReceiver();
        }
        instance.prepareData();
        return instance;
    }

    private DataReceiver() {
    }

    public void prepareData() {
        point9List.clear();
        point9List.addAll(instance.getPoint9TreeSet());
        System.out.println(point9List.size());
    }

    public List<Point9> getPoint9List() {
        return point9List;
    }

    public SortedSet<Point9> getPoint9TreeSet() {
        return point9TreeSet;
    }
}
