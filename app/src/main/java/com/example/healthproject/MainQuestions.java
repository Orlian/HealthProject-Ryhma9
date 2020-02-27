package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Kysymys-luokka, joka sisältää aplikaation kysymysosion
 * @author Joonas Soininen
 * @version 1.1
 */
public class MainQuestions extends AppCompatActivity {
    int answer1=0, answer2=0;

    HashMap<Date, ArrayList<User>>  dateResults = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions);

        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.question1);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.question2);
        int chekedRadioButtonID = radioGroup1.getCheckedRadioButtonId();
        int chekedRadioButtonID2 = radioGroup2.getCheckedRadioButtonId();

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup name, int checkedId) {
                switch (checkedId) {
                    case R.id.rb11:
                        Log.v("DEBUG", "Ykkönen");
                        answer1=1;
                        break;
                    case R.id.rb12:
                        Log.v("DEBUG", "Kakkonen");
                        answer1=2;
                        break;
                    case R.id.rb13:
                        Log.v("DEBUG", "Kolmonen");
                        answer1=3;
                        break;
                    case R.id.rb14:
                        Log.v("DEBUG", "Nelonen");
                        answer1=4;
                        break;
                    case R.id.rb15:
                        Log.v("DEBUG", "Vitonen");
                        answer1=5;
                        break;
                    default:
                        Log.v("DEBUG", "Ei mitteen");
                        answer1=0;
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup name2, int checkedId2) {
                switch (checkedId2) {
                    case R.id.rb21:
                        Log.v("DEBUG", "Ykkönen2");
                        answer2=1;
                        break;
                    case R.id.rb22:
                        Log.v("DEBUG", "Kakkonen2");
                        answer2=2;
                        break;
                    case R.id.rb23:
                        Log.v("DEBUG", "Kolmonen2");
                        answer2=3;
                        break;
                    case R.id.rb24:
                        Log.v("DEBUG", "Nelonen2");
                        answer2=4;
                        break;
                    case R.id.rb25:
                        Log.v("DEBUG", "Vitonen2");
                        answer2=5;
                        break;
                    default:
                        Log.v("DEBUG", "Ei mitteen2");
                        answer2=0;
                        break;
                }
            }
        });
    }
    // Päivämäärän mukaan tallentuvat listat, avaimena päivämäärä ja kellonaika, jotta voi useampia entryjä tehdä per päivä
    // yhteenvetoon vastausten kokonaisarvo ja sen mukaan palaute
    // keskiarvoja palautteassa voi kysymykset ryhmitellä aiheiden mukaan
    //public void onClick(View x){
      //  for (int values : tulevalista){
        //    tulevalista.add(values);
        //}

    }
/* Listajuttu


ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
ArrayList<String> singleList = new ArrayList<String>();
singleList.add("hello");
singleList.add("world");
listOLists.add(singleList);

ArrayList<String> anotherList = new ArrayList<String>();
anotherList.add("this is another list");
listOLists.add(anotherList);


 */

/*





 */