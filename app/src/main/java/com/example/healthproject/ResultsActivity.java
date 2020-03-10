package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.healthproject.QuestionActivity.EXTRA_GROUP1;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP2;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP3;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP4;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP_AVERAGE;
/**
 * Yhteenveto-luokka, joka näyttää käyttäjälle QuestionActivityssä saamiensa pisteiden mukaiset tekstimuotoiset palautteet.
 * Luokka lähettää pisteet eteenpäin joko MainActivityyn tai StatisticActivityyn, riippuen minne käyttäjä haluaa siirtyä nappia painamalla.
 * @author Oskari Piiroinen
 * @version 1.3
 */
public class ResultsActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);



        if (group1==0){
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

        if (group2==0){
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

        if (group3==0){
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

        if (group4==0){
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

        if (groupAverage==0){
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
     *  Menu nappia painamalla käyttäjä siirtyy MainActivityyn ja siirtää int group1, 2, 3, 4 ja GroupAverage arvot MainActivityyn.
     *  Statistics nappia painamalla käyttäjä siirtyy StatisticsActivityyn ja siirtää int group1, 2, 3, 4 ja GroupAverage arvot StatisticsActivityyn.
     */
    public void onClick(View a) {
        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2, 0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3, 0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4, 0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE, 0);

        if (a == findViewById(R.id.mainMenuButton)) {
            Intent mainIntent = new Intent(ResultsActivity.this, MainActivity.class);
            mainIntent.putExtra(EXTRA_GROUP1, group1);
            mainIntent.putExtra(EXTRA_GROUP2, group2);
            mainIntent.putExtra(EXTRA_GROUP3, group3);
            mainIntent.putExtra(EXTRA_GROUP4, group4);
            mainIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
            startActivity(mainIntent);

        } else if (a == findViewById(R.id.statisticsButton)) {
            Intent statsIntent = new Intent(ResultsActivity.this, StatisticsActivity.class);
            statsIntent.putExtra(EXTRA_GROUP1, group1);
            statsIntent.putExtra(EXTRA_GROUP2, group2);
            statsIntent.putExtra(EXTRA_GROUP3, group3);
            statsIntent.putExtra(EXTRA_GROUP4, group4);
            statsIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
            startActivity(statsIntent);
        }
    }
}
