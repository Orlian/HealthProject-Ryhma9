package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.healthproject.LoginActivity.EXTRA_LOGIN_STATUS;

public class MainActivity extends AppCompatActivity {
    private boolean loggedIn=true; //HUOM! Muista ottaa "true" pois ennen kirjautumisen kokeilua!!!!
    public static final String EXTRA_NEED_LOGIN = "Need to log in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        if(v == findViewById(R.id.loginRegisterButton)){
            //Tänne siirtyminen LoginActivity aktiviteettiin
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
            startActivity(loginIntent);

        } else if(v == findViewById(R.id.mainButton)){
            //Tänne siirtyminen QuestionActivity aktiviteettiin (eli päätoiminto), jos käyttäjä on kirjautunut sisään
            if(this.loggedIn = getIntent().getBooleanExtra(EXTRA_LOGIN_STATUS, false)){
                Intent questionsIntent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(questionsIntent);
            }else{
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
