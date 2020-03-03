package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Kysymys-luokka, joka sisältää aplikaation kysymysosion
 * @author Joonas Soininen
 * @version 1.1
 */
public class QuestionActivity extends AppCompatActivity {
    int answer1 = 0, answer2 = 0;
    int group1 = 0, group2 = 0, group3 = 0, group4 = 0, groupAverage = 0;
    public static final String EXTRA_GROUP1 = "group1score";
    public static final String EXTRA_GROUP2 = "group2score";
    public static final String EXTRA_GROUP3 = "group3score";
    public static final String EXTRA_GROUP4 = "group4score";
    public static final String EXTRA_GROUP_AVERAGE = "group5score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions);
        final Button button1 = (Button) findViewById(R.id.saveButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
            }
        });

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
                        answer1 = 1;
                        break;
                    case R.id.rb12:
                        Log.v("DEBUG", "Kakkonen");
                        answer1 = 2;
                        break;
                    case R.id.rb13:
                        Log.v("DEBUG", "Kolmonen");
                        answer1 = 3;
                        break;
                    case R.id.rb14:
                        Log.v("DEBUG", "Nelonen");
                        answer1 = 4;
                        break;
                    case R.id.rb15:
                        Log.v("DEBUG", "Vitonen");
                        answer1 = 5;
                        break;
                    default:
                        Log.v("DEBUG", "Ei mitteen");
                        answer1 = 0;
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
                        answer2 = 1;
                        break;
                    case R.id.rb22:
                        Log.v("DEBUG", "Kakkonen2");
                        answer2 = 2;
                        break;
                    case R.id.rb23:
                        Log.v("DEBUG", "Kolmonen2");
                        answer2 = 3;
                        break;
                    case R.id.rb24:
                        Log.v("DEBUG", "Nelonen2");
                        answer2 = 4;
                        break;
                    case R.id.rb25:
                        Log.v("DEBUG", "Vitonen2");
                        answer2 = 5;
                        break;
                    default:
                        Log.v("DEBUG", "Ei mitteen2");
                        answer2 = 0;
                        break;
                }
            }
        });
    }
    public void onClick(View v){
     /*
     lista tuloksista, joka tallennetaan kun nappia painetaan
     for (int values : tulevalista){
        tulevalista.add(values);
    }*/
        if(v == findViewById(R.id.saveButton)){
            //Tänne siirtyminen Results aktiviteettiin (*ei vielä luotu*)
            Intent statsIntent = new Intent(QuestionActivity.this, ResultsActivity.class);
            //Extrana tänne kyseisen käyttäjän vastausdatan!
            statsIntent.putExtra(EXTRA_GROUP1, group1);
            statsIntent.putExtra(EXTRA_GROUP2, group2);
            statsIntent.putExtra(EXTRA_GROUP3, group3);
            statsIntent.putExtra(EXTRA_GROUP4, group4);
            statsIntent.putExtra(EXTRA_GROUP_AVERAGE, groupAverage);
            startActivity(statsIntent);
        }

    }
}
    // Päivämäärän mukaan tallentuvat listat, avaimena päivämäärä ja kellonaika, jotta voi useampia entryjä tehdä per päivä
    // yhteenvetoon vastausten kokonaisarvo ja sen mukaan palaute
    // keskiarvoja palautteassa voi kysymykset ryhmitellä aiheiden mukaan
    //Hashmap lajittelee päivittäin saadut kysymysten vastaukset
    //Date oliolla saadaan päivämäärä
    // Date date = new Date();
    //HashMap<Date, ArrayList<User>>  dateResults = new HashMap<>();
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