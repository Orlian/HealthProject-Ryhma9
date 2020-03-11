package com.example.healthproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Käyttäjä-luokka, joka sisältää aplikaation käyttäjän oleelliset tiedot ja tallentaa
 * tietyn käyttäjän vastausdatan omaan listaan.
 * Implementoi Serializable-luokkaa, jotta saamme siirrettyä User-luokan olioita Intentien avulla
 * @author Joonas Lehtoranta
 * @version 1.2
 */
public class User implements Serializable {
    private String userName, password;
    private List<Integer> dataList;

    /**
     * User-luokan perusrakentaja, joka asettaa käyttäjätunnuksen ja salasanan käyttäjälle,
     * sekä luo listan johon tulevat vastaukset tallennetaan.
     * @param userName Käyttäjätunnus
     * @param password Salasana
     */
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.dataList = new ArrayList<>();
    }

    /**
     * Jos mahdollistetaan vierailija-tunnusten käyttäminen, niin rakentaja ei tarvitse parametreja
     */
    public User(){
        this.userName = "Guest";
    }

    /**
     * User-luokan nimen palauttaminen
     * @return String userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * User-luokan salasanan palauttaminen
     * @return String password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * User-luokan salasanan asettaminen tai muuttaminen
     * @param password , haluttu uusi salasana
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * User-luokan käyttäjätunnuksen asettaminen tai muuttaminen
     * @param userName , haluttu uusi käyttäjätunnus
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Ylikirjoitettu toString-metodi User-luokan oliolle
     * @return String userName
     */
    @Override
    public String toString(){
        return this.userName;
    }

    /**
     * getDataList palauttaa singletonin käytössä olevan datalistan.
     * @return palauttaa singeltonin käytössä olevan datalistan.
     */

    public List<Integer> getDataList(){
        return this.dataList;
    }


}
