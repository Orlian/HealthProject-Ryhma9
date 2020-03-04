package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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
 * @version 0.1
 */
public class StatisticsActivity extends AppCompatActivity {
    AnyChartView anyChartView;
    String[] states = {"Moods", "Feelings", "Physical", "Relationships"};
    int[] values = {5, 7, 3, 4};

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
        Log.d("Debug", "1");
        List<DataEntry> dataEntries = new ArrayList<>();
        Log.d("Debug", "2");

        for (int i = 0; i < states.length; i++) {
            dataEntries.add(new ValueDataEntry(states[i], values[i]));

        }
        Log.d("Debug", "3");
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}
