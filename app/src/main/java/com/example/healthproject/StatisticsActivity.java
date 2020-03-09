package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Statistiikka-luokka, joka käyttää AnyChart kirjastoa ja näyttää käyttäjän tulokset ympyrädiagrammissa.
 * @author Arttu Myyryläinen
 * @version 0.6
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    AnyChartView anyChartView2;
    private SharedPreferences resultPrefs;

    /*
    switch komentoon liittyvät muuttujat.
    Boolean state = true;
    Boolean limiter = false;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Log.d("Debug", "0");

        anyChartView = findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        setupChart1();

        anyChartView2 = findViewById(R.id.any_chart_view2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);
        setupChart2();

    }

    public void setupChart1() {
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie1 = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        resultPrefs = getSharedPreferences("RESULTS_PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = resultPrefs.getString("HASHMAP_VALUE", "");
        int[] values1 = {10, 10, 10, 10};
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values1[i]));
            }
            pie1.title(getString(R.string.statisticsWeek));
            Log.d("Debug", "true");

        pie1.data(dataEntries);
        Log.d("Debug", "1");
        anyChartView.setChart(pie1);
        Log.d("Debug", "3");
    }

    public void setupChart2(){
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie2 = AnyChart.pie();
        List<DataEntry> dataEntries2 = new ArrayList<>();
        int[] values2 = {100, 10, 10, 10};
        for (int i = 0; i < states.length; i++) {
            dataEntries2.add(new ValueDataEntry(states[i], values2[i]));
        }
        pie2.title(getString(R.string.statisticsMonth));
        Log.d("Debug", "false");
        pie2.data(dataEntries2);
        Log.d("Debug", "2");
        anyChartView2.setChart(pie2);
        Log.d("Debug", "4");
    }

    /*
    switch kokeilu, toimii mutta näyttää vain toisen piirakan.
    public void changeState(View e){
        if(e == findViewById(R.id.switch2) && limiter == false ) {
            Log.d("Debug", "5");
            //anyChartView.clear();
            limiter = true;
            state =!state;
            anyChartView2 = findViewById(R.id.any_chart_view2);
            APIlib.getInstance().setActiveAnyChartView(anyChartView2);
            setupChart2();
        } else{
           // anyChartView2.clear();
            limiter = false;
            anyChartView = findViewById(R.id.any_chart_view);
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            setupChart1();
        }
    }
    */


}
