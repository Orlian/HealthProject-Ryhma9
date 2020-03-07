package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("DEBUG5", UserList.getInstance().getUserList().toString());
    }

    /**
     * Päävalikon nappien onClick-metodi, joka sisältää koodia kaikkien eri nappien id:lle
     * @param v aktiivinen View-olio
     */
    public void onClick(View v){
        if(v == findViewById(R.id.loginRegisterButton)){
            //Tänne siirtyminen LoginActivity aktiviteettiin
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
            startActivity(loginIntent);

        } else if(v == findViewById(R.id.mainButton)){
            //Tänne siirtyminen QuestionActivity aktiviteettiin (eli päätoiminto), jos käyttäjä on kirjautunut sisään
            //Oletusarvo false, true vain testikäytössä
            if(getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE).getBoolean("LOGIN_STATUS", false)){
                loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = loginPrefs.getString("ACTIVE_USER", " ");
                testUser = gson.fromJson(json, User.class);
                Log.v("DEBUG5", "Aktiivinen käyttäjä: " + testUser.getUserName() + " Salasana: " + testUser.getPassword());
                Intent questionsIntent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(questionsIntent);
            }else{
                loggedIn = false;
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
                startActivity(loginIntent);
            }

        } else if(v == findViewById(R.id.statsButton)){
            //Tänne siirtyminen MainStats aktiviteettiin (*ei vielä luotu*)
            Intent statsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
            //Extrana tänne kyseisen käyttäjän vastausdatan!
            startActivity(statsIntent);
        } else if(v == findViewById(R.id.settingsButton)){
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        } //TODO Aktiivisen käyttäjän näyttäminen jollakin ikonilla yläkulmassa
    }
}
