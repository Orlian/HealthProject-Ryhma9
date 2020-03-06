package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton-luokka, joka sisältää listan aplikaatioon rekisteröityneistä käyttäjistä
 * @author Joonas Lehtoranta
 * @version 1.3
 */
class UserList {
    private List<User> userList;
    public static final UserList ourInstance = new UserList();


    static UserList getInstance(){
        return ourInstance;
    }

    private UserList(){
        userList = new ArrayList<>();
    }

    public User getUser(int i){
        return this.userList.get(i);
    }

    public List<User> getUserList(){
        return this.userList;
    }

}
