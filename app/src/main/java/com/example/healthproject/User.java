package com.example.healthproject;

/**
 * Käyttäjä-luokka, joka sisältää aplikaation käyttäjän oleelliset tiedot
 * @author Joonas Lehtoranta
 * @version 1.0
 */
public class User {
    private String userName, password;

    /**
     * User-luokan perusrakentaja, joka asettaa käyttäjätunnuksen ja salasanan käyttäjälle
     * @param userName Käyttäjätunnus
     * @param password Salasana
     */
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
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
        return userName;
    }

    /**
     * User-luokan salasanan palauttaminen
     * @return String password
     */
    public String getPassword() {
        return password;
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

}
