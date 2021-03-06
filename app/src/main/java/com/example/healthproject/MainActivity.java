package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.healthproject.QuestionActivity.EXTRA_GROUP1;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP2;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP3;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP4;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP_AVERAGE;

/**
 * Päävalikko-aktiviteetti, joka toimii kaikkien muiden aktiviteettien yhdyskohtana
 * @author Joonas Lehtoranta
 * @author Joonas Soininen
 * @author Oskari Piiroinen
 * @author Arttu Myyryläinen
 * @version 2.1
 */
public class MainActivity extends AppCompatActivity {
    private boolean loggedIn;                                           //Erillinen boolean, jolla tarkistetaan onko sisäänkirjauduttu
    public static final String EXTRA_NEED_LOGIN = "Need to log in";
    private SharedPreferences loginPrefs;                               //SharedPreferences-olio login asetuksien hakuun
    private User testUser;
    private List<User> users;                                           //SharedPreferenceistä haettava käyttäjälista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();
        Log.v("DEBUG5", "UserList sisältö: " +  UserList.getInstance().getUserList());  //DEBUG userListin sisällön tarkistaminen

    }

    /**
     * Päävalikon nappien onClick-metodi, joka sisältää koodia kaikkien eri nappien id:lle
     * @param v aktiivinen View-olio
     */
    public void onClick(View v) {

        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);

        if (v == findViewById(R.id.loginRegisterButton)) {
            //Vie kirjautumissivulle
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
            startActivity(loginIntent);

        } else if (v == findViewById(R.id.mainButton)) {
            //Tarkistaa aktiivisen käyttäjän, jos löytyy niin mennään kysymysaktiviteettiin, muuten kirjautumisaktiviteettin
            if (getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE).getBoolean("LOGIN_STATUS", false)) {
                loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = loginPrefs.getString("ACTIVE_USER", " ");
                testUser = gson.fromJson(json, User.class);
                Log.v("DEBUG5", "Aktiivinen käyttäjä: " + testUser.getUserName() + " Salasana: " + testUser.getPassword());
                Intent questionsIntent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(questionsIntent);
            } else {
                loggedIn = false;
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
                startActivity(loginIntent);
            }

        } else if (v == findViewById(R.id.statsButton)) {
            //Aktiivisen käyttäjän tilastoihin siirtyminen, jos ei aktiivista käyttäjää niin kirjautumissivulle
            if (getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE).getBoolean("LOGIN_STATUS", false)) {
                loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = loginPrefs.getString("ACTIVE_USER", " ");
                testUser = gson.fromJson(json, User.class);
                Log.v("DEBUG5", "Aktiivinen käyttäjä: " + testUser.getUserName() + " Salasana: " + testUser.getPassword());
                Intent statsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
                statsIntent.putExtra(EXTRA_GROUP1, group1);
                statsIntent.putExtra(EXTRA_GROUP2, group2);
                statsIntent.putExtra(EXTRA_GROUP3, group3);
                statsIntent.putExtra(EXTRA_GROUP4, group4);
                statsIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
                startActivity(statsIntent);
            } else {
                loggedIn = false;
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
                startActivity(loginIntent);
            }
        }
            //Siirrytään asetuksiin
            else if(v == findViewById(R.id.settingsButton)){
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
    }

    /**
     * Päivittää käyttäjän tunnistavaa textViewtä ja hakee singletonin käyttäjälistan arvoksi
     * SharedPreferenceihin tallenetun käyttäjälistan.
     */
    private void updateUI(){
        //Päivittää aktiiviset view:t oikealla datalla
        loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = loginPrefs.getString("ACTIVE_USER", " ");
        testUser = gson.fromJson(json, User.class);
        if(testUser != null){
            TextView tv = findViewById(R.id.activeUser);
            tv.setText((getString(R.string.active_user_active)) + " " + testUser.getUserName() + "!");
        } else {
            TextView tv = findViewById(R.id.activeUser);
            tv.setText(getString(R.string.active_user_main));
        }
        gson = new Gson();
        Type type = new TypeToken<List<User>>() {}.getType();
        String gsonString = gson.toJson(users, type);
        String json2 = loginPrefs.getString("USER_LIST", gsonString);
        users = new Gson().fromJson(json2, new TypeToken<List<User>>() {}.getType());
        UserList userList = UserList.getInstance();
        if(users != null){
            userList.getUserList().clear();
            userList.getUserList().addAll(users);

            Log.v("DEBUG9", "UserList uudelleenkoottuna: " + userList.getUserList());
        }

    }
}
