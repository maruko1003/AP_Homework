package com.example.aphomework;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Lesson3MainActivity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson3]";
    private Button btn_lesson3_1_anr;
    private Button btn_lesson3_2_library;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_lesson3_main);

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson3");

        // homework 3.1
        btn_lesson3_1_anr = (Button) findViewById(R.id.btn_l3_1_anr);

        // homework 3.2
        btn_lesson3_2_library = (Button) findViewById(R.id.btn_l3_2_library);

        // 3.1: set OnClickListener for send intent via click button
        btn_lesson3_1_anr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    Log.d(TAG,"Start Sleep for ANR");
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 3.2 : Click button to launch Map
        btn_lesson3_2_library.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent_launch_map = new Intent(Lesson3MainActivity.this, Lesson3_2_Main_Activity.class);
                startActivity(intent_launch_map);
            }
        });
    }


}
