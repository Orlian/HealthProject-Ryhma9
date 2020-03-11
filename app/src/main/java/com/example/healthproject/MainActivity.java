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
import java.util.Arrays;
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
    private boolean loggedIn; //HUOM! Muista ottaa "true" pois ennen kirjautumisen kokeilua!!!!
    public static final String EXTRA_NEED_LOGIN = "Need to log in";
    private SharedPreferences loginPrefs;
    private User testUser;
    private UserList userList;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("DEBUG5", "UserList sisältö: " +  UserList.getInstance().getUserList());
        updateUI();
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
            //Tänne siirtyminen LoginActivity aktiviteettiin
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
            startActivity(loginIntent);

        } else if (v == findViewById(R.id.mainButton)) {
            //Tänne siirtyminen QuestionActivity aktiviteettiin (eli päätoiminto), jos käyttäjä on kirjautunut sisään
            //Oletusarvo false, true vain testikäytössä
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
            //Tänne siirtyminen MainStats aktiviteettiin (*ei vielä luotu*)
            if (loggedIn == false) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
                startActivity(loginIntent);
            } else {
                Intent statsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
                statsIntent.putExtra(EXTRA_GROUP1, group1);
                statsIntent.putExtra(EXTRA_GROUP2, group2);
                statsIntent.putExtra(EXTRA_GROUP3, group3);
                statsIntent.putExtra(EXTRA_GROUP4, group4);
                statsIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
                //Extrana tänne kyseisen käyttäjän vastausdatan!
                startActivity(statsIntent);
            }
        }

            else if(v == findViewById(R.id.settingsButton)){
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
    }
    private void updateUI(){
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
        users = new Gson().fromJson(json2, new TypeToken<List<Object>>() {}.getType());
        //users-muuttujan sisältämät User-oliot täytyy vielä kääntää takaisin User-olioiksi eikä vain niiden toString
        userList = UserList.getInstance();

        if(users != null){
            Log.v("DEBUG9", "UserList: " + users);
        }
        /*
        Type type = new TypeToken<List<Student>>(){}.getType();
        List<Student> students = gson.fromJson(json, type);
        */




    }
}
