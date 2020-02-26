package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.healthproject.MainLogin.EXTRA_LOGIN_STATUS;

public class MainActivity extends AppCompatActivity {
    private boolean loggedIn;
    private DataList dataList;
    public static final String EXTRA_NEED_LOGIN = "Need to log in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dataList = new DataList();
        Bundle login = getIntent().getExtras();
        if(login == null){

        } else if(login.containsKey(EXTRA_LOGIN_STATUS)){
            this.loggedIn = login.getBoolean(EXTRA_LOGIN_STATUS, true);
        }
    }

    public void onClick(View v){
        if(v == findViewById(R.id.loginRegisterButton)){
            //Tänne siirtyminen MainLogin aktiviteettiin
            Intent loginIntent = new Intent(MainActivity.this, MainLogin.class);
            loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
            startActivity(loginIntent);

        } else if(v == findViewById(R.id.mainButton)){
            //Tänne siirtyminen MainQuestions aktiviteettiin (eli päätoiminto), jos käyttäjä on kirjautunut sisään
            if(this.loggedIn){
                Intent questionsIntent = new Intent(MainActivity.this, MainQuestions.class);
                startActivity(questionsIntent);
            }else{
                Intent loginIntent = new Intent(MainActivity.this, MainLogin.class);
                loginIntent.putExtra(EXTRA_NEED_LOGIN, loggedIn);
                startActivity(loginIntent);
            }

        } else if(v == findViewById(R.id.statsButton)){
            //Tänne siirtyminen MainStats aktiviteettiin (*ei vielä luotu*)
            Intent statsIntent = new Intent(MainActivity.this, MainStatistics.class);
            //Extrana tänne kyseisen käyttäjän vastausdatan!
            startActivity(statsIntent);
        }
    }
}
