package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

/**
 * Kysymys-luokka, joka sisältää aplikaation kysymysosion
 * @author Joonas Soininen
 * @version 1.0
 */
public class MainQuestions extends AppCompatActivity {
    int answer1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.questionText1);
        int chekedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int checkedId) {
                switch (checkedId) {
                    default:
                        Log.v("DEBUG", "Ei mitteen");
                        break;
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

