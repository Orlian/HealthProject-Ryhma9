package com.example.healthproject;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> userList;

    public UserList(){
        this.userList = new ArrayList<>();
    }

    public List<User> getUserList(){
        return this.userList;
    }

    public String getUser(int i){
        return this.userList.get(i).getUserName();
    }


}
