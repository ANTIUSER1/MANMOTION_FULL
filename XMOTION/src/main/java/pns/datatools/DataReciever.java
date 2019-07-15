/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.datatools;

/**
 *
 * @author Movement
 */
public class DataReciever {

    private String data = "";

    private static DataReciever instance;

    public static DataReciever getInstance() {
        if (instance == null) {
            instance = new DataReciever();
        }
        instance.prepareData();
        return instance;
    }

    private DataReciever() {
    }

    public void prepareData() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
