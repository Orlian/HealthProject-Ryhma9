package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka, joka sisältää listan aplikaatioon rekisteröityneistä käyttäjistä
 */
public class UserList {
    private List<User> userList;

    public void UserList(){
        this.userList = new ArrayList<>();
    }

    public User getUser(int i){
        return this.userList.get(i);
    }

    public List<User> getUserList(){
        return this.userList;
    }
}
