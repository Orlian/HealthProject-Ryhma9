package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

/**
 * DataList-luokka säilöö käyttäjän vastauksien numeeriset arvot
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

    /**
     * getDataList-metodi palauttaa tietyn lista-olion
     * @return List<Integer> this.dataList
     */
    public List<Integer> getDataList(){
        return this.dataList;
    }

    /**
     * removeDataListEntry-metodi poistaa tietyn indeksin kohdalla olevan arvon listalta
     * @param i indeksi
     */
    public void removeDataListEntry(int i){
        this.dataList.remove(i);
    }

    /**
     * clearDataList-metodi tyhjentää koko käyttäjän datalistan
     */
    public void clearDataList(){
        this.dataList.clear();
    }


}
