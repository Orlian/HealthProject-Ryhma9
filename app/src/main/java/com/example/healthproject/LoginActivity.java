package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.healthproject.MainActivity.EXTRA_NEED_LOGIN;

/**
 * Kirjautumisesta ja rekisteröinnistä vastaava aktiviteetti, joka kutsuu ja muokkaa käyttäjälistaa,
 * sekä asettaa aktiivisen käyttäjän SharedPreferences tiedostoon.
 * @author Joonas Lehtoranta
 * @version 1.8
 */
public class LoginActivity extends AppCompatActivity {
    private boolean loginStatus;
    private List<Integer> userDataList;
    private List<User> users;

    /**
     * Suoritetaan kun aktiviteetti avataan
     * @param savedInstanceState
     */
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

    /**
     * OnClick-metodi sisältää koodit login-, ja rekisteröinti napille.
     * Tarkistaa ja tarvittaessa tallentaa singleton-luokan luokan userListalle, muuntaa ne gson stringiksi
     * ja tallentaa ne SharedPreferencen kautta pysyvästi.
     * @param v aktiivinen View olio
     */
    public void onClick(View v){
        User testUser;
        UserList userList;
        if(v == findViewById(R.id.loginButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi ja salasana täsmäävät
            EditText userNameInput = (EditText) findViewById(R.id.usernameField);
            String userInput = userNameInput.getText().toString();
            EditText userPasswordInput = (EditText) findViewById(R.id.passwordField);
            String userPassword = userPasswordInput.getText().toString();
            //Tarkistaa rekisteröityjen käyttäjien listalta onko haettua käyttäjää olemassa
            userList = UserList.getInstance();
            for(int i = 0; i < userList.getUserList().size(); i++){
                /*Vertaa listan jokaisen käyttäjän tietoja syötettyihin tietoihin, jos ne täsmäävät asetetaan kyseinen käyttäjä aktiiviseksi
                ja siirrytään mainActivityyn*/
                testUser = userList.getUser(i);
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
                } else {
                    //Jos haettua käyttäjää ei löydy listalta niin näytetään virheviesti
                    TextView tv = findViewById(R.id.loginErrorMessage);
                    tv.setText(getString(R.string.invalid_login));
                    tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                }
            }
            //Jos verrattava käyttäjälista on tyhjä
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
            //Varmistaa ettei käyttäjä yritä rekisteröidä tyhjää käyttäjää tai käyttää tyhjää salasanaa
            if((userInputName.equals("") && userInputPassword.equals("")) || (userInputName.equals("") || userInputPassword.equals(""))){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(getString(R.string.login_error_empty_data));
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            //Varmistaa ettei käyttäjä yritä rekisteröidä käyttäjää, joka sisältää välilyöntejä
            if((userInputName.contains(" ") && userInputPassword.contains(" ")) || (userInputName.contains(" ") || userInputPassword.contains(" "))){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(getString(R.string.login_error_empty_data));
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            //Luo uuden käyttäjän käyttäjän antamilla arvoilla
            testUser = new User(userInputName, userInputPassword);

            SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
            //Mahdollisesti turha datan haku alla
            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {}.getType();
            String gsonString = gson.toJson(users, type);
            String json2 = loginPrefs.getString("USER_LIST", gsonString);
            users = new Gson().fromJson(json2, new TypeToken<List<Object>>() {}.getType());

            userList = UserList.getInstance();
            //Tarkistaa tässä välissä ettei kyseistä käyttäjää ole jo luotuna
            if(!userList.getUserList().contains(testUser)) {
                loginStatus = true;
                SharedPreferences.Editor loginEdit = loginPrefs.edit();
                loginEdit.putBoolean("LOGIN_STATUS", loginStatus);
                gson = new Gson();
                String json = gson.toJson(testUser);
                loginEdit.putString("ACTIVE_USER", json);
                loginEdit.commit();

                gson = new Gson();
                userList.getUserList().add(testUser);
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
