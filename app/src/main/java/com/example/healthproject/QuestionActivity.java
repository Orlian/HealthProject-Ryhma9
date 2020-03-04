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
 * @version 1.8
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
        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.question3);
        RadioGroup radioGroup4 = (RadioGroup) findViewById(R.id.question4);
        RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.question5);
        RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.question6);
        RadioGroup radioGroup7 = (RadioGroup) findViewById(R.id.question7);
        RadioGroup radioGroup8 = (RadioGroup) findViewById(R.id.question8);
        RadioGroup radioGroup9 = (RadioGroup) findViewById(R.id.question9);
        RadioGroup radioGroup10 = (RadioGroup) findViewById(R.id.question10);
        RadioGroup radioGroup11 = (RadioGroup) findViewById(R.id.question11);
        RadioGroup radioGroup12 = (RadioGroup) findViewById(R.id.question12);
        RadioGroup radioGroup13 = (RadioGroup) findViewById(R.id.question13);
        RadioGroup radioGroup14 = (RadioGroup) findViewById(R.id.question14);
        RadioGroup radioGroup15 = (RadioGroup) findViewById(R.id.question15);
        RadioGroup radioGroup16 = (RadioGroup) findViewById(R.id.question16);
        RadioGroup radioGroup17 = (RadioGroup) findViewById(R.id.question17);
        RadioGroup radioGroup18 = (RadioGroup) findViewById(R.id.question18);
        RadioGroup radioGroup19 = (RadioGroup) findViewById(R.id.question19);
        RadioGroup radioGroup20 = (RadioGroup) findViewById(R.id.question20);

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
                Log.v("DEBUG2","Kysymys 1 arvo: " +answer1); //DEBUG/TESTIKOODI
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question2, int checkedId2) {
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
                Log.v("DEBUG2","Kysymys 2 arvo: " +answer2); //DEBUG/TESTIKOODI
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question3, int checkedId3) {
                switch (checkedId3) {
                    case R.id.rb31:
                        Log.v("DEBUG", "31");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb32:
                        Log.v("DEBUG", "32");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb33:
                        Log.v("DEBUG", "33");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb34:
                        Log.v("DEBUG", "34");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb35:
                        Log.v("DEBUG", "35");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "03");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer3 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 3 arvo: " +answer3); //DEBUG/TESTIKOODI
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question4, int checkedId4) {
                switch (checkedId4) {
                    case R.id.rb41:
                        Log.v("DEBUG", "41");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb42:
                        Log.v("DEBUG", "42");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb43:
                        Log.v("DEBUG", "43");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb44:
                        Log.v("DEBUG", "44");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb45:
                        Log.v("DEBUG", "45");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "04");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer4 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 4 arvo: " +answer4); //DEBUG/TESTIKOODI
            }
        });

        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question5, int checkedId5) {
                switch (checkedId5) {
                    case R.id.rb51:
                        Log.v("DEBUG", "51");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb52:
                        Log.v("DEBUG", "52");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb53:
                        Log.v("DEBUG", "53");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb54:
                        Log.v("DEBUG", "54");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb55:
                        Log.v("DEBUG", "55");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "05");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer5 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 5 arvo: " +answer5); //DEBUG/TESTIKOODI
            }
        });

        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question6, int checkedId6) {
                switch (checkedId6) {
                    case R.id.rb61:
                        Log.v("DEBUG", "61");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb62:
                        Log.v("DEBUG", "62");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb63:
                        Log.v("DEBUG", "63");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb64:
                        Log.v("DEBUG", "64");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb65:
                        Log.v("DEBUG", "65");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "06");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer6 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 6 arvo: " +answer6); //DEBUG/TESTIKOODI
            }
        });

        radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question7, int checkedId7) {
                switch (checkedId7) {
                    case R.id.rb71:
                        Log.v("DEBUG", "71");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb72:
                        Log.v("DEBUG", "72");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb73:
                        Log.v("DEBUG", "73");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb74:
                        Log.v("DEBUG", "74");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb75:
                        Log.v("DEBUG", "75");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "07");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer7 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 7 arvo: " +answer7); //DEBUG/TESTIKOODI
            }
        });

        radioGroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question8, int checkedId8) {
                switch (checkedId8) {
                    case R.id.rb81:
                        Log.v("DEBUG", "81");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb82:
                        Log.v("DEBUG", "82");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb83:
                        Log.v("DEBUG", "83");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb84:
                        Log.v("DEBUG", "84");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb85:
                        Log.v("DEBUG", "85");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "08");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer8 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 8 arvo: " +answer8); //DEBUG/TESTIKOODI
            }
        });

        radioGroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question9, int checkedId9) {
                switch (checkedId9) {
                    case R.id.rb91:
                        Log.v("DEBUG", "91");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb92:
                        Log.v("DEBUG", "92");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb93:
                        Log.v("DEBUG", "93");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb94:
                        Log.v("DEBUG", "94");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb95:
                        Log.v("DEBUG", "95");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "09");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer9 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 9 arvo: " +answer9); //DEBUG/TESTIKOODI
            }
        });

        radioGroup10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question10, int checkedId10) {
                switch (checkedId10) {
                    case R.id.rb101:
                        Log.v("DEBUG", "101");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb102:
                        Log.v("DEBUG", "102");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb103:
                        Log.v("DEBUG", "103");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb104:
                        Log.v("DEBUG", "104");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb105:
                        Log.v("DEBUG", "105");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "010");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer10 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 10 arvo: " +answer10); //DEBUG/TESTIKOODI
            }
        });

        radioGroup11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question11, int checkedId11) {
                switch (checkedId11) {
                    case R.id.rb111:
                        Log.v("DEBUG", "111");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb112:
                        Log.v("DEBUG", "112");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb113:
                        Log.v("DEBUG", "113");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb114:
                        Log.v("DEBUG", "114");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb115:
                        Log.v("DEBUG", "115");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "011");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer11 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 11 arvo: " +answer11); //DEBUG/TESTIKOODI
            }
        });

        radioGroup12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question12, int checkedId12) {
                switch (checkedId12) {
                    case R.id.rb121:
                        Log.v("DEBUG", "121");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb122:
                        Log.v("DEBUG", "122");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb123:
                        Log.v("DEBUG", "123");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb124:
                        Log.v("DEBUG", "124");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb125:
                        Log.v("DEBUG", "125");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "012");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer12 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 12 arvo: " +answer12); //DEBUG/TESTIKOODI
            }
        });

        radioGroup13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question13, int checkedId13) {
                switch (checkedId13) {
                    case R.id.rb131:
                        Log.v("DEBUG", "131");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb132:
                        Log.v("DEBUG", "132");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb133:
                        Log.v("DEBUG", "133");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb134:
                        Log.v("DEBUG", "134");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb135:
                        Log.v("DEBUG", "135");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "013");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer13 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 13 arvo: " +answer13); //DEBUG/TESTIKOODI
            }
        });

        radioGroup14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question14, int checkedId14) {
                switch (checkedId14) {
                    case R.id.rb141:
                        Log.v("DEBUG", "141");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb142:
                        Log.v("DEBUG", "142");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb143:
                        Log.v("DEBUG", "143");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb144:
                        Log.v("DEBUG", "144");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb145:
                        Log.v("DEBUG", "145");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "014");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer14 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 14 arvo: " +answer14); //DEBUG/TESTIKOODI
            }
        });

        radioGroup15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question15, int checkedId15) {
                switch (checkedId15) {
                    case R.id.rb151:
                        Log.v("DEBUG", "151");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb152:
                        Log.v("DEBUG", "152");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb153:
                        Log.v("DEBUG", "153");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb154:
                        Log.v("DEBUG", "154");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb155:
                        Log.v("DEBUG", "155");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "015");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer15 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 15 arvo: " +answer15); //DEBUG/TESTIKOODI
            }
        });

        radioGroup16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question16, int checkedId16) {
                switch (checkedId16) {
                    case R.id.rb161:
                        Log.v("DEBUG", "161");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb162:
                        Log.v("DEBUG", "162");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb163:
                        Log.v("DEBUG", "163");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb164:
                        Log.v("DEBUG", "164");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb165:
                        Log.v("DEBUG", "165");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "016");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer16 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 16 arvo: " +answer16); //DEBUG/TESTIKOODI
            }
        });

        radioGroup17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question17, int checkedId17) {
                switch (checkedId17) {
                    case R.id.rb171:
                        Log.v("DEBUG", "171");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb172:
                        Log.v("DEBUG", "172");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb173:
                        Log.v("DEBUG", "173");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb174:
                        Log.v("DEBUG", "174");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb175:
                        Log.v("DEBUG", "175");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "017");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer17 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 17 arvo: " +answer17); //DEBUG/TESTIKOODI
            }
        });

        radioGroup18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question18, int checkedId18) {
                switch (checkedId18) {
                    case R.id.rb181:
                        Log.v("DEBUG", "181");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb182:
                        Log.v("DEBUG", "182");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb183:
                        Log.v("DEBUG", "183");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb184:
                        Log.v("DEBUG", "184");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb185:
                        Log.v("DEBUG", "185");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "018");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer18 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 18 arvo: " +answer18); //DEBUG/TESTIKOODI
            }
        });

        radioGroup19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question19, int checkedId19) {
                switch (checkedId19) {
                    case R.id.rb191:
                        Log.v("DEBUG", "191");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb192:
                        Log.v("DEBUG", "192");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb193:
                        Log.v("DEBUG", "193");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb194:
                        Log.v("DEBUG", "194");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb195:
                        Log.v("DEBUG", "195");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "019");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer19 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 19 arvo: " +answer19); //DEBUG/TESTIKOODI
            }
        });

        radioGroup20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question20, int checkedId20) {
                switch (checkedId20) {
                    case R.id.rb201:
                        Log.v("DEBUG", "201");
                        answers[currentQuestion] = 1;
                        break;
                    case R.id.rb202:
                        Log.v("DEBUG", "202");
                        answers[currentQuestion] = 2;
                        break;
                    case R.id.rb203:
                        Log.v("DEBUG", "203");
                        answers[currentQuestion] = 3;
                        break;
                    case R.id.rb204:
                        Log.v("DEBUG", "204");
                        answers[currentQuestion] = 4;
                        break;
                    case R.id.rb205:
                        Log.v("DEBUG", "205");
                        answers[currentQuestion] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "020");
                        answers[currentQuestion] = 0;
                        break;
                }
                answer20 = getTotalPoint();
                Log.v("DEBUG2","Kysymys 20 arvo: " +answer20); //DEBUG/TESTIKOODI
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
        group1=answer1+answer2; //TESTIKOODI
        Log.v("DEBUG3","SendButton 1+2 " +group1); //TESITKOODI

     /*
     lista tuloksista, joka tallennetaan kun nappia painetaan
     for (int values : tulevalista){
        tulevalista.add(values);
    }*/
        if(v == findViewById(R.id.saveButton)){
            Date date = new Date();

            group1 = answer1 + answer2 + answer19 + answer20;
            group2 = answer3 + answer4 + answer5 + answer6 + answer9 + answer16 + answer18;
            group3 = answer7 + answer8 + answer10 + answer11 + answer12 + answer15 + answer17;
            group4 = answer13 + answer14;
            groupAverage = group1 + group2 + group3 + group4;

            Log.v("DEBUG3","Date: "+date); //TESTIKOODI
            Log.v("DEBUG3","Group 1: "+group1); //TESTIKOODI
            Log.v("DEBUG3","Group 2: "+group2); //TESTIKOODI
            Log.v("DEBUG3","Group 3: "+group3); //TESTIKOODI
            Log.v("DEBUG3","Group 4: "+group4); //TESTIKOODI
            Log.v("DEBUG3","Group 5: "+groupAverage); //TESTIKOODI


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
