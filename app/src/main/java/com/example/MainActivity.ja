package com.example.aphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "[HomeWork]";
    private Button btn_goto_lesson3 ;
    private Button btn_goto_lesson4 ;
    private Button btn_goto_lesson5 ;
    private Button btn_goto_lesson6 ;
    private Button btn_goto_lesson7 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"Enter Lesson Main");

        btn_goto_lesson3 = (Button) findViewById(R.id.btn_main_lesson3);
        btn_goto_lesson4 = (Button) findViewById(R.id.btn_main_lesson4);
        btn_goto_lesson5 = (Button) findViewById(R.id.btn_main_lesson5);
        btn_goto_lesson6 = (Button) findViewById(R.id.btn_main_lesson6);
        btn_goto_lesson7 = (Button) findViewById(R.id.btn_main_lesson7);

        // Go to Lesson3 Main Page
        btn_goto_lesson3.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG,"Enter Lesson3-1");
                Intent intent_goto_lesson3 = new Intent(MainActivity.this,Lesson3MainActivity.class);
                startActivity(intent_goto_lesson3);
            }
        });

        // Go to Lesson4 Main Page
        btn_goto_lesson4.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG,"Enter Lesson4");
                Intent intent_goto_lesson4 = new Intent(MainActivity.this,Lesson4MainActivity.class);
                startActivity(intent_goto_lesson4);
            }
        });

        // Go to Lesson5 Main Page
        btn_goto_lesson5.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG,"Enter Lesson5");
                Intent intent_goto_lesson5 = new Intent(MainActivity.this,Lesson5MainActivity.class);
                startActivity(intent_goto_lesson5);
            }
        });

        // Go to Lesson6 Main Page
        btn_goto_lesson6.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG,"Enter Lesson6");
                Intent intent_goto_lesson6 = new Intent(MainActivity.this,Lesson6MainActivity.class);
                startActivity(intent_goto_lesson6);
            }
        });

        // Go to Lesson7 Main Page
        btn_goto_lesson7.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Log.d(TAG,"Enter Lesson6");
                Intent intent_goto_lesson7 = new Intent(MainActivity.this,Lesson7MainActivity.class);
                startActivity(intent_goto_lesson7);
            }
        });
    }
}
