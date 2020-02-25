package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        //Jos käyttäjä ei ole kirjautunut sisään, näytetään login/register ruudun yllä virheviesti
        Bundle b = getIntent().getExtras();
        boolean loginError = b.getBoolean(MainActivity.EXTRA_MESSAGE, false);
        if(!loginError){
            TextView tv = findViewById(R.id.loginErrorMessage);
            tv.setText(R.string.not_logged_in);
        }
    }
    public void onClick(View v){
        if(v == findViewById(R.id.loginButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi ja salasana täsmäävät

        }else if(v == findViewById(R.id.registerButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi on vapaana

        }
    }
}
