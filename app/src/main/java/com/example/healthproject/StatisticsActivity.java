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
 * @version 1.7
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    AnyChartView anyChartView2;
    private SharedPreferences resultPrefs;
    private User testUser;
    private List <User> users;
    private UserList userList;

    /**
     * oncreate:
     * Asetetaan statisticsview aktiiviseksi ja määritetään view:t joihin anyChart tulostaa ympyrädiagrammit
     * Kutsutaan setupchart 1 ja 2 laskemaan ja piirtämään diagrammit.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        anyChartView = findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        setupChart1();

        anyChartView2 = findViewById(R.id.any_chart_view2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);
        setupChart2();

    }

    /**
     * setupChart1:
     * Annetaan anyChartille mielentilat ja numerot, joiden perusteella ensimmäinen diagrammi piirretään kuvaamaan viimeisimpiä vastauksia.
     * Luvut saadaan intentillä ja vastausryhmien keskiarvo lasketaan jakamalla vastausten yhteenlaskettu arvo vastausten määrällä kategorioittain.
     * Tämän jälkeen ensimmäinen diagrammi piirretään sille varattuun kenttään.
     */
    public void setupChart1() {
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie1 = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);
        Log.v("DEBUG9", "INTENT_ARVO1: "+group1);
        Log.v("DEBUG9", "INTENT_ARVO2: "+group2);
        Log.v("DEBUG9", "INTENT_ARVO3: "+group3);
        Log.v("DEBUG9", "INTENT_ARVO4: "+group4);
        Log.v("DEBUG9", "INTENT_ARVOKAIKKI: "+groupAverage);

        int[] values1 = {group1/4, group2/7, group3/7, group4/2};
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values1[i]));
            }
            pie1.title(getString(R.string.statisticsDay));
            Log.d("Debug", "true");

        pie1.data(dataEntries);
        Log.d("Debug", "1");
        anyChartView.setChart(pie1);
        Log.d("Debug", "3");
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
        float group1 = 0, group2 = 0, group3 = 0, group4 = 0;
        float divider=0;

        resultPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = resultPrefs.getString("ACTIVE_USER", " ");
        Gson gson2 = new Gson();
        testUser = gson2.fromJson(json, User.class);
        Type type = new TypeToken<List<User>>() {}.getType();
        String gsonString = gson.toJson(users, type);
        String json2 = resultPrefs.getString("USER_LIST", gsonString);
        users = new Gson().fromJson(json2, new TypeToken<List<User>>() {}.getType());
        //users-muuttujan sisältämät User-oliot täytyy vielä kääntää takaisin User-olioiksi eikä vain niiden toString
        userList = UserList.getInstance();
        userList.getUserList().clear();
        userList.getUserList().addAll(users);
        Log.v("DEBUG9","ADDALL: "+userList.getUserList());

        for(int i = 0; i < userList.getUserList().size(); i++){
            if(userList.getUser(i).getUserName().equals(testUser.getUserName())){
                    for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){
                        if (y%4==0){
                            group1+=userList.getUser(i).getDataList().get(y);
                            divider++;
                            Log.v("DEBUG9", "Ensimmäinen arvo neljän sarjassa: "+userList.getUser(i).getDataList().get(y));
                        }
                    }
                }
            }
        for(int i = 0; i < userList.getUserList().size(); i++){
            if(userList.getUser(i).getUserName().equals(testUser.getUserName())){
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){
                    if (y%4==1){
                        group2+=userList.getUser(i).getDataList().get(y);
                        Log.v("DEBUG9", "Toinen arvo neljän sarjassa: "+userList.getUser(i).getDataList().get(y));
                    }
                }
            }
        }
        for(int i = 0; i < userList.getUserList().size(); i++){
            if(userList.getUser(i).getUserName().equals(testUser.getUserName())){
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){
                    if (y%4==2){
                        group3+=userList.getUser(i).getDataList().get(y);
                        Log.v("DEBUG9", "Kolmas arvo neljän sarjassa: "+userList.getUser(i).getDataList().get(y));
                    }
                }
            }
        }
        for(int i = 0; i < userList.getUserList().size(); i++){
            if(userList.getUser(i).getUserName().equals(testUser.getUserName())){
                for (int y = 0; y < userList.getUser(i).getDataList().size(); y++){
                    if (y%4==3){
                        group4+=userList.getUser(i).getDataList().get(y);
                        Log.v("DEBUG9", "Neljäs arvo neljän sarjassa: "+userList.getUser(i).getDataList().get(y));
                    }
                }
            }
        }

        Log.v("DEBUG9", "USERLIST2: "+userList.getUserList());
        Log.v("DEBUG9", "group1 arvo: "+group1);
        Log.v("DEBUG9", "group2 arvo: "+group2);
        Log.v("DEBUG9", "group3 arvo: "+group3);
        Log.v("DEBUG9", "group4 arvo: "+group4);


        SharedPreferences.Editor editor = resultPrefs.edit();
        Gson gson3 = new Gson();
        Type gsonType = new TypeToken<List<User>>() {}.getType();
        gsonString = gson3.toJson(userList.getUserList() ,gsonType);
        editor.putString("USER_LIST", gsonString);
        editor.commit();


        float[] values2 = {(group1/4)/divider, (group2/7)/divider, (group3/7)/divider, (group4/2)/divider };
        for (int i = 0; i < states.length; i++) {
            dataEntries2.add(new ValueDataEntry(states[i], values2[i]));
        }
        Log.v("DEBUG9","Jakajan arvo" +divider);
        pie2.title(getString(R.string.statisticsWeek));
        pie2.data(dataEntries2);
        anyChartView2.setChart(pie2);
    }
}
