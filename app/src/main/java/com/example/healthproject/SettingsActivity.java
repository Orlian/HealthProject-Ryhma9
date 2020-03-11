package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

/**
 * Asetukset, eli kielenvaihdon ja salasanan muuttamisen sisältävä aktiviteetti.
 * @author Joonas Lehtoranta
 * @version 1.6
 */
public class SettingsActivity extends AppCompatActivity {

    private User testUser, activeUser;
    private SharedPreferences loginPrefs;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /**
     * onClick-metodi, joka sisältää koodit kielenvaihtonapeille, jotka muuttavat lokalisaatiota,
     * ja aktiivisen käyttäjän salasanan vaihto ja uuden talletus pysyvään tallennustilaan.
     * @param v aktiivista näkymää kuvaava View muuttuja, joka kertoo mitä nappia painetaan.
     */
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
        } else if (v == findViewById(R.id.resetPassword)){
            loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = loginPrefs.getString("ACTIVE_USER", " ");
            activeUser = gson.fromJson(json, User.class);

            EditText newPasswordInput1 = findViewById(R.id.passwordChangeField1);
            String newPassword1 = newPasswordInput1.getText().toString();
            EditText newPasswordInput2 = findViewById(R.id.passwordChangeField2);
            String newPassword2 = newPasswordInput2.getText().toString();
            if((newPassword1.equals("") && newPassword2.equals(""))){
                TextView tv =findViewById(R.id.userSettingsPasswordChange);
                tv.setText(R.string.settings_new_password_error);
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            if((newPassword1.contains(" ") && newPassword2.contains(" "))){
                TextView tv =findViewById(R.id.userSettingsPasswordChange);
                tv.setText(R.string.settings_new_password_error);
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));
                return;
            }
            UserList userList = UserList.getInstance();
            if(newPassword1.equals(newPassword2)){
                for(int i = 0; i < userList.getUserList().size(); i++){
                    testUser = userList.getUser(i);
                    if(testUser.getUserName().equals(activeUser.getUserName())){
                        userList.getUser(i).setPassword(newPassword2);
                        saveNewPassword(userList);
                        Log.v("DEBUG5", "Index: " + i);
                        Log.v("DEBUG5", "Uusi salasana: " + userList.getUser(i).getPassword());
                        break;
                    }
                }
                updateUI();

            } else {
                TextView tv =findViewById(R.id.userSettingsPasswordChange);
                tv.setText(R.string.settings_password_change_error);
                tv.setTextColor(getResources().getColor(R.color.colorErrorText));

            }
        }
    }

    /**
     * Kieltä vaihtaessa kutsuttava metodi, joka päivittää näkymän reaaliajassa oikeeseen kieleen.
     */
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
        tv.setTextColor(getResources().getColor(R.color.colorText));
        tv = findViewById(R.id.mainMenuButton2);
        tv.setText(R.string.mainmenu_button);
        tv = findViewById(R.id.resetPassword);
        tv.setText(R.string.resetPassword);
    }

    /**
     * Kapsuloitu SharedPreference tallennus, joka ottaa parametrikseen UserList-olion jonka listaa on juuri muokattu.
     * @param userListParam olio, joka sisältää tuoreeltaan muokatun listan talletusta varten.
     */
    private void saveNewPassword(UserList userListParam){
        SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor loginEdit = loginPrefs.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>(){}.getType();
        String gsonString = gson.toJson(userListParam.getUserList(), type);
        loginEdit.putString("USER_LIST", gsonString);
        loginEdit.commit();
    }

}
