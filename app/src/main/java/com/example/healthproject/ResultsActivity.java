package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.healthproject.QuestionActivity.EXTRA_GROUP1;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP2;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP3;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP4;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP_AVERAGE;
/**
 * Yhteenveto-luokka, joka näyttää käyttäjälle QuestionActivityssä saamiensa pisteiden mukaiset tekstimuotoiset palautteet.
 * Luokka lähettää pisteet eteenpäin joko MainActivityyn tai StatisticActivityyn, riippuen minne käyttäjä haluaa siirtyä nappia painamalla.
 * @author Oskari Piiroinen
 * @version 1.4
 */
public class ResultsActivity extends AppCompatActivity {
    private SharedPreferences resultPrefs; //alustetaan shared preference tuloksille
    private User testUser;                 //alustetaan User olio testUser
    private List <User> users;             //alustetaan lista User olioista, users niminen
    private UserList userList;             //alustetaan singleton luokan UserList, userList niminen

    /**
     * Suoritetaan kun aktiviteetti luodaan.
     * @param savedInstanceState hakee muistista sinne tallennetun UI tilan.
     * Intent luokan olio vastaanottaa aikeen edelliseltä aktiviteetilta (QuestionActivity)
     * int group1, 2, 3, 4 ja groupAverage muuttujiin sijoitetaan aikeen avaimilla (EXTRA_GROUP1 etc.) arvot, jotka saadaan QuestionActivitystä
     * if kyselyissä vertaillaan muuttujien saamaa int arvoa ja annetaan tekstimuotoinen palaute käyttäjälle sen mukaan.
     *  Jokaiselle palautteelle on layoutissa oma TextView kenttä, joka täytetään pistemäärään mukaan.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();                                    //luodaan intent, jolla vastaanotetaan arvoja Question aktiviteetista
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);   //Asetetaan vastaanotetut int arvot muuttujiin group1, group2 etc.
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);




        if (group1==0){                                                         //Verrataan int arvoisia muuttujia, eri pistemääriin ja annetaan sitä vastaava palaute tekstimuotoisena
            TextView textView1 = (TextView) findViewById(R.id.group1Text);
            textView1.setText(R.string.group_feedback_exception);
        } else if(group1 < 5) {
            TextView textView2 = (TextView) findViewById(R.id.group1Text);
            textView2.setText(R.string.group1critical);
        } else if (group1 < 15) {
            TextView textView3 = (TextView) findViewById(R.id.group1Text);
            textView3.setText(R.string.group1alarm);
        } else if (group1 < 20) {
            TextView textView4 = (TextView) findViewById(R.id.group1Text);
            textView4.setText(R.string.group1good);
        } else {
            TextView textView5 = (TextView) findViewById(R.id.group1Text);
            textView5.setText(R.string.group1excellent);
        }

        if (group2==0){                                                     //Verrataan int arvoisia muuttujia, eri pistemääriin ja annetaan sitä vastaava palaute tekstimuotoisena
            TextView textView1 = (TextView) findViewById(R.id.group2Text);
            textView1.setText(R.string.group_feedback_exception);
        } else if(group2 < 8) {
            TextView textView2 = (TextView) findViewById(R.id.group2Text);
            textView2.setText(R.string.group2critical);
        } else if (group2 < 17) {
            TextView textView3 = (TextView) findViewById(R.id.group2Text);
            textView3.setText(R.string.group2alarm);
        } else if (group2 < 26) {
            TextView textView4 = (TextView) findViewById(R.id.group2Text);
            textView4.setText(R.string.group2midway);
        } else if (group2 < 35) {
            TextView textView5 = (TextView) findViewById(R.id.group2Text);
            textView5.setText(R.string.group2good);
        } else {
            TextView textView6 = (TextView) findViewById(R.id.group2Text);
            textView6.setText(R.string.group2excellent);
        }

        if (group3==0){                                                 //Verrataan int arvoisia muuttujia, eri pistemääriin ja annetaan sitä vastaava palaute tekstimuotoisena
            TextView textView1 = (TextView) findViewById(R.id.group3Text);
            textView1.setText(R.string.group_feedback_exception);
        } else if (group3 < 8) {
            TextView textView2 = (TextView) findViewById(R.id.group3Text);
            textView2.setText(R.string.group3critical);
        } else if (group3 < 17) {
            TextView textView3 = (TextView) findViewById(R.id.group3Text);
            textView3.setText(R.string.group3alarm);
        } else if (group3 < 26) {
            TextView textView4 = (TextView) findViewById(R.id.group3Text);
            textView4.setText(R.string.group3midway);
        } else if (group3 < 35) {
            TextView textView5 = (TextView) findViewById(R.id.group3Text);
            textView5.setText(R.string.group3good);
        } else {
            TextView textView6 = (TextView) findViewById(R.id.group3Text);
            textView6.setText(R.string.group3excellent);
        }

        if (group4==0){                                                 //Verrataan int arvoisia muuttujia, eri pistemääriin ja annetaan sitä vastaava palaute tekstimuotoisena
            TextView textView1 = (TextView) findViewById(R.id.group4Text);
            textView1.setText(R.string.group_feedback_exception);
        } else if(group4 < 3) {
            TextView textView2 = (TextView) findViewById(R.id.group4Text);
            textView2.setText(R.string.group4critical);
        } else if (group4 < 7) {
            TextView textView3 = (TextView) findViewById(R.id.group4Text);
            textView3.setText(R.string.group4midway);
        } else {
            TextView textView4 = (TextView) findViewById(R.id.group4Text);
            textView4.setText(R.string.group4excellent);
        }

        if (groupAverage==0){                                           //Verrataan int arvoisia muuttujia, eri pistemääriin ja annetaan sitä vastaava palaute tekstimuotoisena
            TextView textView1 = (TextView) findViewById(R.id.group5Text);
            textView1.setText(R.string.group_feedback_exception);
        } else if(groupAverage < 21) {
            TextView textView2 = (TextView) findViewById(R.id.group5Text);
            textView2.setText(R.string.group5critical);
        } else if (groupAverage < 41) {
            TextView textView3 = (TextView) findViewById(R.id.group5Text);
            textView3.setText(R.string.group5alarm);
        } else if (groupAverage < 61) {
            TextView textView4 = (TextView) findViewById(R.id.group5Text);
            textView4.setText(R.string.group5midway);
        } else if (groupAverage < 81) {
            TextView textView5 = (TextView) findViewById(R.id.group5Text);
            textView5.setText(R.string.group5good);
        } else {
            TextView textView6 = (TextView) findViewById(R.id.group5Text);
            textView6.setText(R.string.group5excellent);
        }
    }

    /**
     * OnClick metodia kutsutaan kun layouttiin lisättyjä nappeja painetaan. Napin painallusta vertaillaan napin ID:n mukaan.
     * @param a kertoo OnClick metodille mitä näkymää käytetään.
     * Intent luokan olio vastaanottaa aikeen edelliseltä aktiviteetilta (QuestionActivity)
     * int group1, 2, 3, 4 ja groupAverage muuttujiin sijoitetaan aikeen avaimilla (EXTRA_GROUP1 etc.) arvot, jotka saadaan QuestionActivitystä
     *  if lauseessa vertaillaan onko käyttäjä painanut Menu vai Statistics nappia.
     *  Menu nappia painamalla käyttäjä tallentaa datat aktiivisen käyttäjän datalistaan. Käyttäjä siirtyy MainActivityyn ja siirtää int group1, 2, 3, 4 ja GroupAverage arvot MainActivityyn.
     *  Statistics nappia painamalla käyttäjä tallentaa datat aktiivisen käyttäjän datalistaan. Käyttäjä siirtyy StatisticsActivityyn ja siirtää int group1, 2, 3, 4 ja GroupAverage arvot StatisticsActivityyn.
     */
    public void onClick(View a) {
        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);       //luodaan intent, jolla vastaanotetaan arvoja Question aktiviteetista
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);       //Asetetaan vastaanotetut int arvot muuttujiin group1, group2 etc.
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);

        if (a == findViewById(R.id.mainMenuButton)) {   //tarkastetaan if lauseella mitä nappia painetaan sen ID:n perusteella
            saveData();

            Intent mainIntent = new Intent(ResultsActivity.this, MainActivity.class); //luodaan uusi intent Main aktiviteettiin
            mainIntent.putExtra(EXTRA_GROUP1, group1);                                              //siirretään jokainen muuttuja yksitellen avaimilla EXTRA_GROUP1 etc. Main aktiviteettiin
            mainIntent.putExtra(EXTRA_GROUP2, group2);
            mainIntent.putExtra(EXTRA_GROUP3, group3);
            mainIntent.putExtra(EXTRA_GROUP4, group4);
            mainIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
            startActivity(mainIntent);                                                              //siirrytään uuten aktiviteettiin eli Main aktiviteettiin

        } else if (a == findViewById(R.id.statisticsButton)) { //tarkastetaan if lauseella mitä nappia painetaan sen ID:n perusteella
            saveData();

            Intent statsIntent = new Intent(ResultsActivity.this, StatisticsActivity.class); //luodaan uusi intent Stats aktiviteettiin
            statsIntent.putExtra(EXTRA_GROUP1, group1);                                                     //siirretään jokainen muuttuja yksitellen avaimilla EXTRA_GROUP1 etc. Stats aktiviteettiin
            statsIntent.putExtra(EXTRA_GROUP2, group2);
            statsIntent.putExtra(EXTRA_GROUP3, group3);
            statsIntent.putExtra(EXTRA_GROUP4, group4);
            statsIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
            startActivity(statsIntent);                                                                     //siirrytään uuten aktiviteettiin eli Stats aktiviteettiin
        }
    }

    /**
     * saveData metodi, joka tallentaa aktiivisen käyttäjän datalistaan QuestionsActivity:ltä saamansa int arvot.
     *
     */
    private void saveData(){
        Intent intent = getIntent();                                    //luodaan intent, jolla vastaanotetaan arvoja Question aktiviteetista
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);   //Asetetaan vastaanotetut int arvot muuttujiin group1, group2 etc.
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);



        resultPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE); //Määritetään resultsPref tiettyyn arvoon "LOGIN_PREFS" sharedPreferencs avulla
        Gson gson = new Gson();                                                 //Uusi Gson olio luodaan
        String json = resultPrefs.getString("ACTIVE_USER", " ");  //json määritetään arvoon "ACTIVE_USER" avulla
        Gson gson2 = new Gson();                                                //Toinen Gson
        testUser = gson2.fromJson(json, User.class);                            //testUser saa arvon sharedPreferences avulla
        Type type = new TypeToken<List<User>>() {}.getType();                   //Haetaan oikeantyyppinen lista tallennetuista arvoista
        String gsonString = gson.toJson(users, type);                           //Muunnetaan gson json muotoon ja annetaan sille users arvo
        String json2 = resultPrefs.getString("USER_LIST", gsonString);      //json2 saa "USER_LIST" avulla oikean arvon
        users = new Gson().fromJson(json2, new TypeToken<List<User>>() {}.getType());   //Luodaan gson/json tallennettu lista takaisin oikeaan muotoon
        userList = UserList.getInstance();                                      //userList saa noudetun instanssin
        userList.getUserList().clear();                                         //userList alusteaan
        userList.getUserList().addAll(users);                                   //userList täytetään oikealla users arvolla
        Log.v("DEBUG9","ADDALL: "+userList.getUserList());            //DEBUG / TESTIKOODI Tällä tarkastetaan kenen lista on käytössä
        for(int i = 0; i < userList.getUserList().size(); i++){                 //Haetaan oikea käyttäjälista ajamalla koko käyttäjälista for-loopilla läpi ja lisätään arvot oikean käyttäjän datalistalle
          if(userList.getUser(i).getUserName().equals(testUser.getUserName())){
              userList.getUser(i).getDataList().add(group1);
              userList.getUser(i).getDataList().add(group2);
              userList.getUser(i).getDataList().add(group3);
              userList.getUser(i).getDataList().add(group4);
              Log.v("DEBUG9", "testUser lisäykset: "+userList.getUser(i).getDataList());    //DEBUG / TESTIKOODI mitä arvoja käyttäjän listalle on lisätty
            }

        }
    Log.v("DEBUG9", "USERLIST: "+userList.getUserList());            //DEBUG / TESTIKOODI Tällä tarkastetaan kenen lista on käytössä


        SharedPreferences.Editor editor = resultPrefs.edit();                   //luodaan editor olio jolla resultPrefs editoidaan
        Gson gson3 = new Gson();                                                //luodaan uusi gson3 olio
        Type gsonType = new TypeToken<List<User>>() {}.getType();               //määritetään gson tyyppi oikeaksi listaksi
        gsonString = gson3.toJson(userList.getUserList() ,gsonType);            //muunnetaan lista oikeaan string muotoon
        editor.putString("USER_LIST", gsonString);                              //Lisätään stringille "USER_LIST" avain
        editor.commit();                                                        //Tallennetaan vastausten arvot gson/json muotoon jotta niihin päästään muualta myös käsiksi

    }
}
