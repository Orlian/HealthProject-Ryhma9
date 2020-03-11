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

public class SettingsActivity extends AppCompatActivity {

    private User testUser, activeUser;
    private SharedPreferences loginPrefs;
    private List<User> users;

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
        } else if (v == findViewById(R.id.resetPassword)){
            loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = loginPrefs.getString("ACTIVE_USER", " ");
            activeUser = gson.fromJson(json, User.class);

            EditText newPasswordInput1 = findViewById(R.id.passwordChangeField1);
            String newPassword1 = newPasswordInput1.getText().toString();
            EditText newPasswordInput2 = findViewById(R.id.passwordChangeField2);
            String newPassword2 = newPasswordInput2.getText().toString();
            UserList userList = UserList.getInstance();
            if(newPassword1.equals(newPassword2)){
                for(int i = 0; i < userList.getUserList().size(); i++){
                    testUser = userList.getUser(i);
                    if(testUser.getUserName().equals(activeUser.getUserName())){
                        userList.getUser(i).setPassword(newPassword2);
                        saveNewPassword(userList);
                        Log.v("DEBUG5", "Index: " + i);
                        Log.v("DEBUG5", "Uusi salasana: " + UserList.getInstance().getUser(i).getPassword());
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
    }
    private void saveNewPassword(UserList userListParam){
        SharedPreferences loginPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor loginEdit = loginPrefs.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>(){}.getType();
        String gsonString = gson.toJson(users, type);
        String json2 = loginPrefs.getString("USER_LIST", gsonString);
        users = new Gson().fromJson(json2, new TypeToken<List<User>>() {}.getType());
        userListParam.getUserList().clear();
        userListParam.getUserList().addAll(users);
        Gson gson2 = new Gson();
        Type gsonType = new TypeToken<List<User>>() {}.getType();
        gsonString = gson.toJson(userListParam.getUserList() ,gsonType);
        loginEdit.putString("USER_LIST", gsonString);
        loginEdit.commit();


    }

}
