package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

/**
 * Statistiikka-luokka, joka käyttää AnyChart kirjastoa ja näyttää käyttäjän tulokset ympyrädiagrammissa.
 * @author Arttu Myyryläinen
 * @version 0.2
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    String[] states = {"Moods", "Feelings", "Physical", "Relationships"};
    Boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Log.d("Debug", "0");
        anyChartView = findViewById(R.id.any_chart_view);
        setupChart();

    }

    public void setupChart() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        if (state) {
            int[] values = {10, 10, 10, 10};
            Log.d("Debug", "1");
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values[i]));
                Log.d("Debug", "2");
            }
            pie.title("Weekly averages");
            Log.d("Debug", "true");
        } else {
            int[] values = {100, 10, 10, 10};
            Log.d("Debug", "3");
            for (int i = 0; i < states.length; i++) {
                dataEntries.add(new ValueDataEntry(states[i], values[i]));
                Log.d("Debug", "4");
            }
            pie.title("Monthly averages");
            Log.d("Debug", "false");
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }

    public void changeState(View a){
        if(a == findViewById(R.id.switch1)) {
            Log.d("Debug", "5");
            state =!state;
            anyChartView.clear();
            setupChart();
        }
    }
}
