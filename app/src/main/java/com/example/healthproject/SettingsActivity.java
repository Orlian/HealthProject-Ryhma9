package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClick(View v) {
        if (v == findViewById(R.id.languageEngButton)) {
            String languageToLoad = "en";
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            updateUI();
        } else if (v == findViewById(R.id.languageFinButton)) {
            String languageToLoad = "fi";
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            updateUI();
        } else if (v == findViewById(R.id.mainMenuButton2)) {
            Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(mainIntent);
            updateUI();
        }
    }

    public void updateUI(){
        TextView tv = findViewById(R.id.languageSettingText);
        tv.setText(R.string.settings_language);
        tv = findViewById(R.id.userSettingsText);
        tv.setText(R.string.settings_user);
        tv = findViewById(R.id.passwordChangeField1);
        tv.setHint(R.string.settings_password_change_hint);
        tv = findViewById(R.id.passwordChangeField2);
        tv.setHint(R.string.settings_password_change_hint2);
        tv = findViewById(R.id.userSettingsPasswordChange);
        tv.setText(R.string.settings_change_password_text);
        tv = findViewById(R.id.mainMenuButton2);
        tv.setText(R.string.mainmenu_button);
    }

}
