package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    private List<Integer> userDataList;
    private UserList userList;
    private List<User> users;

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
            userList = UserList.getInstance();
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
                    loginEdit.commit();
                    Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loginSuccess);
                    break;
                } else if(i == UserList.getInstance().getUserList().size() -1
                        && (!testUser.getUserName().equals(userInput) || !testUser.getPassword().equals(userPassword))){
                    TextView tv = findViewById(R.id.loginErrorMessage);
                    tv.setText(getString(R.string.invalid_login));
                    tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                }
            }
            if(userList.getUserList().isEmpty()){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(getString(R.string.invalid_login));
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
            }




        }else if(v == findViewById(R.id.registerButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi on vapaana
            //Luo User-olion ja samalla sille oman dataListan
            EditText userRegisterInput = (EditText) findViewById(R.id.usernameField);
            String userInputName = userRegisterInput.getText().toString();
            EditText userPasswordInput = (EditText) findViewById(R.id.passwordField);
            String userInputPassword = userPasswordInput.getText().toString();
            if((userInputName.equals("") && userInputPassword.equals("")) || (userInputName.equals("") || userInputPassword.equals(""))){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(getString(R.string.login_error_empty_data));
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            if((userInputName.contains(" ") && userInputPassword.contains(" ")) || (userInputName.contains(" ") || userInputPassword.contains(" "))){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(getString(R.string.login_error_empty_data));
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            testUser = new User(userInputName, userInputPassword);

            SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {}.getType();
            String gsonString = gson.toJson(users, type);
            String json2 = loginPrefs.getString("USER_LIST", gsonString);
            users = new Gson().fromJson(json2, new TypeToken<List<Object>>() {}.getType());

            userList = UserList.getInstance();
            //Tarkista tässä välissä ettei kyseistä käyttäjää ole jo luotuna
            if(!userList.getUserList().contains(testUser)) {
                loginStatus = true;
                loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                SharedPreferences.Editor loginEdit = loginPrefs.edit();
                loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
                gson = new Gson();
                String json = gson.toJson(testUser);
                loginEdit.putString("ACTIVE_USER", json);
                //kokeile yhdistää users-muuttujaa, eli listaa UserList-luokan userListiin tässä
                userList.getUserList().add(testUser);
                loginEdit.commit();
                gson = new Gson();
                Type gsonType = new TypeToken<List<User>>() {}.getType();
                gsonString = gson.toJson(userList.getUserList() ,gsonType);
                loginEdit.putString("USER_LIST", gsonString);
                loginEdit.commit();
                Log.v("DEBUG9", "Tallennettu userList gson muodossa: " + gsonString);
                Log.v("DEBUG5", "Aktiivinen käyttäjä:" + testUser.getUserName() + " Salasana: " + testUser.getPassword());


                Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginSuccess);
            }

        }
    }
}
