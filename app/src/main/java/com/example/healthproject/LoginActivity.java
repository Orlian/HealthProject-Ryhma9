package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.healthproject.MainActivity.EXTRA_NEED_LOGIN;

public class LoginActivity extends AppCompatActivity {
    private User testUser;
    public static final String EXTRA_LOGIN_STATUS = "Login data";
    public static final String EXTRA_NO_LOGIN = "No login data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        //Jos käyttäjä ei ole kirjautunut sisään, näytetään login/register ruudun yllä virheviesti
        Bundle b = getIntent().getExtras();
        if(!b.containsKey(EXTRA_NEED_LOGIN)){

        } else {
            boolean loginStatus = b.getBoolean(EXTRA_NEED_LOGIN, false);
            if(!loginStatus){
                TextView tv = findViewById(R.id.loginErrorMessage);
                tv.setText(R.string.not_logged_in);
            }
        }

    }
    public void onClick(View v){
        if(v == findViewById(R.id.loginButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi ja salasana täsmäävät
            EditText userNameInput = (EditText) findViewById(R.id.usernameField);
            String userInput = userNameInput.getText().toString();
            //Tarkista vertaamalla käyttäjien hashMappiin onko kyseistä käyttäjänimeä rekisteröity
            //HUOM! Tarvitsee vielä kysymysaktiviteetissa luodun hashmapin käyttöönsä!
            /*for(int i = 0; this.dataList.getDataList().size() > i ; i++){
                if(this.dataList.getUser(i).equals(userInput)){
                    boolean loginStatus = true;
                    Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                    loginSuccess.putExtra(EXTRA_LOGIN_STATUS, loginStatus);
                    startActivity(loginSuccess);
                }
            }
             */
            TextView tv = findViewById(R.id.loginErrorMessage);
            tv.setText("Invalid username or password");

    }else if(v == findViewById(R.id.registerButton)){
            //Siirtyy takaisin MainActivityyn, jos käyttäjänimi on vapaana
            //Luo User-olion ja samalla sille oman dataListan
            Intent temporaryIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(temporaryIntent);

        }
    }
}
