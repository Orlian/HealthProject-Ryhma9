package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

/**
 * DataList-luokka säilöö käyttäjän vastauksien numeeriset arvot
 * @author Joonas Lehtoranta
 * @version 1.2
 */
public class DataList {
    private ArrayList<Integer> dataList;

    /**
     * Konstruktori luo uuden ArrayList-tyyppisen listan
     */
    public DataList(){
        this.dataList = new ArrayList<>();
    }

    /**
     * getData-metodi palauttaa yksittäisen listan arvon indeksin perusteella
     * @param i indeksi
     * @return int arvo kyseiselle listan indeksille
     */
    public int getData(int i){
        return this.dataList.get(i);
    }



}
