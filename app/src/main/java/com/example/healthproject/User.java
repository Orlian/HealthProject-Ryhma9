package com.example.healthproject;

public class User {
    private String userName, password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    //Jos mahdollistetaan vierailija-tunnusten käyttäminen*
    public User(){
        this.userName = "Guest";
    }

    /**
     * User-luokan tietojen muokkaus ja palautus
     * @return
     */
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString(){
        return this.userName;
    }
    /**
     * User-luokan muut metodit
     * @methods
     */

}
