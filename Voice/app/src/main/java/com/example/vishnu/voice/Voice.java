package com.example.vishnu.voice;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.speech.RecognizerIntent;


import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;



public class Voice extends Activity implements OnClickListener{


    ListView lv;
    TextView tv;
    ImageView iv;
    float tr=300;
    int times=0;
    static final int check = 1111;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==check && resultCode == RESULT_OK){

            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));
            tv = (TextView)findViewById(R.id.Voiceresult);

            iv= (ImageView)findViewById(R.id.circle);

            while(times<5)
            {switch (results.get(times)) {


                case "down":
                    tv.setText(results.get(times));
                    if(iv.getTranslationY()>=1000)
                    {
                        iv.setTranslationY(100);

                    }
                    else
                    iv.setTranslationY(iv.getTranslationY()+tr);
                    times=5;
                    break;
                case "up":
                    tv.setText(results.get(times));
                    if(iv.getTranslationY()<=100)
                    {
                        iv.setTranslationY(1000);

                    }
                    iv.setTranslationY(iv.getTranslationY()-tr);
                    times=5;
                    break;
                case "left":
                    tv.setText(results.get(times));
                    if(iv.getTranslationX()<=-500)
                    {
                        iv.setTranslationX(550);

                    }
                    else
                    iv.setTranslationX(iv.getTranslationX()-tr);
                    times=5;
                    break;
                case "right":
                    tv.setText(results.get(times));
                    if(iv.getTranslationX()>=500)
                    {
                        iv.setTranslationX(-550);

                    }
                    else
                    iv.setX(iv.getX()+tr);
                    times=5;
                    break;


                default: times++;

            }

            }







        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.voice);
        lv = (ListView)findViewById(R.id.lvVoiceReturn);
        Button b= (Button)findViewById(R.id.voice);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        times=0;
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now");
        startActivityForResult(i,check);
    }
}