package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.healthproject.QuestionActivity.EXTRA_GROUP1;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP2;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP3;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP4;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP_AVERAGE;

/**
 * Statistiikka-luokka, joka käyttää AnyChart kirjastoa ja näyttää käyttäjän tulokset ympyrädiagrammissa.
 * AnyChart: https://github.com/AnyChart/AnyChart-Android
 * @author Arttu Myyryläinen
 * @version 1.8
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;              //Nimetään AnyChartille view
    AnyChartView anyChartView2;             //Nimetään AnyChartille view
    private SharedPreferences resultPrefs;  //SharedPreferences olio luodaan
    private User testUser;                  //User luokan olio luodaan
    private List <User> users;              //Uusi lista users luodaan
    private UserList userList;              //UserList luokan olio luodaan

    /**
     * oncreate:
     * Asetetaan statisticsview aktiiviseksi ja määritetään view:t joihin anyChart tulostaa ympyrädiagrammit
     * Kutsutaan setupchart 1 ja 2 laskemaan ja piirtämään diagrammit.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        anyChartView = findViewById(R.id.any_chart_view);           //Määritetään AnyChartille view
        APIlib.getInstance().setActiveAnyChartView(anyChartView);   //Asetetaan Anychart aktiiviseksi näkymään
        setupChart1();

        anyChartView2 = findViewById(R.id.any_chart_view2);         //Määritetään AnyChartille view
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);  //Asetetaan AnyChart aktiiviseksi näkymään
        setupChart2();

    }

    /**
     * setupChart1:
     * Annetaan anyChartille mielentilat ja numerot, joiden perusteella ensimmäinen diagrammi piirretään kuvaamaan viimeisimpiä vastauksia.
     * Luvut noudetaan intentillä ja vastausryhmien keskiarvo lasketaan jakamalla vastausten yhteenlaskettu arvo vastausten määrällä kategorioittain.
     * Tämän jälkeen ensimmäinen diagrammi piirretään sille varattuun kenttään.
     */
    public void setupChart1() {
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie1 = AnyChart.pie();                                      //Nimetään ja luodaan ensimmäinen ympyrädiagrammi
        List<DataEntry> dataEntries = new ArrayList<>();

        Intent intent = getIntent();                                    //Intent nouto ja muuttujien luonti sekä määritys
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);
        Log.v("DEBUG9", "INTENT_ARVO1: "+group1); //DEBUG / TESTIKOODI Varmistetaan oikeat muuttujien arvot
        Log.v("DEBUG9", "INTENT_ARVO2: "+group2); //DEBUG / TESTIKOODI
        Log.v("DEBUG9", "INTENT_ARVO3: "+group3); //DEBUG / TESTIKOODI
        Log.v("DEBUG9", "INTENT_ARVO4: "+group4); //DEBUG / TESTIKOODI
        Log.v("DEBUG9", "INTENT_ARVOKAIKKI: "+groupAverage); //DEBUG / TESTIKOODI

        int[] values1 = {group1/4, group2/7, group3/7, group4/2};           //Luodaan Array values1 edellisten arvojen pohjalta. Tässä myös jaetaan arvot vastausten lukumäärällä, että arvo vastaa todellisuutta
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values1[i])); //Lisätään arvot dataEntries arraylistiin
            }
            pie1.title(getString(R.string.statisticsDay));                  //Nimetään ensimmäinen ympyrädiagrammi

        pie1.data(dataEntries);                                             //Asetetaan data ensimmäiseen ympyrädiagrammiin
        anyChartView.setChart(pie1);                                        //Asetetaan ensimmäinen diagrammi näkymään
    }

    /**
     * setupChart2:
     * Annetaan anyChartille mielentilat ja numerot, joiden perusteella diagrammi piirretään.
     * Haetaan numerot pysyvästä tallennustilasta ja keskiarvo lasketaan jakamalla vastausten arvot vastausten määrällä ja tallennuskertojen määrällä.
     * Tämänjälkeen toinen diagrammi piirretään sille määritettyyn kenttään.
     */
    public void setupChart2(){
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie2 = AnyChart.pie();
        List<DataEntry> dataEntries2 = new ArrayList<>();
        float group1 = 0, group2 = 0, group3 = 0, group4 = 0, divider = 0; //Luodaan uudet muuttujat vastausryhmille sekä jakajalle


        resultPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE); //Määritetään resultsPref tiettyyn arvoon "LOGIN_PREFS" sharedPreferencs avulla
        Gson gson = new Gson();                                                 //Uusi Gson olio luodaan
        String json = resultPrefs.getString("ACTIVE_USER", " ");  //json määritetään arvoon "ACTIVE_USER" avulla
        Gson gson2 = new Gson();                                                //Toinen Gson
        testUser = gson2.fromJson(json, User.class);                            //testUser saa arvon sharedPreferences avulla
        Type type = new TypeToken<List<User>>() {}.getType();                   //Haetaan oikeantyyppinen lista tallennetuista arvoista
        String gsonString = gson.toJson(users, type);                           //Muunnetaan gson json muotoon ja annetaan sille users arvo
        String json2 = resultPrefs.getString("USER_LIST", gsonString);     //json2 saa "USER_LIST" avulla oikean arvon
        users = new Gson().fromJson(json2, new TypeToken<List<User>>() {}.getType());   //Luodaan gson/json tallennettu lista takaisin oikeaan muotoon
        //users-muuttujan sisältämät User-oliot täytyy vielä kääntää takaisin User-olioiksi eikä vain niiden toString
        userList = UserList.getInstance();                                      //userList saa noudetun instanssin
        userList.getUserList().clear();                                         //userList alusteaan
        userList.getUserList().addAll(users);                                   //userList täytetään oikealla users arvolla
        Log.v("DEBUG9","ADDALL: "+userList.getUserList()); //DEBUG / TESTIKOODI Tällä tarkastetaan kenen lista on käytössä

        for(int i = 0; i < userList.getUserList().size(); i++){                     //Haetaan oikea käyttäjälista ajamalla koko käyttäjälista for-loopilla läpi
            if(userList.getUser(i).getUserName().equals(testUser.getUserName())){   //Valitaan oikea käyttäjä aiemmin määrätyllä testUser muuttujalla
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){ //käydään käyttäjälle osoitettu datalista for-loopilla läpi
                    if (y%4==0){                                                    //otetaan joka neljäs arvo listalta alkaen ensimmäisestä indeksistä
                        group1+=userList.getUser(i).getDataList().get(y);           //annetaan muuttujalle group1 haettu listan arvo y ja lisätään se aina seuraavaan haettuun arvoon.
                        divider++;                                                  //lisätään divider-muuttujaan yksi
                        Log.v("DEBUG9", "1. arvo neljän sarjassa 'Mielialat': "+userList.getUser(i).getDataList().get(y)); //DEBUG / TESTIKOODI Tarkistamme koodin laskevan joka neljännen luvun 1. alkaen
                    }
                }
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){ //käydään käyttäjälle osoitettu datalista for-loopilla läpi
                    if (y%4==1){                                                    //otetaan joka neljäs arvo listalta alkaen toisesta indeksistä
                        group2+=userList.getUser(i).getDataList().get(y);           //annetaan muuttujalle group2 haettu listan arvo y ja lisätään se aina seuraavaan haettuun arvoon.
                        Log.v("DEBUG9", "2. arvo neljän sarjassa 'Tuntemukset': "+userList.getUser(i).getDataList().get(y)); //DEBUG / TESTIKOODI Tarkistamme koodin laskevan joka neljännen luvun 2. alkaen
                    }
                }
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){ //käydään käyttäjälle osoitettu datalista for-loopilla läpi
                    if (y%4==2){                                                    //otetaan joka neljäs arvo listalta alkaen kolmannesta indeksistä
                        group3+=userList.getUser(i).getDataList().get(y);           //annetaan muuttujalle group3 haettu listan arvo y ja lisätään se aina seuraavaan haettuun arvoon.
                        Log.v("DEBUG9", "3. arvo neljän sarjassa 'Fyysinen': "+userList.getUser(i).getDataList().get(y)); //DEBUG / TESTIKOODI Tarkistamme koodin laskevan joka neljännen luvun 3. alkaen
                    }
                }
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){ //käydään käyttäjälle osoitettu datalista for-loopilla läpi
                    if (y%4==3){                                                    //otetaan joka neljäs arvo listalta alkaen neljännestä indeksistä
                        group4+=userList.getUser(i).getDataList().get(y);           //annetaan muuttujalle group4 haettu listan arvo y ja lisätään se aina seuraavaan haettuun arvoon.
                        Log.v("DEBUG9", "4. arvo neljän sarjassa 'Ihmissuhteet': "+userList.getUser(i).getDataList().get(y)); //DEBUG / TESTIKOODI Tarkistamme koodin laskevan joka neljännen luvun 4. alkaen
                    }
                }
                }
            }


        Log.v("DEBUG9", "USERLIST2: "+userList.getUserList());  //DEBUG / TESTIKOODI
        Log.v("DEBUG9", "group1 arvo: "+group1);                //DEBUG / TESTIKOODI Varmistamme rymien oikeat arvot
        Log.v("DEBUG9", "group2 arvo: "+group2);
        Log.v("DEBUG9", "group3 arvo: "+group3);
        Log.v("DEBUG9", "group4 arvo: "+group4);


        SharedPreferences.Editor editor = resultPrefs.edit();       //luodaan editor olio jolla resultPrefs editoidaan
        Gson gson3 = new Gson();                                    //luodaan uusi gson3 olio
        Type gsonType = new TypeToken<List<User>>() {}.getType();   //määritetään gson tyyppi oikeaksi listaksi
        gsonString = gson3.toJson(userList.getUserList() ,gsonType);//muunnetaan lista oikeaan string muotoon
        editor.putString("USER_LIST", gsonString);                  //Lisätään stringille "USER_LIST" avain
        editor.commit();                                            //Tallennetaan vastausten arvot gson/json muotoon jotta niihin päästään muualta myös käsiksi


        float[] values2 = {(group1/4)/divider, (group2/7)/divider, (group3/7)/divider, (group4/2)/divider };    //Määritetään ja lasketaan arvot
        for (int i = 0; i < states.length; i++) {
            dataEntries2.add(new ValueDataEntry(states[i], values2[i]));                                        //Lisätään arvot dataEntries2:een
        }
        Log.v("DEBUG9","Jakajan arvo: " +divider);                                                   //DEBUG / TESTIKOODI Varmistetaan dividerin arvo
        pie2.title(getString(R.string.statisticsWeek));                                                                         //Nimetään toinen diagrammi
        pie2.data(dataEntries2);                                                                               //Asetetaan data toiseen diagrammiin
        anyChartView2.setChart(pie2);                                                                          //Asetetaan toinen diagrammi näkymään.
    }
}
