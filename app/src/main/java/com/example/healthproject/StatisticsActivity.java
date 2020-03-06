package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
 * @version 0.4
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    AnyChartView anyChartView2;
    Boolean state = true;
    Boolean limiter = false;
    private SharedPreferences resultPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Log.d("Debug", "0");
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView2 = findViewById(R.id.any_chart_view2);
        setupChart();

    }

    public void setupChart() {
        //anyChartView.clear();
        String[] states = {getString(R.string.group1head), getString(R.string.group2head), getString(R.string.group3head), getString(R.string.group4head)};
        Pie pie = AnyChart.pie();
        Pie pie2 = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        List<DataEntry> dataEntries2 = new ArrayList<>();
        resultPrefs = getSharedPreferences("RESULTS_PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = resultPrefs.getString("HASHMAP_VALUE", "");
        int[] values1 = {10, 10, 10, 10};
        int[] values2 = {100, 10, 10, 10};
        //if (state) {
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values1[i]));
            }
            pie.title(getString(R.string.statisticsWeek));
            Log.d("Debug", "true");
       // } else {
            for (int i = 0; i < states.length; i++) {
                dataEntries2.add(new ValueDataEntry(states[i], values2[i]));
            }
            pie.title(getString(R.string.statisticsMonth));
            Log.d("Debug", "false");
        //}
        pie.data(dataEntries);
        Log.d("Debug", "1");
        pie2.data(dataEntries2);
        Log.d("Debug", "2");
        anyChartView.setChart(pie);
        Log.d("Debug", "3");
        anyChartView2.setChart(pie2);
        Log.d("Debug", "4");
    }


    /**public void changeState(View a){
        if(a == findViewById(R.id.switch1) && limiter == false ) {
            Log.d("Debug", "5");
            anyChartView.clear();
            limiter = true;
            state =!state;
            setupChart();
        }
    }**/

}
