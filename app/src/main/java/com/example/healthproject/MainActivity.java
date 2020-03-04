package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private boolean loggedIn=true; //HUOM! Muista ottaa "true" pois ennen kirjautumisen kokeilua!!!!
    public static final String EXTRA_NEED_LOGIN = "Need to log in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Päävalikon nappien onClick-metodi, joka sisältää koodia kolmen eri napin id:lle
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
            if(getSharedPreferences("LOGIN_PREFS", 0).getBoolean("LOGIN_STATUS", false)){
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
        }
    }
}
