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

    private UserList(){
            userList = new ArrayList<>();
    }

    public User getUser(int i){
        return this.userList.get(i);
    }

    public List<User> getUserList(){
        return userList;
    }

}
