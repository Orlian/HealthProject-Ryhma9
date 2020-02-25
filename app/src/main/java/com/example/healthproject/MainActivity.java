package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public boolean loggedIn;
    public static final String EXTRA_MESSAGE = "Need to log in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        if(v == findViewById(R.id.loginRegisterButton)){
            //Tänne siirtyminen MainLogin aktiviteettiin
            Intent loginIntent = new Intent(MainActivity.this, MainLogin.class);
            startActivity(loginIntent);

        } else if(v == findViewById(R.id.mainButton)){
            //Tänne siirtyminen MainQuestions aktiviteettiin (eli päätoiminto)
            if(loggedIn){
                Intent questionsIntent = new Intent(MainActivity.this, MainQuestions.class);
                startActivity(questionsIntent);
            }else if(!loggedIn){
                Intent loginIntent = new Intent(MainActivity.this, MainLogin.class);
                loginIntent.putExtra(EXTRA_MESSAGE, loggedIn);
                startActivity(loginIntent);
            }

        } else if(v == findViewById(R.id.statsButton)){
            //Tänne siirtyminen MainStats aktiviteettiin (*ei vielä luotu*)
        }
    }
}
