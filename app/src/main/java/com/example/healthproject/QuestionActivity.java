package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Kysymys-luokka, joka sisältää aplikaation kysymysosion
 * @author Joonas Soininen
 * @version 1.1
 */
public class QuestionActivity extends AppCompatActivity {

    int answer1 = 0, answer2 = 0, answer3 = 0, answer4 = 0, answer5 = 0;
    int answer6 = 0, answer7 = 0, answer8 = 0, answer9 = 0, answer10 = 0;
    int answer11 = 0, answer12 = 0, answer13 = 0, answer14 = 0, answer15 = 0;
    int answer16 = 0, answer17 = 0, answer18 = 0, answer19 = 0, answer20 = 0;

    RadioGroup radioGroup;

    int answers[] = new int[20];
    int currentQuestion = 0;

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
                Log.v("DEBUG3","SAVEBUTTON");
                sendButton(v);

            }
        });

        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.question1);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.question2);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question1, int checkedId) {
                switch (checkedId) {
                    case R.id.rb11:
                        Log.v("DEBUG", "11");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb12:
                        Log.v("DEBUG", "12");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb13:
                        Log.v("DEBUG", "13");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb14:
                        Log.v("DEBUG", "14");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb15:
                        Log.v("DEBUG", "15");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "01");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer1 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 1 arvo: " +answer1);
                group1 = answer1 + answer2;
                Log.v("DEBUG2","Arvo1: " +group1);
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup name2, int checkedId2) {
                switch (checkedId2) {
                    case R.id.rb21:
                        Log.v("DEBUG", "21");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb22:
                        Log.v("DEBUG", "22");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb23:
                        Log.v("DEBUG", "23");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb24:
                        Log.v("DEBUG", "24");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb25:
                        Log.v("DEBUG", "25");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "02");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer2 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 2 arvo: " +answer2);
                group1 = answer1 + answer2;
                Log.v("DEBUG2","Arvo2: " +group1);
            }
        });

    }
    public int getTotalPoint() {
        int sum = 0;
        for (int i = 0; i < answers.length; i++ )
            sum += answers[i];

        Log.v("DEBUG2", "SUMMA: " +sum);
        return sum;
    }

    public void sendButton(View v){
        Log.v("DEBUG3","SendButton");

     /*
     lista tuloksista, joka tallennetaan kun nappia painetaan
     for (int values : tulevalista){
        tulevalista.add(values);
    }*/
        if(v == findViewById(R.id.saveButton)){

            group1 = answer1 + answer2 + answer19 + answer20;
            group2 = answer3 + answer4 + answer5 + answer6 + answer9 + answer16 + answer18;
            group3 = answer7 + answer8 + answer10 + answer11 + answer12 + answer15 + answer17;
            group4 = answer13 + answer14;
            groupAverage = group1 + group2 + group3 + group4;


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
    /*
    Päivämäärän mukaan tallentuvat listat, avaimena päivämäärä ja kellonaika, jotta voi useampia entryjä tehdä per päivä
    yhteenvetoon vastausten kokonaisarvo ja sen mukaan palaute
    keskiarvoja palautteassa voi kysymykset ryhmitellä aiheiden mukaan
    Hashmap lajittelee päivittäin saadut kysymysten vastaukset
    Date oliolla saadaan päivämäärä
    Date date = new Date();
    HashMap<Date, ArrayList<User>>  dateResults = new HashMap<>();
    */

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
