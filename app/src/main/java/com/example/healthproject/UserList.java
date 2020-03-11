package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton-luokka, joka sisältää listan aplikaatioon rekisteröityneistä käyttäjistä
 * @author Joonas Lehtoranta
 * @version 1.4
 */
class UserList {
    private List<User> userList;
    private static UserList ourInstance;


    /**
     * Singleton-luokan instanssin luonti, joka on synkronoitu vain yhden säikeen käytettäväksi kerrallaan
     * @return palauttaa luodun singleton istanssin.
     */

    public static UserList getInstance(){
        if(ourInstance == null){
            synchronized (UserList.class){
                if(ourInstance == null){
                    ourInstance = new UserList();
                }
            }
        }
        return ourInstance;
    }

    /**
     * Singleton luokka Userlistin konstruktori, joka luo uuden Arraylistin userList.
     */
    private UserList(){
            userList = new ArrayList<>();
    }

    /**
     * getUser metodi joka palauttaa kokonaisluku indeksiä vastaanvan käyttäjän.
     * @param i indeksi
     * @return User luokan olion.
     */

    public User getUser(int i){
        return this.userList.get(i);
    }

    /**
     * getUserList metodi palauttaa käyttäjälistan.
     * @return palauttaa käyttäjälistan.
     */

    public List<User> getUserList(){
        return userList;
    }

}
