package com.example.healthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kysymys-luokka, joka sisältää aplikaation kysymysosion
 * @author Joonas Soininen
 * @version 2.5
 */
public class QuestionActivity extends AppCompatActivity {

    int answer1 = 0, answer2 = 0, answer3 = 0, answer4 = 0, answer5 = 0;
    int answer6 = 0, answer7 = 0, answer8 = 0, answer9 = 0, answer10 = 0;
    int answer11 = 0, answer12 = 0, answer13 = 0, answer14 = 0, answer15 = 0;
    int answer16 = 0, answer17 = 0, answer18 = 0, answer19 = 0, answer20 = 0;

    RadioGroup radioGroup;
    //private SharedPreferences listPrefs;
    //private User testUser, activeUser;
    //private Map<String, Object> retMap;



    int answers[];

    int group1 = 0, group2 = 0, group3 = 0, group4 = 0, groupAverage = 0;

    public static final String EXTRA_GROUP1 = "group1score";
    public static final String EXTRA_GROUP2 = "group2score";
    public static final String EXTRA_GROUP3 = "group3score";
    public static final String EXTRA_GROUP4 = "group4score";
    public static final String EXTRA_GROUP_AVERAGE = "group5score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final Button button1 = (Button) findViewById(R.id.saveButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1.setEnabled(false);
                Log.v("DEBUG3","Save/Tallenna onClick"); //DEBUG / TESTIKOODI
                sendButton(v);

            }
        });

        answers = new int[20];

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

            /**
             * Metodi tarkistaa mikä kyseisen radiogroupin radioButtonin valittu arvo on ja tallentaa sen answer1-muuttujaan
             * @param question1 vastaava kysymys
             * @param checkedId valitun radioButtonin tunniste ja siihen liittyvä arvo
             */
            @Override
            public void onCheckedChanged(RadioGroup question1, int checkedId) {
                switch (checkedId) {
                    case R.id.rb11:
                        Log.v("DEBUG", "11");
                        answers[0] = 1;
                        break;
                    case R.id.rb12:
                        Log.v("DEBUG", "12");
                        answers[0] = 2;
                        break;
                    case R.id.rb13:
                        Log.v("DEBUG", "13");
                        answers[0] = 3;
                        break;
                    case R.id.rb14:
                        Log.v("DEBUG", "14");
                        answers[0] = 4;
                        break;
                    case R.id.rb15:
                        Log.v("DEBUG", "15");
                        answers[0] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "01");
                        answers[0] = 0;
                        break;
                }
                answer1 = answers[0];
                Log.v("DEBUG2","Kysymys 1 arvo: " +answer1); //DEBUG / TESTIKOODI
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question2, int checkedId2) {
                switch (checkedId2) {
                    case R.id.rb21:
                        Log.v("DEBUG", "21");
                        answers[1] = 1;
                        break;
                    case R.id.rb22:
                        Log.v("DEBUG", "22");
                        answers[1] = 2;
                        break;
                    case R.id.rb23:
                        Log.v("DEBUG", "23");
                        answers[1] = 3;
                        break;
                    case R.id.rb24:
                        Log.v("DEBUG", "24");
                        answers[1] = 4;
                        break;
                    case R.id.rb25:
                        Log.v("DEBUG", "25");
                        answers[1] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "02");
                        answers[1] = 0;
                        break;
                }
                answer2 = answers[1];
                Log.v("DEBUG2","Kysymys 2 arvo: " +answer2); //DEBUG / TESTIKOODI
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question3, int checkedId3) {
                switch (checkedId3) {
                    case R.id.rb31:
                        Log.v("DEBUG", "31");
                        answers[2] = 1;
                        break;
                    case R.id.rb32:
                        Log.v("DEBUG", "32");
                        answers[2] = 2;
                        break;
                    case R.id.rb33:
                        Log.v("DEBUG", "33");
                        answers[2] = 3;
                        break;
                    case R.id.rb34:
                        Log.v("DEBUG", "34");
                        answers[2] = 4;
                        break;
                    case R.id.rb35:
                        Log.v("DEBUG", "35");
                        answers[2] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "03");
                        answers[2] = 0;
                        break;
                }
                answer3 = answers[2];
                Log.v("DEBUG2","Kysymys 3 arvo: " +answer3); //DEBUG / TESTIKOODI
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question4, int checkedId4) {
                switch (checkedId4) {
                    case R.id.rb41:
                        Log.v("DEBUG", "41");
                        answers[3] = 1;
                        break;
                    case R.id.rb42:
                        Log.v("DEBUG", "42");
                        answers[3] = 2;
                        break;
                    case R.id.rb43:
                        Log.v("DEBUG", "43");
                        answers[3] = 3;
                        break;
                    case R.id.rb44:
                        Log.v("DEBUG", "44");
                        answers[3] = 4;
                        break;
                    case R.id.rb45:
                        Log.v("DEBUG", "45");
                        answers[3] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "04");
                        answers[3] = 0;
                        break;
                }
                answer4 = answers[3];
                Log.v("DEBUG2","Kysymys 4 arvo: " +answer4); //DEBUG / TESTIKOODI
            }
        });

        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question5, int checkedId5) {
                switch (checkedId5) {
                    case R.id.rb51:
                        Log.v("DEBUG", "51");
                        answers[4] = 1;
                        break;
                    case R.id.rb52:
                        Log.v("DEBUG", "52");
                        answers[4] = 2;
                        break;
                    case R.id.rb53:
                        Log.v("DEBUG", "53");
                        answers[4] = 3;
                        break;
                    case R.id.rb54:
                        Log.v("DEBUG", "54");
                        answers[4] = 4;
                        break;
                    case R.id.rb55:
                        Log.v("DEBUG", "55");
                        answers[4] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "05");
                        answers[4] = 0;
                        break;
                }
                answer5 = answers[4];
                Log.v("DEBUG2","Kysymys 5 arvo: " +answer5); //DEBUG / TESTIKOODI
            }
        });

        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question6, int checkedId6) {
                switch (checkedId6) {
                    case R.id.rb61:
                        Log.v("DEBUG", "61");
                        answers[5] = 1;
                        break;
                    case R.id.rb62:
                        Log.v("DEBUG", "62");
                        answers[5] = 2;
                        break;
                    case R.id.rb63:
                        Log.v("DEBUG", "63");
                        answers[5] = 3;
                        break;
                    case R.id.rb64:
                        Log.v("DEBUG", "64");
                        answers[5] = 4;
                        break;
                    case R.id.rb65:
                        Log.v("DEBUG", "65");
                        answers[5] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "06");
                        answers[5] = 0;
                        break;
                }
                answer6 = answers[5];
                Log.v("DEBUG2","Kysymys 6 arvo: " +answer6); //DEBUG / TESTIKOODI
            }
        });

        radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question7, int checkedId7) {
                switch (checkedId7) {
                    case R.id.rb71:
                        Log.v("DEBUG", "71");
                        answers[6] = 1;
                        break;
                    case R.id.rb72:
                        Log.v("DEBUG", "72");
                        answers[6] = 2;
                        break;
                    case R.id.rb73:
                        Log.v("DEBUG", "73");
                        answers[6] = 3;
                        break;
                    case R.id.rb74:
                        Log.v("DEBUG", "74");
                        answers[6] = 4;
                        break;
                    case R.id.rb75:
                        Log.v("DEBUG", "75");
                        answers[6] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "07");
                        answers[6] = 0;
                        break;
                }
                answer7 = answers[6];
                Log.v("DEBUG2","Kysymys 7 arvo: " +answer7); //DEBUG / TESTIKOODI
            }
        });

        radioGroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question8, int checkedId8) {
                switch (checkedId8) {
                    case R.id.rb81:
                        Log.v("DEBUG", "81");
                        answers[7] = 1;
                        break;
                    case R.id.rb82:
                        Log.v("DEBUG", "82");
                        answers[7] = 2;
                        break;
                    case R.id.rb83:
                        Log.v("DEBUG", "83");
                        answers[7] = 3;
                        break;
                    case R.id.rb84:
                        Log.v("DEBUG", "84");
                        answers[7] = 4;
                        break;
                    case R.id.rb85:
                        Log.v("DEBUG", "85");
                        answers[7] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "08");
                        answers[7] = 0;
                        break;
                }
                answer8 = answers[7];
                Log.v("DEBUG2","Kysymys 8 arvo: " +answer8); //DEBUG / TESTIKOODI
            }
        });

        radioGroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question9, int checkedId9) {
                switch (checkedId9) {
                    case R.id.rb91:
                        Log.v("DEBUG", "91");
                        answers[8] = 1;
                        break;
                    case R.id.rb92:
                        Log.v("DEBUG", "92");
                        answers[8] = 2;
                        break;
                    case R.id.rb93:
                        Log.v("DEBUG", "93");
                        answers[8] = 3;
                        break;
                    case R.id.rb94:
                        Log.v("DEBUG", "94");
                        answers[8] = 4;
                        break;
                    case R.id.rb95:
                        Log.v("DEBUG", "95");
                        answers[8] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "09");
                        answers[8] = 0;
                        break;
                }
                answer9 = answers[8];
                Log.v("DEBUG2","Kysymys 9 arvo: " +answer9); //DEBUG / TESTIKOODI
            }
        });

        radioGroup10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question10, int checkedId10) {
                switch (checkedId10) {
                    case R.id.rb101:
                        Log.v("DEBUG", "101");
                        answers[9] = 1;
                        break;
                    case R.id.rb102:
                        Log.v("DEBUG", "102");
                        answers[9] = 2;
                        break;
                    case R.id.rb103:
                        Log.v("DEBUG", "103");
                        answers[9] = 3;
                        break;
                    case R.id.rb104:
                        Log.v("DEBUG", "104");
                        answers[9] = 4;
                        break;
                    case R.id.rb105:
                        Log.v("DEBUG", "105");
                        answers[9] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "010");
                        answers[9] = 0;
                        break;
                }
                answer10 = answers[9];
                Log.v("DEBUG2","Kysymys 10 arvo: " +answer10); //DEBUG / TESTIKOODI
            }
        });

        radioGroup11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question11, int checkedId11) {
                switch (checkedId11) {
                    case R.id.rb111:
                        Log.v("DEBUG", "111");
                        answers[10] = 1;
                        break;
                    case R.id.rb112:
                        Log.v("DEBUG", "112");
                        answers[10] = 2;
                        break;
                    case R.id.rb113:
                        Log.v("DEBUG", "113");
                        answers[10] = 3;
                        break;
                    case R.id.rb114:
                        Log.v("DEBUG", "114");
                        answers[10] = 4;
                        break;
                    case R.id.rb115:
                        Log.v("DEBUG", "115");
                        answers[10] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "011");
                        answers[10] = 0;
                        break;
                }
                answer11 = answers[10];
                Log.v("DEBUG2","Kysymys 11 arvo: " +answer11); //DEBUG / TESTIKOODI
            }
        });

        radioGroup12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question12, int checkedId12) {
                switch (checkedId12) {
                    case R.id.rb121:
                        Log.v("DEBUG", "121");
                        answers[11] = 1;
                        break;
                    case R.id.rb122:
                        Log.v("DEBUG", "122");
                        answers[11] = 2;
                        break;
                    case R.id.rb123:
                        Log.v("DEBUG", "123");
                        answers[11] = 3;
                        break;
                    case R.id.rb124:
                        Log.v("DEBUG", "124");
                        answers[11] = 4;
                        break;
                    case R.id.rb125:
                        Log.v("DEBUG", "125");
                        answers[11] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "012");
                        answers[11] = 0;
                        break;
                }
                answer12 = answers[11];
                Log.v("DEBUG2","Kysymys 12 arvo: " +answer12); //DEBUG / TESTIKOODI
            }
        });

        radioGroup13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question13, int checkedId13) {
                switch (checkedId13) {
                    case R.id.rb131:
                        Log.v("DEBUG", "131");
                        answers[12] = 1;
                        break;
                    case R.id.rb132:
                        Log.v("DEBUG", "132");
                        answers[12] = 2;
                        break;
                    case R.id.rb133:
                        Log.v("DEBUG", "133");
                        answers[12] = 3;
                        break;
                    case R.id.rb134:
                        Log.v("DEBUG", "134");
                        answers[12] = 4;
                        break;
                    case R.id.rb135:
                        Log.v("DEBUG", "135");
                        answers[12] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "013");
                        answers[12] = 0;
                        break;
                }
                answer13 = answers[12];
                Log.v("DEBUG2","Kysymys 13 arvo: " +answer13); //DEBUG / TESTIKOODI
            }
        });

        radioGroup14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question14, int checkedId14) {
                switch (checkedId14) {
                    case R.id.rb141:
                        Log.v("DEBUG", "141");
                        answers[13] = 1;
                        break;
                    case R.id.rb142:
                        Log.v("DEBUG", "142");
                        answers[13] = 2;
                        break;
                    case R.id.rb143:
                        Log.v("DEBUG", "143");
                        answers[13] = 3;
                        break;
                    case R.id.rb144:
                        Log.v("DEBUG", "144");
                        answers[13] = 4;
                        break;
                    case R.id.rb145:
                        Log.v("DEBUG", "145");
                        answers[13] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "014");
                        answers[13] = 0;
                        break;
                }
                answer14 = answers[13];
                Log.v("DEBUG2","Kysymys 14 arvo: " +answer14); //DEBUG / TESTIKOODI
            }
        });

        radioGroup15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question15, int checkedId15) {
                switch (checkedId15) {
                    case R.id.rb151:
                        Log.v("DEBUG", "151");
                        answers[14] = 1;
                        break;
                    case R.id.rb152:
                        Log.v("DEBUG", "152");
                        answers[14] = 2;
                        break;
                    case R.id.rb153:
                        Log.v("DEBUG", "153");
                        answers[14] = 3;
                        break;
                    case R.id.rb154:
                        Log.v("DEBUG", "154");
                        answers[14] = 4;
                        break;
                    case R.id.rb155:
                        Log.v("DEBUG", "155");
                        answers[14] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "015");
                        answers[14] = 0;
                        break;
                }
                answer15 = answers[14];
                Log.v("DEBUG2","Kysymys 15 arvo: " +answer15); //DEBUG / TESTIKOODI
            }
        });

        radioGroup16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question16, int checkedId16) {
                switch (checkedId16) {
                    case R.id.rb161:
                        Log.v("DEBUG", "161");
                        answers[15] = 1;
                        break;
                    case R.id.rb162:
                        Log.v("DEBUG", "162");
                        answers[15] = 2;
                        break;
                    case R.id.rb163:
                        Log.v("DEBUG", "163");
                        answers[15] = 3;
                        break;
                    case R.id.rb164:
                        Log.v("DEBUG", "164");
                        answers[15] = 4;
                        break;
                    case R.id.rb165:
                        Log.v("DEBUG", "165");
                        answers[15] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "016");
                        answers[15] = 0;
                        break;
                }
                answer16 = answers[15];
                Log.v("DEBUG2","Kysymys 16 arvo: " +answer16); //DEBUG / TESTIKOODI
            }
        });

        radioGroup17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question17, int checkedId17) {
                switch (checkedId17) {
                    case R.id.rb171:
                        Log.v("DEBUG", "171");
                        answers[16] = 1;
                        break;
                    case R.id.rb172:
                        Log.v("DEBUG", "172");
                        answers[16] = 2;
                        break;
                    case R.id.rb173:
                        Log.v("DEBUG", "173");
                        answers[16] = 3;
                        break;
                    case R.id.rb174:
                        Log.v("DEBUG", "174");
                        answers[16] = 4;
                        break;
                    case R.id.rb175:
                        Log.v("DEBUG", "175");
                        answers[16] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "017");
                        answers[16] = 0;
                        break;
                }
                answer17 = answers[16];
                Log.v("DEBUG2","Kysymys 17 arvo: " +answer17); //DEBUG / TESTIKOODI
            }
        });

        radioGroup18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question18, int checkedId18) {
                switch (checkedId18) {
                    case R.id.rb181:
                        Log.v("DEBUG", "181");
                        answers[17] = 1;
                        break;
                    case R.id.rb182:
                        Log.v("DEBUG", "182");
                        answers[17] = 2;
                        break;
                    case R.id.rb183:
                        Log.v("DEBUG", "183");
                        answers[17] = 3;
                        break;
                    case R.id.rb184:
                        Log.v("DEBUG", "184");
                        answers[17] = 4;
                        break;
                    case R.id.rb185:
                        Log.v("DEBUG", "185");
                        answers[17] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "018");
                        answers[17] = 0;
                        break;
                }
                answer18 = answers[17];
                Log.v("DEBUG2","Kysymys 18 arvo: " +answer18); //DEBUG / TESTIKOODI
            }
        });

        radioGroup19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question19, int checkedId19) {
                switch (checkedId19) {
                    case R.id.rb191:
                        Log.v("DEBUG", "191");
                        answers[18] = 1;
                        break;
                    case R.id.rb192:
                        Log.v("DEBUG", "192");
                        answers[18] = 2;
                        break;
                    case R.id.rb193:
                        Log.v("DEBUG", "193");
                        answers[18] = 3;
                        break;
                    case R.id.rb194:
                        Log.v("DEBUG", "194");
                        answers[18] = 4;
                        break;
                    case R.id.rb195:
                        Log.v("DEBUG", "195");
                        answers[18] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "019");
                        answers[18] = 0;
                        break;
                }
                answer19 = answers[18];
                Log.v("DEBUG2","Kysymys 19 arvo: " +answer19); //DEBUG / TESTIKOODI
            }
        });
                //
        radioGroup20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup question20, int checkedId20) {
                switch (checkedId20) {
                    case R.id.rb201:
                        Log.v("DEBUG", "201");
                        answers[19] = 1;
                        break;
                    case R.id.rb202:
                        Log.v("DEBUG", "202");
                        answers[19] = 2;
                        break;
                    case R.id.rb203:
                        Log.v("DEBUG", "203");
                        answers[19] = 3;
                        break;
                    case R.id.rb204:
                        Log.v("DEBUG", "204");
                        answers[19] = 4;
                        break;
                    case R.id.rb205:
                        Log.v("DEBUG", "205");
                        answers[19] = 5;
                        break;
                    default:
                        Log.v("DEBUG", "020");
                        answers[19] = 0;
                        break;
                }
                answer20 = answers[19];
                Log.v("DEBUG2","Kysymys 20 arvo: " +answer20); //DEBUG / TESTIKOODI
            }
        });

    }


    /**
     * Tallennus-metodi, joka säilöö kaikkien vastausten arvot aktiivisen käyttäjän DataListaan
     * @param v aktiivinen View-olio
     */
    public void sendButton(View v){
        Log.v("DEBUG3","Save/Tallenna sendButton"); //DEBUG / TESITKOODI
        int answersGroups[]=new int[5];
        /*
        Gson gson = new Gson();

        SharedPreferences resultPref = getSharedPreferences("RESULTS_PREFS", MODE_PRIVATE);
        Type gsonType = new TypeToken<HashMap>() {}.getType();
        String gsonString = gson.toJson(outer,gsonType);


        listPrefs = getSharedPreferences("LOGIN_PREFS", MODE_PRIVATE);

        String json = listPrefs.getString("ACTIVE_USER", " ");
        testUser = gson.fromJson(json, User.class);
        Log.v("DEBUG5", "Aktiivinen käyttäjä: " + testUser.getUserName());
        activeUser = testUser;


        Date date = new Date();

         */

        group1 = answer1 + answer2 + answer19 + answer20;
        group2 = answer3 + answer4 + answer5 + answer6 + answer9 + answer16 + answer18;
        group3 = answer7 + answer8 + answer10 + answer11 + answer12 + answer15 + answer17;
        group4 = answer13 + answer14;
        groupAverage = group1 + group2 + group3 + group4;

        answersGroups[0]=group1;
        answersGroups[1]=group2;
        answersGroups[2]=group3;
        answersGroups[3]=group4;
        answersGroups[4]=groupAverage;

        Intent statsIntent = new Intent(QuestionActivity.this, ResultsActivity.class);
        //Extrana tänne kyseisen käyttäjän vastausdata!
        statsIntent.putExtra(EXTRA_GROUP1, answersGroups[0]);
        statsIntent.putExtra(EXTRA_GROUP2, answersGroups[1]);
        statsIntent.putExtra(EXTRA_GROUP3, answersGroups[2]);
        statsIntent.putExtra(EXTRA_GROUP4, answersGroups[3]);
        statsIntent.putExtra(EXTRA_GROUP_AVERAGE, answersGroups[4]);
        startActivity(statsIntent);
/*
        for (int i=0; i < answersGroups.length; i++){
            testUser.getDataList().add(answersGroups[i]);
            Log.v("DEBUG4","Datalistan lisäys: " +testUser.getDataList()); //DEBUG / TESTIKOODI

        }

 */

        //Log.v("DEBUG3","Date: "+date); //DEBUG / TESTIKOODI
        Log.v("DEBUG3","Group 1: "+group1); //DEBUG / TESTIKOODI
        Log.v("DEBUG3","Group 2: "+group2); //DEBUG / TESTIKOODI
        Log.v("DEBUG3","Group 3: "+group3); //DEBUG / TESTIKOODI
        Log.v("DEBUG3","Group 4: "+group4); //DEBUG / TESTIKOODI
        Log.v("DEBUG3","Group 5: "+groupAverage); //DEBUG / TESTIKOODI
        /*
        retMap = new Gson().fromJson(gsonString, new TypeToken<HashMap<String, Object>>() {}.getType()); //Uuden mapin luonti gson/json datasta
        Log.v("DEBUG0", "UUSI HASHMAP: "+retMap);
        Map<User, Map<Date, User>>outer;retMap;
        String tarkista;
        tarkista = resultPref.getString("HASHMAP_VALUE", "");
        Log.v("DEBUG0","UUSI HASHMAP ennen if-lausetta: "+tarkista);
        if (tarkista != null){
            testUser = (User) outer.get("Joonas");
            if (testUser.getUserName().equals(activeUser.getUserName())){
                for (int i=0; i < answersGroups.length; i++) {
                    outer.get("Joonas").get("Joonas").getDataList().add(i);
                }
            }
            //outer = new HashMap<>();//new Gson().fromJson(gsonString, new TypeToken<HashMap<Date, User>>() {}.getType());
            Log.v("DEBUG0","Outer juttuja: "+outer);

        } else {
            outer = new HashMap<User, Map<Date, User>>();
            Log.v("DEBUG0", "UUSI HASHMAP: " + outer);
        }


        for (Map.Entry<User, Map<Date, User>> entry : outer.entrySet()){
            Log.v("DEBUG0","Päällimmäinen Avain: "+entry.getKey());
            Log.v("DEBUG0", "Päällimmäinen Arvo: " +entry.getValue());
            testUser=entry.getKey();
        }

        Map<Date,User> inner = outer.get(testUser);
        if (inner == null) {
            inner = new HashMap<Date, User>();
            outer.put(testUser, inner);
            Log.v("DEBUG4", "HashMap OUTER OK!"); //DEBUG / TESTIKOODI
            Log.v("DEBUG4", "HashMap OUTER ARVO: " +outer.toString()); //DEBUG / TESTIKOODI
        }
        inner.put(date, testUser);
        Log.v("DEBUG4", "HashMap INNER OK!"); //DEBUG / TESTIKOODI
        Log.v("DEBUG4","HashMap INNER ARVO: "+inner.toString()); //DEBUG / TESTIKOODI


        resultPref = getSharedPreferences("RESULTS_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor resultsEdit = resultPref.edit();
        gsonType = new TypeToken<HashMap>() {}.getType();
        gsonString = gson.toJson(outer,gsonType);
        resultsEdit.putString("HASHMAP_VALUE", gsonString);
        resultsEdit.commit();
        Log.v("DEBUG0","GSON HASHMAP: "+gsonString);



        //TESTIKOODIA
/*
        for(String s : retMap.keySet()){
            Log.v("DEBUG0","retMap keyset: " +s);
        }

        for (Map.Entry<String, Object> entry : retMap.entrySet()){
            Log.v("DEBUG0","Päällimmäinen Avain: "+entry.getKey());
            Log.v("DEBUG0", "Päällimmäinen Arvo: " +entry.getValue());
        }



        for (Map.Entry<User, Map<Date, User>> entry2 : outer.entrySet()) {
            Log.v("DEBUG0","outer Avain: "+entry2.getKey());
            Log.v("DEBUG0", "outer Arvo: " +entry2.getValue());
        }

        for (Map.Entry<Date, User> entry3 : inner.entrySet()) {
            Log.v("DEBUG0","inner Avain: "+entry3.getKey());
            Log.v("DEBUG0", "inner Arvo: " +entry3.getValue());
            testUser = entry3.getValue();
            Log.v("DEBUG","KÄYTTÄJÄN DATALIST: "+testUser.getDataList());

        }

 */



       /*
        Collection<Object> valuesretMap = retMap.values();
        for(Object valueretMap : valuesretMap){
            Log.v("DEBUG0","retMap VALUE: " +valueretMap);
            Log.v("DEBUG0","retMap VALUES: " +valuesretMap);
        }
        for(String keyretMap : retMap.keySet()){
            Log.v("DEBUG0","retMap key: " +keyretMap);
            Log.v("DEBUG0","retMap.get(key): " +retMap.get(keyretMap));
            //Log.v("DEBUG0","testUser.getDataList(): "+testUser.getDataList());
        }

        Collection<Map<Date, User>> valuesouter = outer.values();
        for(Object valueouter : valuesouter){
            Log.v("DEBUG0","outer VALUE: " +valueouter);
            Log.v("DEBUG0","outer VALUES: " +valuesouter);
        }
        for(User keyouter : outer.keySet()){
            Log.v("DEBUG0","outer key: " +keyouter);
            Log.v("DEBUG0","outer.get(key): " +outer.get(keyouter));
            //Log.v("DEBUG0","testUser.getDataList(): "+testUser.getDataList());
        }

         */


    /*
        Collection<User> valuesinner = inner.values();
        for(Object valueinner : valuesinner){
            Log.v("DEBUG0","inner VALUE: " +valueinner);
            Log.v("DEBUG0","inner VALUES: " +valuesinner);
        }
        for(Date keyinner : inner.keySet()){
            Log.v("DEBUG0","inner key: " +keyinner);
            Log.v("DEBUG0","inner.get(key): " +inner.get(keyinner));
            //Log.v("DEBUG0","testUser.getDataList(): "+testUser.getDataList());
        }

     */
    //TESTIKOODIT LOPPUU





    }
}



