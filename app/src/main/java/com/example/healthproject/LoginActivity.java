package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;

import static com.example.healthproject.MainActivity.EXTRA_NEED_LOGIN;

/**
 * Kirjautumisesta ja rekisteröinnistä vastaava aktiviteetti, joka kutsuu ja muokkaa käyttäjälistaa,
 * sekä asettaa aktiivisen käyttäjän SharedPreferences tiedostoon.
 * @author Joonas Lehtoranta
 * @version 1.8
 */
public class LoginActivity extends AppCompatActivity {
    private User testUser;
    private boolean loginStatus;
    private UserList userList = UserList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Jos käyttäjä ei ole kirjautunut sisään, näytetään login/register ruudun yllä virheviesti
        //Mutta vain silloin, jos käyttäjä on yrittänyt päästä päävalikosta kysymyksiin kirjautumatta!
        if(loginStatus = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE).getBoolean("LOGIN_STATUS", true) &&
        getIntent().getBooleanExtra(EXTRA_NEED_LOGIN, true)){

            TextView tv = findViewById(R.id.loginErrorMessage);
            tv.setText(R.string.not_logged_in);
        }


    }
    public void onClick(View v){
        if(v == findViewById(R.id.loginButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi ja salasana täsmäävät
            EditText userNameInput = (EditText) findViewById(R.id.usernameField);
            String userInput = userNameInput.getText().toString();
            EditText userPasswordInput = (EditText) findViewById(R.id.passwordField);
            String userPassword = userPasswordInput.getText().toString();
            //Tarkista vertaamalla käyttäjien hashMappiin onko kyseistä käyttäjänimeä rekisteröity
            //HUOM! EI TOIMI VIELÄ, KOSKA USERLIST ON VIELÄ NULL OBJECT REFERENCE
            for(int i = 0; i < UserList.getInstance().getUserList().size(); i++){
                //Ideana on käydä läpi kaikki user-listan user-olioiden nimet ja verrata niitä inputtiin
                testUser = UserList.getInstance().getUser(i);
                if(testUser.getUserName().equals(userInput) && testUser.getPassword().equals(userPassword)){
                    loginStatus = true;
                    SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                    SharedPreferences.Editor loginEdit = loginPrefs.edit();
                    loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
                    Gson gson = new Gson();
                    String json = gson.toJson(testUser);
                    loginEdit.putString("ACTIVE_USER", json);
                    loginEdit.apply();
                    Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loginSuccess);
                }
            }
            TextView tv = findViewById(R.id.loginErrorMessage);
            //HUOM! Korvaa teksti resurssilla
            tv.setText("Invalid username or password");



        }else if(v == findViewById(R.id.registerButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi on vapaana
            //Luo User-olion ja samalla sille oman dataListan
            EditText userRegisterInput = (EditText) findViewById(R.id.usernameField);
            String userInputName = userRegisterInput.getText().toString();
            EditText userPasswordInput = (EditText) findViewById(R.id.passwordField);
            String userInputPassword = userPasswordInput.getText().toString();
            testUser = new User(userInputName, userInputPassword);
            //Tarkista tässä välissä ettei kyseistä käyttäjää ole jo luotuna
            if(!UserList.getInstance().getUserList().contains(testUser)) {
                loginStatus = true;
                SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                SharedPreferences.Editor loginEdit = loginPrefs.edit();
                loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
                Gson gson = new Gson();
                String json = gson.toJson(testUser);
                loginEdit.putString("ACTIVE_USER", json);
                loginEdit.commit();
                UserList.getInstance().getUserList().add(testUser);
                Log.v("DEBUG5", "Aktiivinen käyttäjä:" + testUser.getUserName() + " Salasana: " + testUser.getPassword());
                Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginSuccess);
            }

        }
    }
}
