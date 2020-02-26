package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

public class DataList {
    private ArrayList<Integer> dataList;

    public DataList(){
        this.dataList = new ArrayList<>();
    }

    public int getData(int i){
        return this.dataList.get(i);
    }


}
