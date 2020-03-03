package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.healthproject.QuestionActivity.EXTRA_GROUP1;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP2;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP3;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP4;
import static com.example.healthproject.QuestionActivity.EXTRA_GROUP_AVERAGE;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_results);


        Intent intent = getIntent();
        int group1 = intent.getIntExtra(EXTRA_GROUP1, 0);
        int group2 = intent.getIntExtra(EXTRA_GROUP2,0);
        int group3 = intent.getIntExtra(EXTRA_GROUP3,0);
        int group4 = intent.getIntExtra(EXTRA_GROUP4,0);
        int groupAverage = intent.getIntExtra(EXTRA_GROUP_AVERAGE,0);

        if (group1 < 5) {
            TextView textView1 = findViewById(R.id.group1Text);
            textView1.setText(R.string.group1critical);
        } else if (group1 < 15) {
            TextView textView2 = findViewById(R.id.group1Text);
            textView2.setText(R.string.group1alarm);
        } else if (group1 < 20) {
            TextView textView3 = findViewById(R.id.group1Text);
            textView3.setText(R.string.group1good);
        } else {
            TextView textView4 = findViewById(R.id.group1Text);
            textView4.setText(R.string.group1excellent);
        }



         if(group2<8){
         TextView textView1 = (TextView) findViewById(R.id.group2Text);
         textView1.setText(R.string.group2critical);
         } else if(group2<17){
         TextView textView2 = (TextView) findViewById(R.id.group2Text);
         textView2.setText(R.string.group2alarm);
         } else if(group2<26){
         TextView textView3 = (TextView) findViewById(R.id.group2Text);
         textView3.setText(R.string.group2midway);
         } else if(group2<35){
         TextView textView4 = (TextView) findViewById(R.id.group2Text);
         textView4.setText(R.string.group2good);
         } else {
         TextView textView5 = (TextView) findViewById(R.id.group2Text);
         textView5.setText(R.string.group2excellent);
         }


         if(group3<8){
         TextView textView1 = (TextView) findViewById(R.id.group3Text);
         textView1.setText(R.string.group3critical);
         } else if(group3<17){
         TextView textView2 = (TextView) findViewById(R.id.group3Text);
         textView2.setText(R.string.group3alarm);
         } else if(group3<26){
         TextView textView3 = (TextView) findViewById(R.id.group3Text);
         textView3.setText(R.string.group3midway);
         } else if(group3<35){
         TextView textView4 = (TextView) findViewById(R.id.group3Text);
         textView4.setText(R.string.group3good);
         } else {
         TextView textView5 = (TextView) findViewById(R.id.group3Text);
         textView5.setText(R.string.group3excellent);
         }


         if(group4<3){
         TextView textView1 = (TextView) findViewById(R.id.group4Text);
         textView1.setText(R.string.group4critical);
         } else if(group4<7){
         TextView textView2 = (TextView) findViewById(R.id.group4Text);
         textView2.setText(R.string.group4midway);
         } else {
         TextView textView3 = (TextView) findViewById(R.id.group4Text);
         textView3.setText(R.string.group4excellent);
         }


         if(groupAverage<21){
         TextView textView1 = (TextView) findViewById(R.id.group5Text);
         textView1.setText(R.string.group5critical);
         } else if(groupAverage<41){
         TextView textView2 = (TextView) findViewById(R.id.group5Text);
         textView2.setText(R.string.group5alarm);
         } else if(groupAverage<61){
         TextView textView3 = (TextView) findViewById(R.id.group5Text);
         textView3.setText(R.string.group5midway);
         } else if(groupAverage<81){
         TextView textView4 = (TextView) findViewById(R.id.group5Text);
         textView4.setText(R.string.group5good);
         } else {
         TextView textView5 = (TextView) findViewById(R.id.group5Text);
         textView5.setText(R.string.group5excellent);
         }
    }
}
