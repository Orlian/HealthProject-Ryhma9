package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

import static com.example.healthproject.MainActivity.EXTRA_NEED_LOGIN;

public class LoginActivity extends AppCompatActivity {
    private User testUser;
    private boolean loginStatus;
    private HashMap<Date, User> hashMap;
    private UserList userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Jos käyttäjä ei ole kirjautunut sisään, näytetään login/register ruudun yllä virheviesti
        //Mutta vain silloin, jos käyttäjä on yrittänyt päästä päävalikosta kysymyksiin kirjautumatta!
        if(!getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE).getBoolean("LOGIN_STATUS", true) &&
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
            userList = UserList.getInstance();
            for(int i = 0; i < userList.getUserList().size(); i++){
                //Ideana on käydä läpi kaikki user-listan user-olioiden nimet ja verrata niitä inputtiin
                this.testUser = userList.getUser(i);
                if(this.testUser.getUserName().equals(userInput)){
                    loginStatus = true;
                    SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                    SharedPreferences.Editor loginEdit = loginPrefs.edit();
                    loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
                    loginEdit.commit();
                    Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                    loginSuccess.putExtra("Active_user", this.testUser);
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
            //Tarkista tässä välissä ettei kyseistä käyttäjää ole jo luotuna
            loginStatus = true;
            SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
            SharedPreferences.Editor loginEdit = loginPrefs.edit();
            loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
            loginEdit.commit();
            User user = new User(userInputName, userInputPassword);
            userList.getUserList().add(user);
            Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
            loginSuccess.putExtra("Active_user", user);
            startActivity(loginSuccess);

        }
    }
}
